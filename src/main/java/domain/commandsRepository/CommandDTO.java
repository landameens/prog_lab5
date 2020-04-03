package domain.commandsRepository;

public class CommandDTO {
    public String name;

    @Override
    public String toString() {
        return name + System.lineSeparator();
    }
}
