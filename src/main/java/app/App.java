package app;

import app.Exceptions.InputException;

import java.io.IOException;

public final class App {
    //TODO: старайся все классы, от которых ничего не должно наследоваться объявлять как final

    public static void main(String[] args) throws IOException, InputException {
        Console console = new Console(System.in, System.out);
        console.start();
    }
}
