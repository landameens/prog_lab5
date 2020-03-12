package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Comparator;
import java.util.TreeSet;

public class MinSet extends ConcreteSet {

    //TODO: поменять сигнатуру метода (была изменена в абстрактном классе)
    //TODO: пометить как final класс
    //TODO: расширяй дальше Concrete set САМА, ЕСЛИ ЕСТЬ ВОПРОСЫ СПРАШИВАЙ, А НЕ КОПИПАСТЬ
    @Override
    public TreeSet<StudyGroup> execute(TreeSet<StudyGroup> studyGroups) throws StudyGroupRepositoryException {
        Comparator<StudyGroup> studyGroupComparator = new StudyGroup.StudyGroupComparator();

        StudyGroup minStudyGroup = findMin(studyGroupComparator, studyGroups);

        return new TreeSet<StudyGroup>(){{
            add(minStudyGroup);
        }};
    }

    private StudyGroup findMin(Comparator<StudyGroup> studyGroupComparator, TreeSet<StudyGroup> studyGroups){
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
