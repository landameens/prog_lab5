package app;

import app.Exceptions.InputException;
import app.Exceptions.InternalException;


import controller.Controller;
import controller.Interpretator;
import domain.commandsRepository.HistoryRepository;
import domain.commandsRepository.ICommandsRepository;
import domain.exception.VerifyException;
import domain.studyGroupFactory.StudyGroupFactory;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.exception.DAOException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public final class App {
    private static final String LACK_OF_ARGUMENTS_ERROR = "Неверный путь. Введите в формате {absolute/relative} {path to the file}";

    public static void main(String[] args) throws InternalException, VerifyException, DAOException {
        ClassLoader classLoader = App.class.getClassLoader();
        String path = "";

        if (args.length > 0) {
            checkInputPath(args);

            PathModifiers modifier = PathModifiers.getPathModifiers(args[0]);
            File file = new File(path);
            if (!file.exists()){
                System.err.println("Такого файла не существует.");
                System.exit(1);
            }

            if(!file.canExecute()){
                System.err.println("Пожалуйста, предоставьте права доступа.");
                System.exit(1);
            }

            if (modifier.equals(PathModifiers.ABSOLUTE)) {
                path = args[1];
            }

            if (modifier.equals(PathModifiers.RELATIVE)) {
                URL fileURL = classLoader.getResource(args[1]);
                path = fileURL.getFile();
            }

            if (!modifier.equals(PathModifiers.RELATIVE) && !modifier.equals(PathModifiers.ABSOLUTE)) {
                System.err.println(LACK_OF_ARGUMENTS_ERROR);
            }

        }

        String pathToGroups = path;
        String pathToInfo = path;
        String pathToIdProducer = path;
        String pathToScript = path;

        if (!path.equals("")) {
            pathToGroups = path + "/studyGroups";
            pathToInfo = path + "/info";
            pathToIdProducer = path + "idProducer";
        }

        IdProducer idProducer = new IdProducer(new ArrayList<Long>(), pathToIdProducer);
        StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
        IStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory, pathToGroups, pathToInfo);
        ICommandsRepository commandsRepository = new HistoryRepository();
        Interpretator interpretator = new Interpretator(studyGroupRepository, commandsRepository, path);
        Controller controller = new Controller(interpretator, commandsRepository);

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
