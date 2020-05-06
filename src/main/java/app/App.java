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

public final class App {
    private static final String ARGUMENTS_ERROR = "Введено слишком много аргументов, повторите ввод директории," +
            " куда будет сохраняться коллекция и сопутсвующие файлы";

    public static void main(String[] args) throws InternalException, VerifyException, DAOException {
        String pathForAppFiles = null;
        if (args.length > 0) {
            checkInputPath(args);

            pathForAppFiles = args[0];
        }

        IdProducer idProducer = new IdProducer(pathForAppFiles);
        StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
        IStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory, pathForAppFiles);

        ICommandsRepository commandsRepository = new HistoryRepository();
        Interpretator interpretator = new Interpretator(studyGroupRepository, commandsRepository);
        Controller controller = new Controller(interpretator, commandsRepository);
        Console console = new Console(System.in, System.out, controller);

        try {
            console.start();
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkInputPath(String[] args) {
        if (args.length > 1) {
            System.err.println(ARGUMENTS_ERROR);
            System.exit(1);
        }
    }
}
