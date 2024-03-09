package fctk.server.controllers;

import fctk.server.entities.StudentDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.student.AddStudentRequest;
import fctk.server.requests.student.EditStudentRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import fctk.server.services.StudentService;
import fctk.server.services.interfaces.IGroupService;
import fctk.server.services.interfaces.IStudentService;
import fctk.server.validators.IdRequestValidator;
import fctk.server.validators.student.AddStudentValidator;
import fctk.server.validators.student.EditStudentValidator;

import java.util.List;

public class StudentController {

    private final IStudentService studentService;
    private final IGroupService groupService;
    private final IdRequestValidator idRequestValidator;

    private final AddStudentValidator addStudentValidator;

    private final EditStudentValidator editStudentValidator;

    public StudentController(StudentService studentService, IGroupService groupService, IdRequestValidator idRequestValidator, AddStudentValidator addStudentValidator, EditStudentValidator editStudentValidator) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.idRequestValidator = idRequestValidator;
        this.addStudentValidator = addStudentValidator;
        this.editStudentValidator = editStudentValidator;
    }

    public ResponseEntity<CommonResponse<List<StudentDTO>>> getStudentsByGroupId(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<List<StudentDTO>> response;
        if (problems.isEmpty()) {
            try {
                var studentList = studentService.getStudentsByGroupId(idRequest.id());
                response = new CommonResponse<>(studentList);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<StudentDTO>> getStudentById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<StudentDTO> response;
        if (problems.isEmpty()) {
            try {
                var student = studentService.getStudent(idRequest.id());
                response = new CommonResponse<>(student);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Long>> addStudent(AddStudentRequest addStudentRequest) {
        var problems = addStudentValidator.validate(addStudentRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var group = groupService.getGroup(addStudentRequest.groupId());
                var id = studentService.addStudent(
                        new StudentDTO(
                                null,
                                addStudentRequest.surname(),
                                addStudentRequest.name(),
                                addStudentRequest.patronymic(),
                                addStudentRequest.status(),
                                group
                        )
                );
                response = new CommonResponse<>(id);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Long>> editStudent(EditStudentRequest editStudentRequest) {
        var problems = editStudentValidator.validate(editStudentRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var group = groupService.getGroup(editStudentRequest.groupId());
                studentService.updateStudent(new StudentDTO(editStudentRequest.id(), editStudentRequest.surname(), editStudentRequest.name(), editStudentRequest.patronymic(), editStudentRequest.status(), group));
                response = new CommonResponse<>(editStudentRequest.id());
            } catch (Exception e) {
                status = 404L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Long>> deleteStudent(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                studentService.deleteStudent(idRequest.id());
                response = new CommonResponse<>(idRequest.id());
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }
}
