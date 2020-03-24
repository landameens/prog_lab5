package controller;

import app.query.Query;
import controller.commands.Command;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.exception.CreationException;

public class Controller {
    private Interpretator interpretator;

    public Controller() {
        interpretator = new Interpretator();
    }

    public Response handleQuery(Query query) throws CreationException {

        ICommandFactory commandFactory = interpretator.getFactoryInstance(query.getCommandName());

        Command command = commandFactory.createCommand(query.getCommandName(), query.getCommandType(), query.getArguments());

        return command.execute(query);
    }
}
