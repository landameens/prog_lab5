package domain;

import domain.exception.CreationException;
import domain.exception.VerifyException;
import studyGroup.StudyGroup;
import studyGroup.StudyGroupDTO;

public interface IStudyGroupFactory {
    //TODO: реализация собсна
    //TODO: для привсоения id уникального для объектов нужно создать класс IdProducer, он должен прокидываться в конструктор фабрики и использоваться в методе createStudyGroup
    StudyGroup createNewStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException, CreationException;

    StudyGroup getStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException, CreationException;

    StudyGroupDTO getStudyGroupDTO(StudyGroup studyGroup);
}