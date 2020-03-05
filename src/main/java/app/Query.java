package app;

import java.util.Map;

public class Query {
    private CommandName commandName;
    private CommandType commandType;
    private Map<String, String> arguments;

    public Query(CommandName commandName, CommandType commandType, Map<String,String> arguments){
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

    public Map<String, String> getArguments() {
        return arguments;
    }
}

