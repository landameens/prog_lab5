package domain.studyGroup;

import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;

public final class StudyGroupDTO {
    public Long id;
    public String name;
    public CoordinatesDTO coordinates;
    public java.time.LocalDateTime creationDate;
    public int studentsCount;
    public Long shouldBeExpelled;
    public String formOfEducation;
    public String semesterEnum;
    public PersonDTO groupAdmin;

}
