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
    private static final String ARGUMENTS_ERROR = "Большое количество аргументов. Пожалуйста, введите имя файла с начальными данными. ";

    public static void main(String[] args) throws InternalException, VerifyException, DAOException {
        String path = "";
        if (args.length > 0) {
            checkInputPath(args);

            ClassLoader classLoader = App.class.getClassLoader();
            String [] parts = classLoader.getResource("studyGroups").getFile().split("/");

            for(int i = parts.length - 1; i < 0; i--){
                path = path + "\\" + parts[i];
            }
            path = path + args[0];

            if (args[0].matches("\\.\\./.")){
                path = "";
                for(int i = parts.length - 2; i < 0; i--){
                    path = path + "\\" + parts[i];
                }
                path = path + args[0];
            }
            File file = new File(path);
            if (!file.exists()){
                System.err.println("Такого файла не существует. Проверьте наличие такого файла и повторите попытку.");
                System.exit(1);
            }

            if(!file.canExecute()){
                System.err.println("Недостаточно прав. Пожалуйста, предоставьте права доступа и повторите попытку.");
                System.exit(1);
            }
        }


        IdProducer idProducer = new IdProducer();
        StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
        IStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory, path);
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
