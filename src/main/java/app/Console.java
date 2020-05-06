package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;
import app.query.CommandName;
import app.query.CommandType;
import app.query.Query;
import app.query.queryBuilder.QueryBuilder;
import app.query.queryBuilder.QueryBuilderFactory;
import controller.Controller;
import controller.response.Response;
import domain.exception.CreationException;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for input-output, it forms the query and handles it to controller to get response.
 */
public final class Console {
    private static final String START_MESSAGE_STRING = "Добро пожаловать в наше приложение! Для ознакомления с существующими командами, введите команду 'help'." + System.lineSeparator();
    private final String INTERNAL_ERROR_WITH_IO = "Ошибка ввода-вывода. ";

    private BufferedReader reader;
    private BufferedOutputStream writer;
    private Interpretator interpretator;
    private Validator validator;
    private Viewer viewer;
    private Controller controller;

    public Console(InputStream input,
                   OutputStream output,
                   Controller controller){
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new BufferedOutputStream(output);
        this.controller = controller;
        interpretator = new Interpretator();
        validator = new Validator();
        viewer = new Viewer();
    }

    public void start() throws InternalException, InputException {
        writeLine(START_MESSAGE_STRING);
        while (true){
            writeLine(viewer.showInvitationCommandMessage());

            String command = getInputString();
            String[] commandArray = command.split("[\\s]+");



            try {
                validator.validateCommandName(commandArray[0]);
            } catch (InputException e) {
                writeLine(e.getMessage());
                continue;
            }

            CommandName commandName = CommandName.getCommandNameEnum(commandArray[0]);
            CommandType commandType = interpretator.interpretateCommandType(commandName);

            List<String> commandList = new ArrayList<>();
            Collections.addAll(commandList, commandArray);
            try {
                validator.validateNumberOfArguments(commandName, commandList);
            } catch (InputException e) {
                writeLine(e.getMessage());
                continue;
            }

            Map<String, String> arguments = new HashMap<>();
            if (commandType.equals(CommandType.COMPOUND_COMMAND)){
                arguments = getArgumentsOfCompoundCommands(commandName);
            }

            QueryBuilderFactory queryBuilderFactory = new QueryBuilderFactory(validator, interpretator);
            QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(commandType);
            Query query = null;
            try {
                query = queryBuilder.buildQuery(commandName,
                                                      commandList,
                                                      arguments);
            } catch (InputException e) {
                writeLine(e.getMessage());
                continue;
            }

            try {
                Response response = controller.handleQuery(query);
                writeLine(response.getAnswer());

                if (response.getStatus().getCode().equals("601")){
                    System.exit(0);
                }
            } catch (CreationException e) {
                throw new InputException(e.getMessage());
            }
        }
    }

    private String getInputString() throws InputException {
        String command;
        try {
            command = reader.readLine();
        } catch (IOException e) {
            throw new InputException(e.getMessage());
        }
        command = command.trim();

        return command;
    }

    public void writeLine(String text) throws InternalException {
        try {
            byte[] buffer = text.getBytes();
            writer.write(buffer, 0, buffer.length);
            writer.flush();
        } catch (IOException e) {
            throw new InternalException(INTERNAL_ERROR_WITH_IO);
        }
    }

    /**
     * This method gets map of fields and invitation messages for user's input, display the message,
     * reads user's input and validate each field's value until user's input is correct.
     * Returns the map of field names and argument values.
     * @param name
     * @return
     * @throws InternalException
     */
    public Map<String, String> getArgumentsOfCompoundCommands(CommandName name) throws InternalException {
        Map<String,String> mapOfArguments = new HashMap<>();
        Map<String, String> mapForInputArguments = interpretator.getMapForInputArguments(name, viewer);

        for (Map.Entry<String,String> entry : mapForInputArguments.entrySet()){
            String field = entry.getKey();
            String message = entry.getValue();
            writeLine(message);
            Boolean flag = true;
            String correctValue = null;

            while (flag) {
                try {
                    String userInput = reader.readLine();
                    userInput = userInput.trim();
                    validator.validateElementFields(field, userInput);
                    flag = false;
                    correctValue = userInput;
                } catch (InputException e){
                    writeLine(e.getMessage());
                    flag = true;
                } catch (IOException e){
                    throw new InternalException(INTERNAL_ERROR_WITH_IO);
                }
            }
            mapOfArguments.put(field, correctValue);
        }
        return mapOfArguments;
    }
}
