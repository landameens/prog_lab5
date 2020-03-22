package app.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all the information about the command, its name, type and arguments.
 */
public final class Query {
    //TODO: пакетирование - сделать пакет query и в него засунуть нужные классы
    private String commandName;
    private String commandType;
    private Map<String, String> arguments;

    public Query(String commandName,
                 String commandType,
                 Map<String,String> arguments){
        this.commandName = commandName;
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandType() {
        return commandType;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }
}

