package studyGroup;

import studyGroup.coordinates.Coordinates;
import studyGroup.person.Person;

public final class StudyGroupDTO {
    public String name;
    public Coordinates coordinates;
    public java.time.LocalDateTime creationDate;
    public int studentsCount;
    public Long shouldBeExpelled;
    public FormOfEducation formOfEducation;
    public Semester semesterEnum;
    public Person groupAdmin;
}
