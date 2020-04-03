package controller.commands;

import controller.response.Response;
import domain.commandsRepository.ICommandsRepository;

import java.util.Map;

public class HistoryCommand extends Command {
    private ICommandsRepository commandsRepository;

    public HistoryCommand(String name,
                          Map<String, String> args,
                          ICommandsRepository commandsRepository) {
        super(name, args);
        this.commandsRepository = commandsRepository;
    }

    @Override
    public Response execute() {
        //TODO: логика здесь, а не в репозитории (см тудушки там)
        String answer = commandsRepository.getHistorytext();

        return getSuccessfullyResponseDTO(answer);
    }
}
