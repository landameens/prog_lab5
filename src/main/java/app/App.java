package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;

import java.io.IOException;

public final class App {
    //TODO: старайся все классы, от которых ничего не должно наследоваться объявлять как final

    public static void main(String[] args) throws IOException, InternalException {
        Console console = new Console(System.in, System.out);
        try {
            console.start();
        } catch (InputException e ){
            console.showExceptionMessage(e);
        }
    }
}
