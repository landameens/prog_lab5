package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public final class MinSet extends ConcreteSet {
    @Override
    public Set<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        Comparator<StudyGroup> studyGroupComparator = new StudyGroup.StudyGroupComparator();

        StudyGroup minStudyGroup = findMin(studyGroupComparator, studyGroups);

        return new TreeSet<StudyGroup>(){{
            add(minStudyGroup.clone());
        }};
    }

    private StudyGroup findMin(Comparator<StudyGroup> studyGroupComparator, Set<StudyGroup> studyGroups){
        StudyGroup minStudyGroup = null;

        for (StudyGroup studyGroup : studyGroups){
            if (minStudyGroup == null){
                minStudyGroup = studyGroup;
            }

            if (studyGroupComparator.compare(studyGroup, minStudyGroup)<0) {
                minStudyGroup = studyGroup;
            }
        }
        return minStudyGroup;
    }

}
