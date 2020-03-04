package studyGroup;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int height; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Country nationality; //Поле может быть null
}
