package domain;

import domain.exception.CreationException;
import domain.exception.VerifyException;
import studyGroup.StudyGroup;
import studyGroup.StudyGroupDTO;

public interface IStudyGroupFactory {

    StudyGroup createNewStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException, CreationException;
}