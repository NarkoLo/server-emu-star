package fctk.server.controllers;

import fctk.server.entities.GroupDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.group.AddStudentGroupRequest;
import fctk.server.requests.group.EditStudentGroupRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import fctk.server.services.interfaces.IGroupService;
import fctk.server.validators.IdRequestValidator;
import fctk.server.validators.group.AddStudentGroupValidator;
import fctk.server.validators.group.EditStudentGroupValidator;

import java.util.List;

public class GroupController {

    private final IGroupService groupService;
    private final AddStudentGroupValidator addStudentGroupValidator;

    private final EditStudentGroupValidator editStudentGroupValidator;
    private final IdRequestValidator idRequestValidator;

    public GroupController(
            IGroupService groupService,
            AddStudentGroupValidator addStudentGroupValidator,
            EditStudentGroupValidator editStudentGroupValidator,
            IdRequestValidator idRequestValidator
    ) {
        this.groupService = groupService;
        this.addStudentGroupValidator = addStudentGroupValidator;
        this.editStudentGroupValidator = editStudentGroupValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<List<GroupDTO>>> getStudentGroups() {
        long status = 200L;
        CommonResponse<List<GroupDTO>> response;
        try {
            var groupList = groupService.getGroups();
            response = new CommonResponse<>(groupList);
        } catch (Exception e) {
            status = 422L;
            response = new CommonResponse<>(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<GroupDTO>> getStudentGroupById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<GroupDTO> response;
        if (problems.isEmpty()) {
            try {
                var group = groupService.getGroup(idRequest.id());
                response = new CommonResponse<>(group);
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

    public ResponseEntity<CommonResponse<Long>> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        var problems = addStudentGroupValidator.validate(addStudentGroupRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var id = groupService.addGroup(new GroupDTO(null, addStudentGroupRequest.name()));
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

    public ResponseEntity<CommonResponse<Long>> editStudentGroup(EditStudentGroupRequest editStudentGroupRequest) {
        var problems = editStudentGroupValidator.validate(editStudentGroupRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                groupService.updateGroup(new GroupDTO(editStudentGroupRequest.id(), editStudentGroupRequest.name()));
                response = new CommonResponse<>(editStudentGroupRequest.id());
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

    public ResponseEntity<CommonResponse<Long>> deleteStudentGroup(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                groupService.deleteGroup(idRequest.id());
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
