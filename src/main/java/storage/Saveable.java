package storage;

import storage.exception.DAOException;

public interface Saveable {
    void save() throws DAOException;
}
