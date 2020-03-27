package controller.commands.factory;

import controller.commands.AddCommand;
import controller.commands.Command;
import controller.commands.ShowCommand;
import domain.exception.CreationException;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class StudyGroupRepositoryCommandFactory implements ICommandFactory {
    private IStudyGroupRepository studyGroupRepository;

    public StudyGroupRepositoryCommandFactory(IStudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }


    //todo: сюда имя класса и его класс
    private Map<String, Class<? extends Command>> classMap = new HashMap<String, Class<? extends Command>>() {
        {
            put("show", ShowCommand.class);
            put("add", AddCommand.class);
        }
    };


    @Override
    public Command createCommand(String commandName,
                                 Map<String, String> arguments) throws CreationException {
        Class<? extends Command> clazz = classMap.get(commandName);

        try {
            Constructor<? extends Command> constructor = clazz.getConstructor(String.class, Map.class, IStudyGroupRepository.class);
            return constructor.newInstance(commandName, arguments, studyGroupRepository);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new CreationException(e);
        }
    }
}
