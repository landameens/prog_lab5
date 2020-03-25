package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;

import java.io.IOException;

import controller.Controller;
import domain.exception.StudyGroupRepositoryException;
import domain.exception.VerifyException;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;
import domain.studyGroupFactory.StudyGroupFactory;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.exception.DAOException;

import java.net.URL;

public final class App {
    private static final String LACK_OF_ARGUMENTS_ERROR = "Неверный путь. Введите в формате {absolute/relative} {path to the file}";

    public static void main(String[] args) throws IOException, InternalException, VerifyException, DAOException, StudyGroupRepositoryException {
        ClassLoader classLoader = App.class.getClassLoader();
        String path = "C:\\Users\\user\\Desktop\\Programming\\prog_lab5\\src\\main\\resources";

        if (args.length > 0) {
            checkInputPath(args);

            PathModifiers modifier = PathModifiers.getPathModifiers(args[1]);

            if (modifier.equals(PathModifiers.ABSOLUTE)) {
                path = args[2];
            }

            if (modifier.equals(PathModifiers.RELATIVE)) {
                URL fileURL = classLoader.getResource(args[2]);
                path = fileURL.getFile();
            }

            if (modifier == null) {
                System.err.println(LACK_OF_ARGUMENTS_ERROR);
            }
        }

        Controller controller = new Controller();

        Console console = new Console(System.in, System.out, controller);
        try {
            console.start();
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkInputPath(String[] args) {
        if (args.length != 2) {
            System.err.println(LACK_OF_ARGUMENTS_ERROR);
            System.exit(1);
        }
    }
}
