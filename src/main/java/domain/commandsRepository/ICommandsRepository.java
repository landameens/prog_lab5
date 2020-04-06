package domain.commandsRepository;

import java.util.List;

public interface ICommandsRepository {
    void add(Record commandDTO);

    List<Record> getRecords(int quantity);
}
