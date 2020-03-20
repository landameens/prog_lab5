package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;

import java.io.IOException;

//TODO: в идеале в APP должно лежать всего 4 пакета STORAGE, CONTROLLER, DOMAIN и VIEW
// ты пилишь VIEW - так что нужно все эти классы засунуть в соответсвующий пакет
public final class App {

    public static void main(String[] args) throws IOException, InternalException {
        Console console = new Console(System.in, System.out);
        try {
            console.start();
        } catch (InputException e ){
            console.showExceptionMessage(e);
        }
    }
}
