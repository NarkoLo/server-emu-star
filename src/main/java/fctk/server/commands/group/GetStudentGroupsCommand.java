package fctk.server.commands.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.commands.ICommand;
import fctk.server.controllers.GroupController;

public class GetStudentGroupsCommand implements ICommand {
    private final GroupController groupController;
    private final ObjectMapper mapper;

    public GetStudentGroupsCommand(GroupController groupController, ObjectMapper mapper) {
        this.groupController = groupController;
        this.mapper = mapper;
    }

    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(groupController.getStudentGroups());
    }
}
