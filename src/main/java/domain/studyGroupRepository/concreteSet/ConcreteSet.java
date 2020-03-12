package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Set;
import java.util.TreeSet;

public abstract class ConcreteSet {
    public abstract TreeSet<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException;
}
