package app;

import domain.exception.StudyGroupRepositoryException;
import domain.exception.VerifyException;
import domain.studyGroup.FormOfEducation;
import domain.studyGroup.Semester;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.Coordinates;
import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.Country;
import domain.studyGroup.person.Person;
import domain.studyGroup.person.PersonDTO;
import domain.studyGroupFactory.StudyGroupFactory;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.StudyGroupDAO;
import storage.exception.DAOException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public class App {
    private static void checkInputPath(String[] args) {
        if (args.length != 2) {
            System.err.println(LACK_OF_ARGUMENTS_ERROR);
            System.exit(1);
        }
    }
    private static final String LACK_OF_ARGUMENTS_ERROR = "Неверный путь. Введите в формате {absolute/relative} {path to the fail}";

    public static void main(String[] args) throws StudyGroupRepositoryException, VerifyException, DAOException {
        ClassLoader classLoader = App.class.getClassLoader();
        String path = "";


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


        IdProducer idProducer = new IdProducer();
        StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
        TreeSetStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory, path);

        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.x = 13;
        coordinatesDTO.y = 94;

        PersonDTO personDTO = new PersonDTO();
        personDTO.name = "Yarik";
        personDTO.height = 178;
        personDTO.passportID = "2h23";
        personDTO.nationality = "germany";

        StudyGroupDTO studyGroupDTO = new StudyGroupDTO();
        studyGroupDTO.name = "B232";
        studyGroupDTO.coordinates = coordinatesDTO;
        studyGroupDTO.formOfEducation = "fullTimeEducation";
        studyGroupDTO.semesterEnum = "second";
        studyGroupDTO.studentsCount = 25;
        studyGroupDTO.groupAdmin = personDTO;
        studyGroupDTO.shouldBeExpelled = 2L;

        //studyGroupRepository.add(studyGroupDTO);
        //studyGroupRepository.returnStudyGroup();
        //studyGroupRepository.save();
    }

}
