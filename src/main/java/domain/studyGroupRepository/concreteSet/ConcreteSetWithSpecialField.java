package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public final class ConcreteSetWithSpecialField extends ConcreteSet {

    private final String field;
    private final Object value;
    private final Class<?> clazz;

    public ConcreteSetWithSpecialField(Class<?> clazz,
                                       String field,
                                       Object value){
        this.field = field;
        this.value = value;
        this.clazz = clazz;
    }

    @Override
    public Set<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        Set<StudyGroup> finalStudyGroup = new TreeSet<>();

        Field clazzField = null;
        try {
            clazzField = clazz.getDeclaredField(field);
            clazzField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        for (StudyGroup studyGroup : studyGroups){
            try {
                if(clazzField.get(studyGroup).equals(value)){
                    finalStudyGroup.add(studyGroup.clone());
                };
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        clazzField.setAccessible(false);

        return finalStudyGroup;
    }
}
