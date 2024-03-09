package fctk.server.commands;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICommand {
    String executeToJSON(String json) throws JsonProcessingException;
}
