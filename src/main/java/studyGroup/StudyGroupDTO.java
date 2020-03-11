package studyGroup;

import studyGroup.coordinates.Coordinates;
import studyGroup.coordinates.CoordinatesDTO;
import studyGroup.person.Person;
import studyGroup.person.PersonDTO;

import java.time.LocalDateTime;

public final class StudyGroupDTO {
    public Long id;
    public String name;
    public CoordinatesDTO coordinates;
    public java.time.LocalDateTime creationDate;
    public int studentsCount;
    public Long shouldBeExpelled;
    public FormOfEducation formOfEducation;
    public Semester semesterEnum;
    public PersonDTO groupAdmin;

}
