package controller;

import app.query.Query;
import controller.commands.Command;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.commandsRepository.ICommandsRepository;
import domain.commandsRepository.Record;
import domain.exception.CreationException;

/**
 * Class for processing user requests
 */
public class Controller {
    private Interpretator interpretator;
    private ICommandsRepository commandsRepository;

    public Controller(Interpretator interpretator, ICommandsRepository commandsRepository) {
        this.interpretator = interpretator;
        this.commandsRepository = commandsRepository;
    }

    /**
     * Method for creating and executing a user's command
     * @param query
     * @return response to the command
     * @throws CreationException
     */
    public Response handleQuery(Query query) throws CreationException {

        ICommandFactory commandFactory = interpretator.getFactoryInstance(query.getCommandName());

        Command command = commandFactory.createCommand(query.getCommandName(), query.getArguments());

        Record commandDTO = new Record();
        commandDTO.name = query.getCommandName();

        commandsRepository.add(commandDTO);

        return command.execute();
    }
}
