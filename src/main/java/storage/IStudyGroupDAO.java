package storage;

import storage.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface IStudyGroupDAO {
    Set<?> getDTO() throws DAOException;

    void saveDTO(Set<?> dto) throws DAOException;
}
