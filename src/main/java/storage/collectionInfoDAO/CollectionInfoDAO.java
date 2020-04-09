package storage.collectionInfoDAO;

import domain.studyGroupRepository.CollectionInfo;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.ZonedDateTime;
import java.util.*;

public class CollectionInfoDAO implements ICollectionInfoDAO {
    private String pathToInfo;

    public CollectionInfoDAO(String pathToFile) {
        this.pathToInfo = pathToFile;
    }

    @Override
    public CollectionInfo getInfos() throws DAOException {
        File file = new File(pathToInfo);
        File[] files =  file.listFiles();

        List<File> collectionInfo = Arrays.asList(files);
        return deserialize(collectionInfo);
    }

    private CollectionInfo deserialize(List<File> collectionInfo)throws DAOException{

        for (File file : collectionInfo) {
            try {
                JAXBContext context = JAXBContext.newInstance(CollectionInfo.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (CollectionInfo) unmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }

        return createCollectionInfo();
    }

    private CollectionInfo createCollectionInfo(){
        CollectionInfo info = new CollectionInfo();
        info.creationDate = ZonedDateTime.now();
        info.size = 0;
        info.type = TreeSetStudyGroupRepository.class;

        return info;
    }

    @Override
    public void saveInfo(CollectionInfo collectionInfo) throws DAOException {
        File file = new File(pathToInfo);
        if (file.delete()){
            serialize(file, collectionInfo);
        }
    }

    private void serialize(File file, CollectionInfo collectionInfo) throws DAOException {
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionInfo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(collectionInfo, file);
        } catch (JAXBException e) {
            throw new DAOException(e);
        }
    }
}
