package controller.commands;

import controller.commands.factory.ICommandFactory;
import controller.commands.factory.SimpleCommands;
import controller.commands.factory.StudyGroupRepositoryCommandFactory;

import java.util.HashMap;
import java.util.Map;

public class Interpretator {

    private ICommandFactory otherCommandsFactory;
    private ICommandFactory studyGroupRepositoryCommandFactory;

    public Interpretator() {
        otherCommandsFactory = new SimpleCommands();
        studyGroupRepositoryCommandFactory = new StudyGroupRepositoryCommandFactory();
    }

    private Map<String, Class<? extends ICommandFactory>> map = new HashMap<String, Class<? extends ICommandFactory>>(){
        {
            put("help", SimpleCommands.class);
        }
    };


    public ICommandFactory getFactoryInstance(String name){
        Class<? extends ICommandFactory> clazz = map.get(name);

        if (clazz.equals(otherCommandsFactory.getClass())) {
            return otherCommandsFactory;
        }

        return studyGroupRepositoryCommandFactory;
    }
}
