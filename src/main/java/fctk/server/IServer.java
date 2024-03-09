package fctk.server;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IServer {
    String executeRequest(String endPoint, String json) throws JsonProcessingException;
}
