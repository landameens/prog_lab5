package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IdProducerDAO implements IIdProducerDAO {
    private String path;

    public IdProducerDAO(String path) {
        this.path = path + "\\idProducer\\default";
    }

    @Override
    public IdProducerDTO getIdProducerDTO() throws DAOException {
        IdProducerDTO dto = new IdProducerDTO();
        List<Long> newListId = new ArrayList<>();
        for (long i = 1; i < 100; i++){
            newListId.add(i);
        }
        dto.IdCollection = newListId;

        File file = new File(path);
        byte[] bytes = file.toString().getBytes();

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInput objectInput = new ObjectInputStream(byteInputStream)){
            dto.IdCollection = (List<Long>) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }

        return dto;
    }


    public void saveIdProducerDTO(IdProducerDTO dto) throws DAOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream)){
            objectOutput.writeObject(dto);
        } catch (IOException e) {
            throw new DAOException(e);
        }

        File file = new File(path);
        file.delete();
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
