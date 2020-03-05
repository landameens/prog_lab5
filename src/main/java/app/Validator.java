package app;

import app.Exceptions.InputException;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final String UNKNOWN_COMMAND = "Ошибка: Неизвестная команда.";

    private List<String> allCommands = new ArrayList<String>(){
        {
            allCommands.add("help");
            allCommands.add("info");
            allCommands.add("show");
            allCommands.add("add");
            allCommands.add("update");
            allCommands.add("remove_by_id");
            allCommands.add("clear");
            allCommands.add("save");
            allCommands.add("execute_script");
            allCommands.add("exit");
            allCommands.add("add_if_min");
            allCommands.add("remove_lower");
            allCommands.add("history");
            allCommands.add("count_by_group_admin");
            allCommands.add("filter_by_should_be_expelled");
            allCommands.add("filter_less_than_should_be_expelled");
        }

    };

    public void validateCommandName(String commandName) throws InputException {
        if (!allCommands.contains(commandName)) throw new InputException(UNKNOWN_COMMAND);
    }
    public void validate_ADD_CommandArguments(){

    };

}
