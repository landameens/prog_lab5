package domain.studyGroupFactory.idProducer;

import storage.exception.DAOException;
import storage.idProducerDAO.IIdProducerDAO;
import storage.idProducerDAO.IdProducerDAO;

import java.util.LinkedList;
import java.util.List;

public class IdProducer {
    private List<Long> idList;
    private IIdProducerDAO idProducerDAO;

    public IdProducer(String path) throws DAOException {
        this.idProducerDAO = new IdProducerDAO(path);
        idList = getInitialCollection();
    }

    private List<Long> getInitialCollection() {
        List<Long> newListId = new LinkedList<>();
        for (long i = 1; i < 100; i++){
            newListId.add(i);
        }
        return newListId;
    }


    public long getId(){
        int k = 0;
        Long resultId = idList.get(k);
        idList.remove(k);
        return resultId;
    }

    public void saveId() throws DAOException {
        IdProducerDTO idProducerDTO = new IdProducerDTO();
        idProducerDTO.IdCollection = idList;
        idProducerDAO.saveIdProducerDTO(idProducerDTO);
    }

}
