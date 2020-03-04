package domain;

import domain.exception.StudyGroupException;
import studyGroup.StudyGroupDTO;

public interface IStudyGroupRepository {

    void add(StudyGroupDTO studyGroupDTO) throws StudyGroupException;

    void remove_by_id(int id) throws StudyGroupException;

    void update(int id, StudyGroupDTO studyGroupDTO) throws StudyGroupException;

}
