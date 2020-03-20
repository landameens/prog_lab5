package controller;

import app.Query;
import controller.commands.Command;
import controller.responce.Response;

public class Controller {

    public Response handleQuery(Query query){

        String commandName = query.getCommandName().getName();

        Command command = new Command(commandName);
        Response response = command.execute();
        return ;
    }
}
