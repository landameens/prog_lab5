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

import java.time.LocalDateTime;
import java.util.Set;

public class App {
    public static void main(String[] args) throws StudyGroupRepositoryException, VerifyException, DAOException {
        String path = "C:\\Users\\user\\Desktop\\Programming\\prog_lab5\\src\\main\\resources\\studyGroups";
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
