package domain.studyGroupRepository;

import domain.studyGroupRepository.concreteSet.ConcreteSet;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;

import java.util.Set;

public interface IStudyGroupRepository {
    void add(StudyGroupDTO studyGroupDTO) throws StudyGroupRepositoryException;

    void remove(StudyGroup studyGroup) throws StudyGroupRepositoryException;

    void update(StudyGroup studyGroup) throws StudyGroupRepositoryException;

    Set<StudyGroup> getConcreteSetOfStudyGroups(ConcreteSet concreteSet) throws StudyGroupRepositoryException;

}
