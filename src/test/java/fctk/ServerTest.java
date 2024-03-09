package fctk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.IServer;
import fctk.server.Server;
import fctk.server.entities.GroupDTO;
import fctk.server.requests.IdRequest;
import fctk.server.requests.group.AddStudentGroupRequest;
import fctk.server.requests.group.EditStudentGroupRequest;
import fctk.server.responses.CommonResponse;
import fctk.server.responses.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    private IServer server;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = new Server();
        mapper = new ObjectMapper();
    }

    @Test
    void testServerBadRequest() throws JsonProcessingException {
        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        "Bad Request",
                        400L
                )
        );
        String result = server.executeRequest("smth", "");
        assertEquals(expected, result);
    }

    @Test
    void testServerAddGroup() throws JsonProcessingException {
        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(1L),
                        201L
                )
        );
        String json = mapper.writeValueAsString(new AddStudentGroupRequest("MMB-103"));
        String result = server.executeRequest("addGroup", json);
        assertEquals(expected, result);
    }

    @Test
    void testServerDeleteGroup() throws JsonProcessingException {
        server.executeRequest(
                "addGroup",
                mapper.writeValueAsString(new AddStudentGroupRequest("MMB-103"))
        );

        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(1L),
                        200L
                )
        );
        String json = mapper.writeValueAsString(new IdRequest(1L));
        String result = server.executeRequest("deleteGroup", json);
        assertEquals(expected, result);
    }

    @Test
    void testServerEditGroup() throws JsonProcessingException {
        server.executeRequest(
                "addGroup",
                mapper.writeValueAsString(new AddStudentGroupRequest("MMB-103"))
        );

        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(1L),
                        200L
                )
        );
        String json = mapper.writeValueAsString(new EditStudentGroupRequest(1L, "MFS-101"));
        String result = server.executeRequest("editGroup", json);
        assertEquals(expected, result);
    }

    @Test
    void testServerGetGroupById() throws JsonProcessingException {
        server.executeRequest(
                "addGroup",
                mapper.writeValueAsString(new AddStudentGroupRequest("MMB-103"))
        );

        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>(new GroupDTO(1L, "MMB-103")),
                        200L
                )
        );
        String json = mapper.writeValueAsString(new IdRequest(1L));
        String result = server.executeRequest("getGroupById", json);
        assertEquals(expected, result);
    }

    @Test
    void testServerValidateError() throws JsonProcessingException {
        var problems = new ArrayList<String>();
        problems.add("Name is null!");
        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>("Error while validate", problems),
                        422L
                )
        );
        String json = "{}";
        String result = server.executeRequest("addGroup", json);
        assertEquals(expected, result);
    }

    @Test
    void testServerServiceException() throws JsonProcessingException {
        String expected = mapper.writeValueAsString(
                new ResponseEntity<>(
                        new CommonResponse<>("Group with id = 1 not found!"),
                        422L
                )
        );
        String json = mapper.writeValueAsString(new IdRequest(1L));
        String result = server.executeRequest("getGroupById", json);
        assertEquals(expected, result);
    }
}