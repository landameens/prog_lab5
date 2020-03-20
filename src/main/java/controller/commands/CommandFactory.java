package controller.commands;

import java.util.Map;

public class CommandFactory {
    private final String name;
    private final String type;
    private final Map<String, String> ars;

    public CommandFactory(String name,
                          String type,
                          Map<String, String> args) {
        this.name = name;
        this.type = type;
        this.ars = args;
    }

    public Command createCommand(){

    }
}
