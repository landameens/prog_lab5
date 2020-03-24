package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.ResponseDTO;

import java.util.Map;

public abstract class Command {
    protected String type;
    protected Map<String, String> args;
    protected ResponseDTO responseDTO;

    public Command(String type,
                   Map<String, String> args) {
        this.type = type;
        this.args = args;
    }

    public abstract Response execute(Query query);
}
