package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.ResponseDTO;
import domain.exception.StudyGroupRepositoryException;

import java.util.Map;

public abstract class Command {
    protected String name;
    protected Map<String, String> args;
    protected ResponseDTO responseDTO;

    public Command(String name,
                   Map<String, String> args) {
        this.name = name;
        this.args = args;
    }

    public abstract Response execute(Query query) throws StudyGroupRepositoryException;
}
