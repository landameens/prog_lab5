package app;

import app.Exceptions.InputException;
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

    public static void main(String[] args) throws VerifyException, DAOException {
        String pathForAppFiles = null;
        if (args.length > 0) {
            checkInputPath(args);

            pathForAppFiles = args[0];

            File file = new File(pathForAppFiles);
            if (!file.exists()){
                System.err.println("Такого файла не существует. Проверьте наличие такого файла и повторите попытку.");
                System.exit(1);
            }

            if(!file.canExecute()){
                System.err.println("Недостаточно прав. Пожалуйста, предоставьте права доступа и повторите попытку.");
                System.exit(1);
            }
        }

        Console console = null;
        try {
            IdProducer idProducer = new IdProducer(pathForAppFiles);
            StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
            IStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory, pathForAppFiles);

            ICommandsRepository commandsRepository = new HistoryRepository();
            Interpretator interpretator = new Interpretator(studyGroupRepository, commandsRepository);
            Controller controller = new Controller(interpretator, commandsRepository);
            console = new Console(System.in, System.out, controller);
        } catch (DAOException | VerifyException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        try {
            console.start();
        } catch (InputException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void checkInputPath(String[] args) {
        if (args.length > 1) {
            System.err.println(ARGUMENTS_ERROR);
            System.exit(1);
        }
    }
}
