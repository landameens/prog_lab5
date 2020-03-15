package app;


import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;
import domain.studyGroupFactory.StudyGroupFactory;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;

//TODO: следующий этап идти и разбираться в DAO (data acces object) мне самому надо будет в этом разобраться)
//TODO:
public class App {
    public static void main(String[] args) throws StudyGroupRepositoryException {
        IdProducer idProducer = new IdProducer();
        StudyGroupFactory studyGroupFactory = new StudyGroupFactory(idProducer);
        TreeSetStudyGroupRepository studyGroupRepository = new TreeSetStudyGroupRepository(studyGroupFactory);

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

        studyGroupRepository.add(studyGroupDTO);
    }
}
