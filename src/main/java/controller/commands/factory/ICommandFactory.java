package controller.commands.factory;

import controller.commands.Command;
import domain.exception.CreationException;

import java.util.Map;

public interface ICommandFactory {
    Command createCommand(String commandName,
                          String commandType,
                          Map<String, String> arguments) throws CreationException;
}
