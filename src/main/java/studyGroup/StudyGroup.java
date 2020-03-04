package studyGroup;

import domain.exception.VerifyException;

import java.time.LocalDateTime;

public class StudyGroup {

    private static final String SHOULD_BE_POSITIVE = "Значение должно быть положительным.";
    private static final String EMPTY_EXCEPTION = "Значение не должно быть пустым";

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private Long shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null

    //TODO: конструктор с проверкой

    public StudyGroup(Long id,
                      String name,
                      Coordinates coordinates,
                      LocalDateTime creationDate,
                      int studentsCount,
                      Long shouldBeExpelled,
                      FormOfEducation formOfEducation,
                      Semester semesterEnum,
                      Person groupAdmin) {
        //checkID(id);
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    private void checkId(Long id) throws VerifyException {
        if (id == null){
            throw new VerifyException(EMPTY_EXCEPTION);
        }

        if (id <= 0){
            throw new VerifyException(SHOULD_BE_POSITIVE);
        }
    }

    private void checkName(String name) throws VerifyException{
        if (name == null){
            throw new VerifyException(EMPTY_EXCEPTION);
        }

        if (name.equals("")){
            throw new VerifyException(EMPTY_EXCEPTION);
        }

    }

    private void checkStudentsCount(int studentsCount) throws VerifyException{
        if (studentsCount <= 0){
            throw new VerifyException(SHOULD_BE_POSITIVE);
        }
    }

    private void checkShouldBeExpelled(Long shouldBeExpelled) throws VerifyException{
        if (shouldBeExpelled <= 0){
            throw new VerifyException(SHOULD_BE_POSITIVE);
        }
    }
}
