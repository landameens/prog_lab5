package controller;

import controller.commands.factory.ICommandFactory;
import controller.commands.factory.SimpleCommandsFactory;
import controller.commands.factory.StudyGroupRepositoryCommandFactory;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.HashMap;
import java.util.Map;

public class Interpretator {

    private ICommandFactory simpleCommandsFactory;
    private ICommandFactory studyGroupRepositoryCommandFactory;

    public Interpretator(IStudyGroupRepository studyGroupRepository) {
        simpleCommandsFactory = new SimpleCommandsFactory();
        studyGroupRepositoryCommandFactory = new StudyGroupRepositoryCommandFactory(studyGroupRepository);
    }

    //Todo: сюда дабавалять классы и их фабрику
    private Map<String, Class<? extends ICommandFactory>> commandFactoryMap = new HashMap<String, Class<? extends ICommandFactory>>(){
        {
            put("help", SimpleCommandsFactory.class);
            put("show", StudyGroupRepositoryCommandFactory.class);
            put("add", StudyGroupRepositoryCommandFactory.class);
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

        return null;
    }
}
