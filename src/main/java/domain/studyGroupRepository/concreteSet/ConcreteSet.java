package domain.studyGroupRepository.concreteSet;

import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;

import java.util.Set;

//TODO: тебе понадобятся ConcreteSetWhichLessThanThatProduct (для команды по типу remove_lower)
//TODO: также нужен сет, который возвращал тебе сет продуктов с определенным полем, например по id, или person
public abstract class ConcreteSet {
    public abstract Set<StudyGroup> execute(Set<StudyGroup> studyGroups) throws StudyGroupRepositoryException;
}
