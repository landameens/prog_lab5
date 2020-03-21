package controller.commands;

import controller.responce.Response;
import controller.responce.ResponseDTO;

import java.util.Map;

public abstract class Command {
    protected String type;
    protected Map<String, String> args;
    protected ResponseDTO responseDTO;

    public Command(String type, Map<String, String> args) {
        this.type = type;
        this.args = args;
    }

    public Response execute(){
        return Response.getResponse(responseDTO);
    }
}
