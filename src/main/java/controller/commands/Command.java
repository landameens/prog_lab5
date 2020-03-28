package controller.commands;

import controller.response.Response;
import controller.response.ResponseDTO;

import java.util.Map;

public abstract class Command {
    protected String name;
    protected Map<String, String> args;
    protected ResponseDTO responseDTO;

    public Command(String name,
                   Map<String, String> args) {
        this.name = name;
        this.args = args;
        responseDTO = new ResponseDTO();
    }

    public abstract Response execute();
}
