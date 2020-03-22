package controller;

import app.Query;
import controller.commands.Command;
import controller.response.Response;

public class Controller {

    public Response handleQuery(Query query){

        String commandName = query.getCommandName();

        Command command ;

        return command.execute();
    }
}
