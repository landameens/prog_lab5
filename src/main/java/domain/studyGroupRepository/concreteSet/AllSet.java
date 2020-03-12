package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.TreeSet;

public final class AllSet extends ConcreteSet {
    @Override
    public TreeSet<StudyGroup> execute(TreeSet<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        TreeSet<StudyGroup> finalStudyGroup = new TreeSet<>();

        for(StudyGroup studyGroup :studyGroups) {
            finalStudyGroup.add(studyGroup);
        }

        return finalStudyGroup;
    }
}
