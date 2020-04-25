package controller.commands;

import controller.commands.factory.*;
import controller.commands.scripts.RecursionChecker;
import domain.commandsRepository.ICommandsRepository;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for an assign to a command to each factory
 */
public class Interpretator {
    private final ICommandFactory simpleCommandsFactory;
    private final ICommandFactory studyGroupRepositoryCommandFactory;
    private final ICommandFactory commandRepositoryFactory;
    private final ICommandFactory scriptFactory;

    public Interpretator(IStudyGroupRepository studyGroupRepository,
                         ICommandsRepository historyRepository,
                         RecursionChecker recursionChecker,
                         String path) {
        simpleCommandsFactory = new SimpleCommandsFactory();
        studyGroupRepositoryCommandFactory = new StudyGroupRepositoryCommandFactory(studyGroupRepository);
        commandRepositoryFactory = new HistoryRepositoryCommandFactory(historyRepository);
        scriptFactory = new ScriptCommandFactory(studyGroupRepository, historyRepository, recursionChecker, path);
    }

    private final Map<String, Class<? extends ICommandFactory>> commandFactoryMap = new HashMap<String, Class<? extends ICommandFactory>>(){
        {
            put("help", SimpleCommandsFactory.class);
            put("show", StudyGroupRepositoryCommandFactory.class);
            put("add", StudyGroupRepositoryCommandFactory.class);
            put("remove_by_id", StudyGroupRepositoryCommandFactory.class);
            put("clear", StudyGroupRepositoryCommandFactory.class);
            put("update", StudyGroupRepositoryCommandFactory.class);
            put("add_if_min", StudyGroupRepositoryCommandFactory.class);
            put("save", StudyGroupRepositoryCommandFactory.class);
            put("remove_lower", StudyGroupRepositoryCommandFactory.class);
            put("exit", SimpleCommandsFactory.class);
            put("filter_by_should_be_expelled", StudyGroupRepositoryCommandFactory.class);
            put("filter_less_than_should_be_expelled", StudyGroupRepositoryCommandFactory.class);
            put("count_by_group_admin", StudyGroupRepositoryCommandFactory.class);
            put("info", StudyGroupRepositoryCommandFactory.class);
            put("execute_script", ScriptCommandFactory.class);
            put("history", HistoryRepositoryCommandFactory.class);
        }
    };

    /**
     * Method to get an instance of the factory for the name of the command
     * @param name
     * @return factory corresponding to the command
     */
    public ICommandFactory getFactoryInstance(String name){
        Class<? extends ICommandFactory> clazz = commandFactoryMap.get(name);

        if (clazz.equals(simpleCommandsFactory.getClass())) {
            return simpleCommandsFactory;
        }

        if (clazz.equals(studyGroupRepositoryCommandFactory.getClass())) {
            return studyGroupRepositoryCommandFactory;
        }

        if(clazz.equals(commandRepositoryFactory.getClass())){
            return commandRepositoryFactory;
        }

        if(clazz.equals(scriptFactory.getClass())){
            return scriptFactory;
        }

        return null;
    }
}
