package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;
import app.query.Query;
import app.query.queryBuilder.QueryBuilder;
import app.query.queryBuilder.QueryBuilderFactory;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for input-output, it forms the query and handles it to controller to get response.
 */
public final class Console {
    private BufferedReader reader;
    private BufferedOutputStream writer;
    private Interpretator interpretator;
    private Validator validator;
    private Viewer viewer;

    private final String INTERNAL_ERROR_WITH_IO = "Ошибка ввода-вывода. ";

    public Console(InputStream input,
                   OutputStream output){
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new BufferedOutputStream(output);
    }

    public void start() throws InputException, IOException, InternalException {
        while (true){
            String command = reader.readLine();
            command = command.trim();
            String[] commandArray = command.split("[\\s]+");
            validator.validateCommandName(commandArray[1]);
            //TODO: Данный метод можно спокойно вынести из интерпретатора в класс-енам, в методы (например) public static getInstance(String name) {}
            
            CommandName commandName = interpretator.interpretableCommandName(commandArray[1]);
            CommandType commandType = interpretator.interpretateCommandType(commandName);
            List<String> commandList = new ArrayList<>();

            Collections.addAll(commandList, commandArray);
            validator.validateNumberOfArguments(commandName, commandType, commandList);

            Map<String, String> arguments = new HashMap<>();

            if (commandType.equals(CommandType.COMPOUND_COMMAND)){
                arguments = getArgumentsOfCompoundCommands(commandName);
            }

            QueryBuilderFactory queryBuilderFactory = new QueryBuilderFactory();
            QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(commandType);
            Query query = queryBuilder.buildQuery(commandName.getName(),
                    commandType.getName(),
                    commandList,
                    arguments);
            //TODO: создать Controller, передавать туды запрос и получать ответ и т.д. и т.р.........

        }
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
                    userInput.trim();
                    //number of args?
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
