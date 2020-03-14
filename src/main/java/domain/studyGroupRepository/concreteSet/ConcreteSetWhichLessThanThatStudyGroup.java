package domain.studyGroupRepository.concreteSet;

import domain.studyGroup.StudyGroup;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public final class ConcreteSetWhichLessThanThatStudyGroup extends ConcreteSet {

    //TODO: нейминг Comparing
    private final StudyGroup studyGroupForComparasion;

    public ConcreteSetWhichLessThanThatStudyGroup(StudyGroup studyGroupForComparasion){

        this.studyGroupForComparasion = studyGroupForComparasion;
    }

    @Override
    public Set<StudyGroup> execute(Set<StudyGroup> studyGroups) {
        Comparator<StudyGroup> studyGroupComparator = new StudyGroup.StudyGroupComparator();
        Set<StudyGroup> finalStudyGroupSet = new TreeSet<>();

        for(StudyGroup studyGroup : studyGroups) {
            if (studyGroupComparator.compare(studyGroup, studyGroupForComparasion) < 0) {
                finalStudyGroupSet.add(studyGroup.clone());
            }
        }

        return finalStudyGroupSet;
    }

}
