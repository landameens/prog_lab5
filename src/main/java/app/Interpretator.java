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

    /**
     * For user's input string of a command name returns enum constant.
     * @param name
     * @return
     */
    public CommandName interpretateCommandName(String name){
        return CommandName.getCommandNameEnum(name);
    }

    /**
     * Returns a map of fields and invitation messages for a command.
     * @param name
     * @param viewer
     * @return
     */
    public Map<String, String> getMapForInputArguments (CommandName name, Viewer viewer){
        return viewer.getInputMessagesMap().get(name);
    }

    /**
     * Makes map of field and argument values for building a query for simple commands.
     * @param name
     * @param commandList
     * @return
     */
    public Map<String, String> interpretateSimpleCommandArguments (CommandName name,
                                                                   List<String> commandList) {
        String field = mapOfNamesAndFields.get(name);
        String argument = commandList.get(1);

        Map<String, String> map = new HashMap<>();
        map.put(field, argument);

        return map;
    }


}
