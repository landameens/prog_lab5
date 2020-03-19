package app;

import app.Exceptions.InputException;
import app.QueryBuilder.QueryBuilder;
import app.QueryBuilder.QueryBuilderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Console {
    private BufferedReader reader;
    private BufferedOutputStream writer;
    private Interpretator interpretator;
    private Validator validator;

    public Console(InputStream input, OutputStream output){
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new BufferedOutputStream(output);
    }

    public void start() throws IOException, InputException {
        while (true){
            String command = reader.readLine();
            command = command.trim();
            String[] commandArray = command.split("[\\s]+");
            validator.validateCommandName(commandArray[1]);
            //TODO: Данный метод можно спокойно вынести из интерпретатора в класс-енам, в методы (например) public static getInstance(String name) {}
            // Пример можно посмотреть у Нади

            CommandName commandName = interpretator.interpretateCommandName(commandArray[1]);
            CommandType commandType = interpretator.interpretateCommandType(commandName);
            List<String> commandList = new ArrayList<>();
            Collections.addAll(commandList, commandArray);
            validator.validateNumberOfArguments(commandName, commandType, commandList);

            QueryBuilderFactory queryBuilderFactory = new QueryBuilderFactory();
            QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(commandType);
            Query query = queryBuilder.buildQuery(commandName, commandType, commandList);


        }
    }
}
