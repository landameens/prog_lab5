package domain.studyGroup;

public enum FormOfEducation {
    DISTANCE_EDUCATION("distanceEducation"),
    FULL_TIME_EDUCATION("fullTimeEducation"),
    EVENING_CLASSES("eveningClasses");

    private String name;
    FormOfEducation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FormOfEducation getFormOfEducation(String name){
        if (name.equals("distanceOfEducation")){
            return DISTANCE_EDUCATION;
        }

        if (name.equals("fullTimeEducation")){
            return FULL_TIME_EDUCATION;
        }

        if (name.equals("eveningClasses")){
            return EVENING_CLASSES;
        }

        return null;
    }
}
