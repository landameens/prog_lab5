package controller.commands;

import controller.responce.Response;

public class Add extends Command {
    private final String name;

    public Add(String name) {
        this.name = name;
    }

    @Override
    Response execute() {
        return null;
    }

    public String getName() {
        return name;
    }
}
