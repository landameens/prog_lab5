package domain.commandsRepository;

import java.util.List;

public interface ICommandsRepository {
    void add(CommandDTO commandDTO);

    List<CommandDTO> getHistory();

    String getHistorytext();
}
