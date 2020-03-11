package domain.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import studyGroup.StudyGroup;

import java.util.TreeSet;

public abstract class ConcreteSet {
    public abstract TreeSet<StudyGroup> execute(TreeSet<StudyGroup> studyGroups) throws StudyGroupRepositoryException;
}
