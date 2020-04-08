package controller.commands;

import app.*;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.commandsRepository.HistoryRepository;
import domain.commandsRepository.ICommandsRepository;
import domain.commandsRepository.Record;
import domain.exception.CreationException;
import domain.studyGroupRepository.IStudyGroupRepository;
import storage.IScriptDAO;
import storage.ScriptDAO;
import storage.exception.RecursionExeption;

import java.io.IOException;
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
        IScriptDAO scriptDAO = new ScriptDAO(args.get("file_name"));
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

        //todo нечитаемо, рефакторинг
        while (iterator.hasNext()){
            String line = iterator.next();

            if (line.isEmpty()){
                continue;
            }

            line = line.trim();
            String[] commandArray = line.split("[\\s]+");

            String commandName = commandArray[0];
            ICommandFactory commandFactory = interpretator.getFactoryInstance(commandName);

            Map<String, String> args = new HashMap<>();
            CommandType commandType = interpretatorToType.interpretateCommandType(CommandName.getCommandNameEnum(commandName));

            if (commandType.equals(CommandType.COMPOUND_COMMAND)){
                args = getArguments(commandName, iterator);
            }

            try {
                Command command = commandFactory.createCommand(commandName, args);

                Record commandDTO = new Record();
                commandDTO.name = commandName;
                commandsRepository.add(commandDTO);

                answer = answer.append(command.execute().getAnswer());

            } catch (CreationException e) {
                return getBadRequestResponseDTO(e.getMessage());
            }

        }

        return getSuccessfullyResponseDTO(answer.toString());

    }

    private Map<String, String> getArguments(String commandName, Iterator<String> iterator) {
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
