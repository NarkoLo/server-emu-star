package fctk.server.controllers;

import fctk.server.entities.TeacherDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.teacher.AddTeacherRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import fctk.server.services.interfaces.ITeacherService;
import fctk.server.validators.IdRequestValidator;
import fctk.server.validators.teacher.AddTeacherValidator;

public class TeacherController {
    private final ITeacherService teacherService;
    private final AddTeacherValidator addTeacherValidator;

    private final IdRequestValidator idRequestValidator;

    public TeacherController(ITeacherService teacherService, AddTeacherValidator addTeacherValidator, IdRequestValidator idRequestValidator) {
        this.teacherService = teacherService;
        this.addTeacherValidator = addTeacherValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<TeacherDTO>> getTeacherById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<TeacherDTO> response;
        if (problems.isEmpty()) {
            try {
                var subject = teacherService.get(idRequest.id());
                response = new CommonResponse<>(subject);
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

    public ResponseEntity<CommonResponse<Long>> addTeacher(AddTeacherRequest addTeacherRequest) {
        var problems = addTeacherValidator.validate(addTeacherRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var id = teacherService.add(new TeacherDTO(null, addTeacherRequest.surname(), addTeacherRequest.name(), addTeacherRequest.patronymic(), addTeacherRequest.position()));
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
}
