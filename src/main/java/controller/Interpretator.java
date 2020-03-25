package controller;

import controller.commands.factory.ICommandFactory;
import controller.commands.factory.SimpleCommandsFactory;
import controller.commands.factory.StudyGroupRepositoryCommandFactory;

import java.util.HashMap;
import java.util.Map;

public class Interpretator {

    private ICommandFactory simpleCommandsFactory;
    private ICommandFactory studyGroupRepositoryCommandFactory;

    public Interpretator() {
        simpleCommandsFactory = new SimpleCommandsFactory();
        studyGroupRepositoryCommandFactory = new StudyGroupRepositoryCommandFactory();
    }

    private Map<String, Class<? extends ICommandFactory>> commandFactoryMap = new HashMap<String, Class<? extends ICommandFactory>>(){
        {
            put("help", SimpleCommandsFactory.class);
        }
    };


    public ICommandFactory getFactoryInstance(String name){
        Class<? extends ICommandFactory> clazz = commandFactoryMap.get(name);

        if (clazz.equals(simpleCommandsFactory.getClass())) {
            return simpleCommandsFactory;
        }

        return studyGroupRepositoryCommandFactory;
    }
}
