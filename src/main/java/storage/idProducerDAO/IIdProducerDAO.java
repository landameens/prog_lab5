package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

public interface IIdProducerDAO {
    Long getDefaultIdProducerDTO() throws DAOException;

    void saveIdProducerDTO(IdProducerDTO dto) throws DAOException;
}
