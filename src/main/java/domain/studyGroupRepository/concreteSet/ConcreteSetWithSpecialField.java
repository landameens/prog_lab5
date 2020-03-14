package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Set;
import java.util.TreeSet;

public final class ConcreteSetWithSpecialField extends ConcreteSet {

    public ConcreteSetWithSpecialField(Class<?> clazz, String field, Object value){

    }

    @Override
    public Set<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        Set<StudyGroup> finalStudyGroup = new TreeSet<>();



        return finalStudyGroup;
    }
}
