package storage;

import domain.studyGroup.StudyGroupDTO;
import domain.studyGroupRepository.CollectionInfo;
import storage.exception.DAOException;

import javax.xml.bind.JAXBException;

public interface ICollectionInfoDAO {
    CollectionInfo getInfos() throws DAOException, JAXBException;

    //TODO: коллекция то одна, так что поменяй имя метода
    void saveInfos(CollectionInfo collectionInfo) throws DAOException;
}
