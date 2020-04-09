package storage.collectionInfoDAO;

import domain.studyGroup.StudyGroupDTO;
import domain.studyGroupRepository.CollectionInfo;
import storage.exception.DAOException;

import javax.xml.bind.JAXBException;

public interface ICollectionInfoDAO {
    CollectionInfo getInfos() throws DAOException, JAXBException;

    void saveInfo(CollectionInfo collectionInfo) throws DAOException;
}
