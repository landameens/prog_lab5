package app;

import app.Exceptions.InputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.CommandName.*;

/**
 * This class is responsible for validating user's input, command name, number of arguments, type of arguments, and others.
 */
public final class Validator {

    private final String UNKNOWN_COMMAND = "Ошибка: Неизвестная команда.";
    private final String WRONG_NUMBER_OF_ARGUMENTS = "Ошибка: Неверное количество аргументов. ";
    private final String NULL_ARGUMENT = "Ошибка: Аргумент не может быть null. ";
    private final String NOT_INTEGER_ARGUMENT = "Ошибка: Аргумент не целочисленный. ";
    private final String NOT_FLOAT_ARGUMENT = "Ошибка: Аргумент не вещественный.";
    private final String NEGATIVE_ARGUMENT = "Ошибка: Аргумент не является положительным числом. ";
    private final String MORE_THEN_28 = "Ошибка: Аргумент превысил максимальное знаение 28. ";
    private final String NOT_LONG_ARGUMENT = "Ошибка: Аргумент не целочисленный. ";
    private final String NOT_ENUM_CONSTANT = "Ошибка: Аргумент не является константой Enum. ";
    private final String EMPTY_STRING = "Ошибка: Строка не может быть пустой. ";

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

    public void validateSimpleArgumentsOfCompoundCommand(CommandName name, List<String> commandList) throws InputException {
        switch (name){
            case UPDATE:
                if (commandList.get(1) == null) { throw new InputException(NULL_ARGUMENT); }
                try {
                    int id = Integer.parseInt(commandList.get(1));
                    if (id <= 0) {throw new InputException(NEGATIVE_ARGUMENT); }
                } catch (NumberFormatException e){
                    throw new InputException(NOT_INTEGER_ARGUMENT);
                }
                break;
        }
    }

    public void validateElementFields (String field, String value) throws InputException {
        switch (field){
            case "StudyGroupName":
                checkStudyGroupName(value);
                break;
            case "xCoordinate":
                checkXCoordinate(value);
                break;
            case "yCoordinate":
                checkYCoordinate(value);
                break;
            case "studentsCount":
                checkStudentCount(value);
                break;
            case "shouldBeExpelled":
                checkShouldBeExpelled(value);
                break;
            case "formOfEducation":
                checkFormOfEducation(value);
                break;
            case "semesterEnum":
                checkSemesterEnum(value);
                break;
            case "groupAdminName":
                checkGroupAdminName(value);
                break;
            case "groupAdminHeight":
                checkGroupAdminHeight(value);
                break;
            case "groupAdminPassportID":
                checkGroupAdminPassportID(value);
                break;
            case "groupAdminNationality":
                checkGroupAdminNationality(value);
                break;
        }
    }

    public void checkStudyGroupName(String name) throws InputException {
        if (name == null) { throw new InputException(NULL_ARGUMENT); }
    }

    public void checkXCoordinate (String xCoordinate) throws InputException {
        try {
            if (xCoordinate == null) { throw new InputException(NULL_ARGUMENT);}
            float x = Float.parseFloat(xCoordinate);
            if (x>28) { throw new InputException(MORE_THEN_28); }
        } catch (NumberFormatException e){
            throw new InputException(NOT_FLOAT_ARGUMENT);
        }
    }

    public void checkYCoordinate (String yCoordinate) throws InputException {
        try{
            long y = Long.parseLong(yCoordinate);
        } catch (NumberFormatException e){
            throw new InputException(NOT_LONG_ARGUMENT);
        }
    }

    public void checkStudentCount (String studentCount) throws InputException {
        try {
            int students = Integer.parseInt(studentCount);
            if (students<=0) {throw new InputException(NEGATIVE_ARGUMENT); }
        } catch (NumberFormatException e){
            throw new InputException(NOT_INTEGER_ARGUMENT);
        }
    }

    public void checkShouldBeExpelled (String shouldBeExp) throws InputException {
        try {
            int shouldBeExpelled = Integer.parseInt(shouldBeExp);
            if (shouldBeExpelled <= 0) {throw new InputException(NEGATIVE_ARGUMENT); }
        } catch (NumberFormatException e){
            throw new InputException(NOT_INTEGER_ARGUMENT);
        }
    }

    public void checkFormOfEducation (String value) throws InputException {
        if ( !(value.equals("DISTANCE_EDUCATION") || value.equals("FULL_TIME_EDUCATION") || value.equals("EVENING_CLASSES")) ){
            throw new InputException(NOT_ENUM_CONSTANT);
        }
    }

    public void checkSemesterEnum (String value) throws InputException {
        if ( !(value.equals("FIRST") || value.equals("SECOND") || value.equals("FOURTH") || value.equals("EIGHTH") ) ){
            throw new InputException(NOT_ENUM_CONSTANT);
        }
    }

    public void checkGroupAdminName (String name) throws InputException {
        if (name == null) {throw new InputException(NULL_ARGUMENT);}
        if (name.equals("")) {throw new InputException(EMPTY_STRING); }
    }

    public void checkGroupAdminHeight(String value) throws InputException {
        try{
            long height = Long.parseLong(value);
            if (height<=0) {throw new InputException(NEGATIVE_ARGUMENT);}
        } catch (NumberFormatException e){
            throw new InputException(NOT_LONG_ARGUMENT);
        }
    }

    public void checkGroupAdminPassportID (String value) throws InputException {
        if (value == null) {throw new InputException(NULL_ARGUMENT);}
        if (value.equals("")) {throw new InputException(EMPTY_STRING); }
    }

    public void checkGroupAdminNationality  (String value) throws InputException {
        if ( !(value.equals("UNITED_KINGDOM") || value.equals("GERMANY") || value.equals("VATICAN") || value.equals("SOUTH_KOREA") || value.equals("JAPAN") ) ){
            throw new InputException(NOT_ENUM_CONSTANT);
        }
    }


}