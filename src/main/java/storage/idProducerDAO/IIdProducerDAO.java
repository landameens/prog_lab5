package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;


public interface IIdProducerDAO {
    IdProducerDTO getIdProducerDTO() throws DAOException;

    void saveIdProducerDTO(IdProducerDTO dto) throws DAOException;
}
