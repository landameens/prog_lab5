package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;
import app.query.Query;
import app.query.queryBuilder.QueryBuilder;
import app.query.queryBuilder.QueryBuilderFactory;
import controller.Controller;
import domain.exception.CreationException;
import domain.exception.StudyGroupRepositoryException;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for input-output, it forms the query and handles it to controller to get response.
 */
public final class Console {
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

    public void start() throws InputException, IOException, InternalException {
        while (true){
       //     writeLine("Write command, please: \n");
            String command = reader.readLine();
            command = command.trim();
            String[] commandArray = command.split("[\\s]+");
       //     writeLine("value =" + Arrays.toString(commandArray));
       //     writeLine(commandArray[0]);
       //     validator.validateCommandName(commandArray[0]);

            CommandName commandName = interpretator.interpretateCommandName(commandArray[0]);
       //     writeLine("CommandName = " + commandName.getName());
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
            Query query = queryBuilder.buildQuery(commandName,
                                                  commandList,
                                                  arguments);

            try {
                writeLine(controller.handleQuery(query).getAnswer());
            } catch (CreationException | StudyGroupRepositoryException e) {
                throw new InputException(e.getMessage());
            }
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
                    userInput = userInput.trim();
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
