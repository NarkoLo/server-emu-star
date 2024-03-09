package fctk.server.commands;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DefaultCommand implements ICommand {
    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return "{\"body\":\"Bad Request\",\"httpStatus\":400}";
    }
}
