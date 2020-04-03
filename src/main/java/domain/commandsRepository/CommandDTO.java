package domain.commandsRepository;

//TODO: опять же нейминг, лучше всего взять Record (запись) или синонимы
public class CommandDTO {
    public String name;

    @Override
    public String toString() {
        return name + System.lineSeparator();
    }
}
