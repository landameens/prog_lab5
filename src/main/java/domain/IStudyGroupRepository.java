package domain;

import domain.exception.StudyGroupException;
import studyGroup.StudyGroup;
import studyGroup.StudyGroupDTO;

import java.util.Set;

public interface IStudyGroupRepository {

    void add(StudyGroupDTO studyGroupDTO) throws StudyGroupException;

    void remove(int id) throws StudyGroupException;

    void update(int id, StudyGroupDTO studyGroupDTO) throws StudyGroupException;

    Set<StudyGroup> getConcreteSetOfStudyGroups(ConcreteSet concreteSet);

}
