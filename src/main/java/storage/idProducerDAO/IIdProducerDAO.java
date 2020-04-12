package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.util.List;

public interface IIdProducerDAO {
    List<Long> getList() throws DAOException;

    IdProducerDTO getIdProducerDTO() throws DAOException;

    void saveIdProducerDTO(IdProducerDTO dto) throws DAOException;
}
