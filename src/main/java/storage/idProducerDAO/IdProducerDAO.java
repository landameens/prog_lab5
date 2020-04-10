package storage.idProducerDAO;

import domain.studyGroupFactory.idProducer.IdProducerDTO;
import storage.exception.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IdProducerDAO implements IIdProducerDAO {
    private File file;

    public IdProducerDAO(String path) {
        this.file = checkToFile(path);
    }

    private class TransfertDTO{
        public String S;
        public Long L;
    }

    public List<Long> getList() throws DAOException {
        List<Long> longs = new ArrayList<>();
        List<String> strings;

        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new DAOException(e);
        }

        strings.forEach(s -> {
            TransfertDTO transfertDTO = new TransfertDTO();
            transfertDTO.S = s;
            if(tryParseToLong(transfertDTO)){
                longs.add(transfertDTO.L);
            }
        });

        if(longs.isEmpty())
            setFullRange(longs);
        return longs;
    }

    private void setFullRange(List<Long> l){
        for(long i=1L; i <= 100; i++){
            l.add(i);
        }
    }

    private boolean tryParseToLong(TransfertDTO t){
        try{
            Long l = Long.valueOf(t.S);
            t.L =l;
            return true;
        }
        catch (Exception e){
            return false;
        }
    }



    public void saveIdProducerDTO(IdProducerDTO dto){
        try {
            Files.write(file.toPath(), toStringList(dto.IdCollection));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Iterable<String> toStringList(List<Long> l){
        ArrayList<String> strings = new ArrayList<>();
        l.forEach( lng -> strings.add(lng.toString()));
        return strings;
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
