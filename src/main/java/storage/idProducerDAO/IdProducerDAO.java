package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;

public class IdProducerDAO implements IIdProducerDAO {
    private String path;

    public IdProducerDAO(String path) {
        this.path = path + "\\idProducer\\default";
    }

    @Override
    public Long getDefaultIdProducerDTO() throws DAOException {
        try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(path))){

            Long defaultId = (Long) objectInput.readObject();

            if (defaultId == null){
                return 1L;
            }

            return defaultId;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e.getMessage());
        }
    }


    public void saveIdProducerDTO(IdProducerDTO dto) throws DAOException {
        try (ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(path))){
            objectOutput.writeObject(dto);
        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
