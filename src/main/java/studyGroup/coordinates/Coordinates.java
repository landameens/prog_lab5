package studyGroup.coordinates;

import domain.exception.VerifyException;

public class Coordinates {
    private static final String EMPTY_VALUE = "Значение не должно быть пустым.";
    private static final String MAXIMUM_VALUE = "Превышено максимальное значение.";

    private Integer x; //Максимальное значение поля: 28, Поле не может быть null
    private int y;

    public Coordinates(Integer x, int y) throws VerifyException {
        checkX(x);
        this.x = x;
        this.y = y;
    }

    private void checkX(Integer x) throws VerifyException{
        if (x == null){
            throw new VerifyException(EMPTY_VALUE);
        }

        if (x > 28){
            throw new VerifyException(MAXIMUM_VALUE);
        }

    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
