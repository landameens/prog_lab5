package storage;

import java.util.List;

public interface IStudyGroupDAO {
    List<?> getDTO();

    void saveDTO(List<?> dto);
}
