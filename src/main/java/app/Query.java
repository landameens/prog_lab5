package app;

import java.util.HashMap;


public class Query {
    //TODO: Лучше передавать стрингу, а не енам, когда будешь настраивать сервер могут появиться проблемы из-за сложности енама относительно стринги.
    private CommandName commandName;
    private CommandType commandType;
    private HashMap<String, String> arguments;

    public Query(CommandName commandName, CommandType commandType, HashMap<String,String> arguments){
        this.commandName = commandName;
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandName getCommandName() {
        return commandName;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public HashMap<String, String> getArguments() {
        return arguments;
    }
}

