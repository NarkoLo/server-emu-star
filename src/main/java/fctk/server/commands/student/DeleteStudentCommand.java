package fctk.server.commands.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.commands.ICommand;
import fctk.server.controllers.StudentController;
import fctk.server.requests.IdRequest;

public class DeleteStudentCommand implements ICommand {
    private final StudentController studentController;

    private final ObjectMapper mapper;

    public DeleteStudentCommand(StudentController studentController, ObjectMapper mapper) {
        this.studentController = studentController;
        this.mapper = mapper;
    }

    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(studentController.deleteStudent(mapper.readValue(json, IdRequest.class)));
    }
}
