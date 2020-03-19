package app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.CommandName.*;
import static app.CommandType.*;

/**
 * This class is responsible for interpretating user's input
 */
public final class Interpretator {

    private Map<CommandName, CommandType> allCommands = new HashMap<CommandName, CommandType>(){
        {
            allCommands.put(HELP, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(INFO, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(SHOW, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(ADD, COMPOUND_COMMAND);
            allCommands.put(UPDATE, COMPOUND_COMMAND);
            allCommands.put(REMOVE_BY_ID, SIMPLE_COMMAND);
            allCommands.put(CLEAR, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(SAVE, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(EXECUTE_SCRIPT, SIMPLE_COMMAND);
            allCommands.put(EXIT, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(ADD_IF_MIN, COMPOUND_COMMAND);
            allCommands.put(REMOVE_LOWER, COMPOUND_COMMAND);
            allCommands.put(HISTORY, COMMAND_WITHOUT_ARGUMENTS);
            allCommands.put(COUNT_BY_GROUP_ADMIN, COMPOUND_COMMAND);
            allCommands.put(FILTER_BY_SHOULD_BE_EXPELLED, SIMPLE_COMMAND);
            allCommands.put(FILTER_LESS_THEN_SHOULD_BE_EXPELLED, SIMPLE_COMMAND);
        }

    };

    private Map<CommandName,String> mapOfNamesAndFields = new HashMap<CommandName, String>(){
        {
            mapOfNamesAndFields.put(REMOVE_BY_ID, "id");
            mapOfNamesAndFields.put(EXECUTE_SCRIPT, "file_name");
            mapOfNamesAndFields.put(FILTER_BY_SHOULD_BE_EXPELLED, "should_be_expelled");
            mapOfNamesAndFields.put(FILTER_LESS_THEN_SHOULD_BE_EXPELLED, "should_be_expelled");

            mapOfNamesAndFields.put(UPDATE, "id");
        }
    };


    public CommandType interpretateCommandType(CommandName commandName){
        return allCommands.get(commandName);
    }

    public CommandName interpretateCommandName(String name){
        //TODO: много if-ов очень не круто, посмотри, если хочешь писать чистый код, чекни TODOшку в Console.java
        if (name.equals("help") )
            return HELP;
        if (name.equals("info") )
            return INFO;
        if (name.equals("show") )
            return SHOW;
        if (name.equals("add") )
            return ADD;
        if (name.equals("update") )
            return UPDATE;
        if (name.equals("remove_by_id") )
            return REMOVE_BY_ID;
        if (name.equals("clear") )
            return CLEAR;
        if (name.equals("save") )
            return SAVE;
        if (name.equals("execute_script") )
            return EXECUTE_SCRIPT;
        if (name.equals("exit") )
            return EXIT;
        if (name.equals("add_if_min") )
            return ADD_IF_MIN;
        if (name.equals("remove_lower") )
            return REMOVE_LOWER;
        if (name.equals("history") )
            return HISTORY;
        if (name.equals("count_by_group_admin") )
            return COUNT_BY_GROUP_ADMIN;
        if (name.equals("filter_by_should_be_expelled") )
            return FILTER_BY_SHOULD_BE_EXPELLED;
        if (name.equals("filter_less_than_should_be_expelled") )
            return FILTER_LESS_THEN_SHOULD_BE_EXPELLED;
        else return null;
    }

    public Map<String, String> getMapForInputArguments (CommandName name, Viewer viewer){
        return viewer.getInputMessagesMap().get(name);
    }

    public HashMap<String, String> interpretateSimpleCommandArguments (CommandName name, List<String> commandList) {
        String field = mapOfNamesAndFields.get(name);
        String argument = commandList.get(1);
        HashMap<String, String> map = new HashMap<>();
        map.put(field, argument);
        return map;
    }


}
