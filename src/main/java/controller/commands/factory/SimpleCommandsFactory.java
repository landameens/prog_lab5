package controller.commands.factory;

import controller.commands.Command;
import controller.commands.HelpCommand;
import domain.exception.CreationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SimpleCommandsFactory implements ICommandFactory {

    private Map<String, Class<? extends Command>> classMap = new HashMap<String, Class<? extends Command>>() {
        {
            put("help", HelpCommand.class);
        }
    };

    @Override
    public Command createCommand(String commandName,
                                 String commandType,
                                 Map<String, String> arguments) throws CreationException {
        Class<? extends Command> clazz = classMap.get(commandName);
        try {
            Constructor<? extends Command> constructor = clazz.getConstructor(String.class, Map.class);
            return constructor.newInstance(commandType, arguments);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new CreationException(e);
        }
    }
}
