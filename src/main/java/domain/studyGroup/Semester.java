package domain.studyGroup;

public enum Semester {
    FIRST("first"),
    SECOND("second"),
    FOURTH("fourth"),
    EIGHTH("eighth");

    private String name;
    Semester(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Semester getSemesterEnum(String name){
        if (name.equals("first")){
            return FIRST;
        }

        if (name.equals("second")){
            return SECOND;
        }

        if (name.equals("fourth")){
            return FOURTH;
        }

        if (name.equals("eighth")){
            return EIGHTH;
        }

        return null;
    }
}
