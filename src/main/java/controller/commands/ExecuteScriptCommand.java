package controller.commands;

import app.*;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.commandsRepository.HistoryRepository;
import domain.commandsRepository.ICommandsRepository;
import domain.commandsRepository.Record;
import domain.exception.CreationException;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.scriptDAO.IScriptDAO;
import storage.scriptDAO.ScriptDAO;
import storage.exception.RecursionExeption;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ExecuteScriptCommand extends StudyGroupRepositoryCommand {
    private Interpretator interpretator;
    private app.Interpretator interpretatorToType;
    private ICommandsRepository commandsRepository;
    private Viewer viewer;

    public ExecuteScriptCommand(String type,
                                Map<String, String> args,
                                IStudyGroupRepository studyGroupRepository){
        super(type, args, studyGroupRepository);
        interpretator = new Interpretator(studyGroupRepository);
        interpretatorToType = new app.Interpretator();
        this.commandsRepository = new HistoryRepository();
        viewer = new Viewer();
    }

    @Override
    public Response execute() {
        ClassLoader classLoader = TreeSetStudyGroupRepository.class.getClassLoader();
        URL url = classLoader.getResource("script");
        String path = url.getFile() + "//" + args.get("file_name");

        IScriptDAO scriptDAO = new ScriptDAO(path);
        try {
            List<String> script = scriptDAO.getScript();

            return executeScript(script);
        } catch (IOException | RecursionExeption e) {
            return getBadRequestResponseDTO(e.getMessage());
        }

    }

    private Response executeScript(List<String> script) {
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

                Command command = createCommand(commandName, iterator);

                addToHistory(commandName);

                String thisCommandAnswer = command.execute().getAnswer();
                answer = answer.append(thisCommandAnswer);

            } catch (CreationException e) {
                return getBadRequestResponseDTO(e.getMessage());
            }
        }
        return getSuccessfullyResponseDTO(answer.toString());
    }

    private Command createCommand(String commandName, Iterator<String> iterator) throws CreationException {
        ICommandFactory commandFactory = interpretator.getFactoryInstance(commandName);
        Map<String, String> args = getArguments(commandName, iterator);

        return commandFactory.createCommand(commandName, args);
    }

    private void addToHistory(String commandName) {
        Record commandDTO = new Record();
        commandDTO.name = commandName;
        commandsRepository.add(commandDTO);
    }

    private Map<String, String> getArguments(String commandName, Iterator<String> iterator) {
        Map<String, String> args = new HashMap<>();

        CommandType commandType = interpretatorToType.interpretateCommandType(CommandName.getCommandNameEnum(commandName));

        if (commandType.equals(CommandType.COMPOUND_COMMAND)){
            args = getArgumentsForCompoundCommand(commandName, iterator);
        }

        return args;
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
