package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Set;
import java.util.TreeSet;

public final class AllSet extends ConcreteSet {
    @Override
    public Set<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        Set<StudyGroup> finalStudyGroup = new TreeSet<>();

        for(StudyGroup studyGroup : studyGroups) {

            finalStudyGroup.add(studyGroup.clone());
        }

        return finalStudyGroup;
    }
}
