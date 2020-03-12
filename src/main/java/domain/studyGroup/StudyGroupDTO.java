package domain.studyGroup;

import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;

public final class StudyGroupDTO {
    //TODO: никаких enum-ов в DTO, в DTO вместо енама должно валяться что-то простое по типу String, иначе будут проблемы
    //с сохранением. Решение - иди в класс енама. Вопросы - ко мне
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
