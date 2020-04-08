package controller.commands;

import controller.commands.factory.HistoryRepositoryCommandFactory;
import controller.commands.factory.ICommandFactory;
import controller.commands.factory.SimpleCommandsFactory;
import controller.commands.factory.StudyGroupRepositoryCommandFactory;
import domain.commandsRepository.HistoryRepository;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.HashMap;
import java.util.Map;

public class Interpretator {
    private ICommandFactory simpleCommandsFactory;
    private ICommandFactory studyGroupRepositoryCommandFactory;
    private ICommandFactory commandRepositoryFactory;

    public Interpretator(IStudyGroupRepository studyGroupRepository) {
        simpleCommandsFactory = new SimpleCommandsFactory();
        studyGroupRepositoryCommandFactory = new StudyGroupRepositoryCommandFactory(studyGroupRepository);
        commandRepositoryFactory = new HistoryRepositoryCommandFactory(new HistoryRepository() {
        });
    }

    private Map<String, Class<? extends ICommandFactory>> commandFactoryMap = new HashMap<String, Class<? extends ICommandFactory>>(){
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
            put("execute_script", StudyGroupRepositoryCommandFactory.class);
            put("history", HistoryRepositoryCommandFactory.class);
        }
    };


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

        return null;
    }
}
