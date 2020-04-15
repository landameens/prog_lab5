package controller.commands;

import app.*;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.commandsRepository.ICommandsRepository;
import domain.commandsRepository.Record;
import domain.exception.CreationException;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.scriptDAO.IScriptDAO;
import storage.scriptDAO.ScriptDAO;
import storage.exception.RecursionExeption;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ExecuteScriptCommand extends Command {
    private Interpretator interpretator;
    private app.Interpretator interpretatorToType;
    private Viewer viewer;
    private ICommandsRepository history;
    private IStudyGroupRepository studyGroupRepository;

    public ExecuteScriptCommand(String type,
                                Map<String, String> args,
                                IStudyGroupRepository studyGroupRepository,
                                ICommandsRepository commandsRepository){
        super(type, args);
        this.history = commandsRepository;
        this.studyGroupRepository = studyGroupRepository;
        interpretator = new Interpretator(studyGroupRepository, commandsRepository);
        interpretatorToType = new app.Interpretator();
        viewer = new Viewer();
    }

    @Override
    public Response execute() {
        ClassLoader classLoader = TreeSetStudyGroupRepository.class.getClassLoader();
        URL url = classLoader.getResource("script");
        String path = url.getFile() + "/" + args.get("file_name");

        IScriptDAO scriptDAO = new ScriptDAO(path);
        try {
            List<String> script = scriptDAO.getScript();

            return executeScript(script);
        } catch (IOException | RecursionExeption e) {
            return getBadRequestResponseDTO(e.getMessage());
        }

    }

    private Response executeScript(List<String> script){
        Iterator<String> iterator = script.iterator();

        StringBuilder answer = new StringBuilder();

        while (iterator.hasNext()){
            String line = iterator.next();

            if (line.isEmpty()){
                continue;
            }

            try {
                String[] commandArray = getCommandArray(line);
                String commandName = commandArray[0];

                Command command = createCommand(commandArray, iterator);

                addToHistory(commandName);

                String thisCommandAnswer = command.execute().getAnswer();
                answer = answer.append(thisCommandAnswer);

            } catch (CreationException e) {
                return getBadRequestResponseDTO(e.getMessage());
            }
        }
        return getSuccessfullyResponseDTO(answer.toString());
    }

    private Command createCommand(String[] commandArray, Iterator<String> iterator) throws CreationException {
        String commandName = commandArray[0];
        ICommandFactory commandFactory = interpretator.getFactoryInstance(commandName);
        Map<String, String> args = getArguments(commandArray, iterator);

        return commandFactory.createCommand(commandName, args);
    }

    private void addToHistory(String commandName) {
        Record commandDTO = new Record();
        commandDTO.name = commandName;
        history.add(commandDTO);
    }

    private Map<String, String> getArguments(String[] commandArray, Iterator<String> iterator) {
        Map<String, String> args = new HashMap<>();

        CommandType commandType = interpretatorToType.interpretateCommandType(CommandName.getCommandNameEnum(commandArray[0]));

        if (commandType.equals(CommandType.COMPOUND_COMMAND)){
            args = getArgumentsForCompoundCommand(commandArray[0], iterator);
        }

        if (commandType.equals(CommandType.SIMPLE_COMMAND)){
            args = getArgumentsForSimpleCommand(commandArray);
        }

        return args;
    }

    private Map<String, String> getArgumentsForSimpleCommand(String[] commandArray) {
        CommandName commandName = CommandName.getCommandNameEnum(commandArray[0]);
        List<String> commandList = new ArrayList<>();
        Collections.addAll(commandList, commandArray);
        return interpretatorToType.interpretateSimpleCommandArguments(commandName, commandList);
    }

    private String[] getCommandArray(String line) {
        line = line.trim();
        return line.split("[\\s]+");
    }

    private Map<String, String> getArgumentsForCompoundCommand(String commandName, Iterator<String> iterator) {
        Map<String,String> returnableArgs = new HashMap<>();
        Map<String, String> mapForInputArguments = interpretatorToType.getMapForInputArguments(CommandName.getCommandNameEnum(commandName), viewer);

        for (Map.Entry<String,String> entry : mapForInputArguments.entrySet()) {
            String field = entry.getKey();

            String lineOfArgument = iterator.next();
            lineOfArgument = lineOfArgument.trim();

            returnableArgs.put(field, lineOfArgument);
        }

        return returnableArgs;
    }

}
