package fctk.server.commands.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.commands.ICommand;
import fctk.server.controllers.GroupController;
import fctk.server.requests.IdRequest;

public class DeleteStudentGroupCommand implements ICommand {

    private final GroupController groupController;
    private final ObjectMapper mapper;

    public DeleteStudentGroupCommand(GroupController groupController, ObjectMapper mapper) {
        this.groupController = groupController;
        this.mapper = mapper;
    }

    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(groupController.deleteStudentGroup(mapper.readValue(json, IdRequest.class)));
    }
}
