package storage;

import storage.exception.DAOException;

import java.util.List;

public interface IStudyGroupDAO {
    List<?> getDTO() throws DAOException;

    void saveDTO(List<?> dto) throws DAOException;
}
