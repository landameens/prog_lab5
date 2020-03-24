package controller.commands.factory;

import controller.commands.Command;
import controller.commands.TestCommand;
import controller.commands.factory.ICommandFactory;
import domain.exception.CreationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SimpleCommands implements ICommandFactory {

    private Map<String, Class<? extends Command>> map = new HashMap<String, Class<? extends Command>>() {
        {
            put("help", TestCommand.class);
        }
    };

    @Override
    public Command createCommand(String commandName,
                                 String commandType,
                                 Map<String, String> arguments) throws CreationException {
        Class<? extends Command> clazz = map.get(commandName);
        try {
            Constructor<? extends Command> constructor = clazz.getConstructor(String.class, Map.class);
            return constructor.newInstance(commandType, arguments);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new CreationException(e);
        }
    }
}
