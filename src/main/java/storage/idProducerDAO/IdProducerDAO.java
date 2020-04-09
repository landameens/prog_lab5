package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IdProducerDAO implements IIdProducerDAO {
    private File file;

    public IdProducerDAO(String path) {
        this.file = checkToFile(path);
    }

    @Override
    public List<Long> getList() throws DAOException {

        List<Long> list = new LinkedList<>();
        if (list.isEmpty()){
            for (long i = 1; i < 100; i++){
                list.add(i);
            }
        }

        try(ObjectInput objectInput = new ObjectInputStream(new FileInputStream(file))) {
            Object object = objectInput.readObject();
            if(object == null){
                return list;
            }

            return (List<Long>) object;
        } catch (ClassNotFoundException | IOException exception) {
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

    private File checkToFile(String path)  {
        File file = new File(path);

        if (file.isDirectory()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }

        return file;
    }
}
