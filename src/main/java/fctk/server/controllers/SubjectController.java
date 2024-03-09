package fctk.server.controllers;

import fctk.server.entities.SubjectDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.subject.AddSubjectRequest;
import fctk.server.requests.subject.EditSubjectRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import fctk.server.services.interfaces.ISubjectService;
import fctk.server.validators.IdRequestValidator;
import fctk.server.validators.subject.AddSubjectValidator;
import fctk.server.validators.subject.EditSubjectValidator;

import java.util.List;

public class SubjectController {
    private final ISubjectService subjectService;

    private final AddSubjectValidator addSubjectValidator;

    private final EditSubjectValidator editSubjectValidator;

    private final IdRequestValidator idRequestValidator;

    public SubjectController(
            ISubjectService subjectService,
            AddSubjectValidator addSubjectValidator,
            EditSubjectValidator editSubjectValidator,
            IdRequestValidator idRequestValidator
    ) {
        this.subjectService = subjectService;
        this.addSubjectValidator = addSubjectValidator;
        this.editSubjectValidator = editSubjectValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<List<SubjectDTO>>> getSubjects() {
        long status = 200L;
        CommonResponse<List<SubjectDTO>> response;
        try {
            var subjectList = subjectService.getAll();
            response = new CommonResponse<>(subjectList);
        } catch (Exception e) {
            status = 422L;
            response = new CommonResponse<>(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<SubjectDTO>> getSubjectById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<SubjectDTO> response;
        if (problems.isEmpty()) {
            try {
                var subject = subjectService.get(idRequest.id());
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

    public ResponseEntity<CommonResponse<Long>> addSubject(AddSubjectRequest addSubjectRequest) {
        var problems = addSubjectValidator.validate(addSubjectRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var id = subjectService.add(new SubjectDTO(null, addSubjectRequest.name()));
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

    public ResponseEntity<CommonResponse<Long>> editSubject(EditSubjectRequest editSubjectRequest) {
        var problems = editSubjectValidator.validate(editSubjectRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                subjectService.update(new SubjectDTO(editSubjectRequest.id(), editSubjectRequest.name()));
                response = new CommonResponse<>(editSubjectRequest.id());
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

    public ResponseEntity<CommonResponse<Long>> deleteSubject(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                subjectService.delete(idRequest.id());
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
