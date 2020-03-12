package app;

import app.Exceptions.InputException;

import java.io.*;

public class Console {
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
            //some code must be here
            String command = reader.readLine();
            command = command.trim();
            String[] recCommand = command.split("[\\s]+");
            validator.validateCommandName(recCommand[1]);
            //TODO: Данный метод можно спокойно вынести из интерпретатора в класс-енам, в методы (например) public static getInstance(String name) {} Пример можно посмотреть у Нади
            CommandName commandName = interpretator.interpretateCommandName(recCommand[1]);
            CommandType commandType = interpretator.interpretateCommandType(commandName);


        }
    }
}
