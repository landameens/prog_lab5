package storage;

import storage.exception.DAOException;

import java.util.List;

public class StudyGroupDAO implements IStudyGroupDAO{

    private String pathToFile;

    @Override
    public List<?> getDTO() throws DAOException {
        return null;
    }

    @Override
    public void saveDTO(List<?> dto) throws DAOException {

    }
}
