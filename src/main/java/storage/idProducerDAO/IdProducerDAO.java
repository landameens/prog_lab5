package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IdProducerDAO implements IIdProducerDAO {
    private String path;

    public IdProducerDAO(String path) {
        this.path = path + "\\idProducer\\default";
    }

    @Override
    public List<Long> getIdProducerDTO() throws DAOException {
        try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(path))){

            List<Long> listId = (List<Long>) objectInput.readObject();

            if (listId.isEmpty()){
                List<Long> newListId = new ArrayList<>();
                for (long i = 1; i < 100; i++){
                    newListId.add(i);
                }
                return newListId;
            }
            return listId;
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
