package domain.studyGroupFactory.idProducer;

import storage.exception.DAOException;
import storage.idProducerDAO.IdProducerDAO;

import java.util.List;

public class IdProducer {
    private List<Long> idList;
    private IdProducerDAO idProducerDAO;

    public IdProducer(String path) throws DAOException {
        this.idProducerDAO = new IdProducerDAO(path);
        idList = idProducerDAO.getIdProducerDTO();
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

    private  List<Long> getDefaultId(String path) throws DAOException {
        IdProducerDAO idProducerDAO = new IdProducerDAO(path);

        return idProducerDAO.getIdProducerDTO();
    }
}
