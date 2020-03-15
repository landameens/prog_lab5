package storage;

import domain.studyGroup.StudyGroupDTO;
import storage.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface IStudyGroupDAO {
    //TODO: Тоже надо подшаманить с wildcard'ом
    Set<?> getDTO() throws DAOException;

    void saveDTO(Set<StudyGroupDTO> dto) throws DAOException;
}
