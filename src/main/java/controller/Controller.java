package controller;

import app.query.Query;
import controller.commands.Command;
import controller.commands.factory.ICommandFactory;
import controller.response.Response;
import domain.commandsRepository.CommandDTO;
import domain.commandsRepository.ICommandsRepository;
import domain.exception.CreationException;

public class Controller {
    private Interpretator interpretator;
    private ICommandsRepository commandsRepository;

    public Controller(Interpretator interpretator, ICommandsRepository commandsRepository) {
        this.interpretator = interpretator;
        this.commandsRepository = commandsRepository;
    }

    public Response handleQuery(Query query) throws CreationException {

        ICommandFactory commandFactory = interpretator.getFactoryInstance(query.getCommandName());

        Command command = commandFactory.createCommand(query.getCommandName(), query.getArguments());

        CommandDTO commandDTO = new CommandDTO();
        commandDTO.name = query.getCommandName();

        commandsRepository.add(commandDTO);

        return command.execute();
    }
}
