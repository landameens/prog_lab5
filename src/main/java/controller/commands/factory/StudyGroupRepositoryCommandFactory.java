package controller.commands.factory;

import controller.commands.Command;
import controller.commands.factory.ICommandFactory;

import java.util.Map;

public class StudyGroupRepositoryCommandFactory implements ICommandFactory {
    @Override
    public Command createCommand(String commandName, String commandType, Map<String, String> arguments) {
        return null;
    }
}
