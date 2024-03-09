package fctk.server.commands.teacher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.commands.ICommand;
import fctk.server.controllers.TeacherController;
import fctk.server.requests.teacher.AddTeacherRequest;

public class AddTeacherCommand implements ICommand {

    private final TeacherController teacherController;

    private final ObjectMapper mapper;

    public AddTeacherCommand(TeacherController teacherController, ObjectMapper mapper) {
        this.teacherController = teacherController;
        this.mapper = mapper;
    }

    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(teacherController.addTeacher(mapper.readValue(json, AddTeacherRequest.class)));
    }
}
