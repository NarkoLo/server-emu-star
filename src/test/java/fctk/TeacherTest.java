package fctk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.IServer;
import fctk.server.Server;
import fctk.server.entities.TeacherDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.teacher.AddTeacherRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {
    private IServer server;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = new Server();
        mapper = new ObjectMapper();
    }

    @Test
    void addTeacherTest() throws JsonProcessingException {
        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(1L),
                        201L
                )
        );
        String json = mapper.writeValueAsString(new AddTeacherRequest("Ivanov", "Ivan", "Ivanovich", "Docent"));
        String result = server.executeRequest("addTeacher", json);

        System.out.println(result);

        assertEquals(expected, result);
    }

    @Test
    void getTeacherTest() throws JsonProcessingException {
        String json = mapper.writeValueAsString(new AddTeacherRequest("Ivanov", "Ivan", "Ivanovich", "Docent"));
        server.executeRequest("addTeacher", json);

        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(new TeacherDTO(1L, "Ivanov", "Ivan", "Ivanovich", "Docent")),
                        200L
                )
        );
        String json2 = mapper.writeValueAsString(new IdRequest(1L));
        String result = server.executeRequest("getTeacherById", json2);

        System.out.println(result);

        assertEquals(expected, result);
    }
}
