package domain;

import domain.exception.CreationException;
import domain.exception.VerifyException;
import studyGroup.FormOfEducation;
import studyGroup.Semester;
import studyGroup.StudyGroup;
import studyGroup.StudyGroupDTO;
import studyGroup.coordinates.Coordinates;
import studyGroup.person.Person;

import java.time.LocalDateTime;

public class StudyGroupFactory implements IStudyGroupFactory {

    private IdProducer idProducer;

    public StudyGroupFactory(IdProducer idProducer){
        this.idProducer = idProducer;
    }
    @Override
    public StudyGroup createNewStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException, CreationException {
        if (studyGroupDTO == null){
            return null;
        }

        Long id = idProducer.getId();
        String name = studyGroupDTO.name;
        //TODO: написать createCoordinations
        Coordinates coordinates = Coordinates.createCoordinates(studyGroupDTO.coordinates);
        LocalDateTime creationDate = LocalDateTime.now();
        int studentsCount = studyGroupDTO.studentsCount;
        Long shouldBeExpelled = studyGroupDTO.shouldBeExpelled;
        FormOfEducation formOfEducation = studyGroupDTO.formOfEducation;
        Semester semesterEnum = studyGroupDTO.semesterEnum;
        Person groupAdmin = studyGroupDTO.groupAdmin;

        return new StudyGroup(id,
                            name,
                            coordinates,
                            creationDate,
                            studentsCount,
                            shouldBeExpelled,
                            formOfEducation,
                            semesterEnum,
                            groupAdmin);
    }

    @Override
    public StudyGroup getStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException, CreationException {
        return null;
    }

    @Override
    public StudyGroupDTO getStudyGroupDTO(StudyGroup studyGroup) {
        return null;
    }
}
