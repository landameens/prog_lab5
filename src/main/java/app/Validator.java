package app;

import app.Exceptions.InputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.CommandName.*;

public final class Validator {

    private final String UNKNOWN_COMMAND = "Ошибка: Неизвестная команда.";
    private final String WRONG_NUMBER_OF_ARGUMENTS = "Ошибка: Неверное количество аргументов. ";
    private final String NULL_ARGUMENT = "Ошибка: Аргумент не может быть null. ";
    private final String NOT_INTEGER_ARGUMENT = "Ошибка: Аргумент не целочисленный. ";
    private final String NEGATIVE_ARGUMENT = "Ошибка: Аргумент не является положительным числом. ";

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

    private final Map<CommandName, Integer> numberOfCommandArguments = new HashMap<CommandName, Integer>() {
        {
            put(ADD, 0);
            put(UPDATE, 1);
            put(REMOVE_BY_ID, 1);
            put(EXECUTE_SCRIPT, 1);
            put(ADD_IF_MIN, 0);
            put(REMOVE_LOWER, 0);
            put(COUNT_BY_GROUP_ADMIN, 0);
            put(FILTER_BY_SHOULD_BE_EXPELLED, 1);
            put(FILTER_LESS_THEN_SHOULD_BE_EXPELLED, 1);
        }

    };


    public  void validateNumberOfArguments(CommandName name, CommandType type, List<String> commandList) throws InputException {
        int numberOfArgs = commandList.size()-1;
        if (type.equals(CommandType.COMMAND_WITHOUT_ARGUMENTS) & (numberOfArgs>0) ) {
            throw new InputException(WRONG_NUMBER_OF_ARGUMENTS);
        } else {
            if (numberOfArgs != numberOfCommandArguments.get(name)){
                throw new InputException(WRONG_NUMBER_OF_ARGUMENTS);
            }
        }

    }

    public void validateSimpleCommandArguments(CommandName name, List<String> commandList) throws InputException {
        switch (name){
            case REMOVE_BY_ID:
                if (commandList.get(1) == null) { throw new InputException(NULL_ARGUMENT); }
                try {
                    int id = Integer.parseInt(commandList.get(1));
                    if (id <= 0) {throw new InputException(NEGATIVE_ARGUMENT); }
                } catch (NumberFormatException e){
                    throw new InputException(NOT_INTEGER_ARGUMENT);
                }
                break;

            case EXECUTE_SCRIPT:
                if (commandList.get(1) == null) { throw new InputException(NULL_ARGUMENT); }
                break;

            case FILTER_BY_SHOULD_BE_EXPELLED:
            case FILTER_LESS_THEN_SHOULD_BE_EXPELLED:
                try {
                    int shouldBeExpelled = Integer.parseInt(commandList.get(1));
                    if (shouldBeExpelled <= 0) {throw new InputException(NEGATIVE_ARGUMENT); }
                } catch (NumberFormatException e){
                    throw new InputException(NOT_INTEGER_ARGUMENT);
                }
                break;
        }
    }



}
