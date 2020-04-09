package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IdProducerDAO implements IIdProducerDAO {
    private File file;

    public IdProducerDAO(String path) {
        this.file = new File(path);
    }

    @Override
    public IdProducerDTO getIdProducerDTO() throws DAOException {
        try(ObjectInput objectInput = new ObjectInputStream(new FileInputStream(file))) {
            return (IdProducerDTO) objectInput.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            throw new DAOException(exception);
        }
    }


    public void saveIdProducerDTO(IdProducerDTO dto) throws DAOException {
        try (ObjectOutput objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(dto);
        } catch (IOException e) {
            throw new DAOException(e);
        }

    }
}
