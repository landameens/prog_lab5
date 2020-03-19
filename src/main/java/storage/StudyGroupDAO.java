package storage;

import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import storage.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

public class StudyGroupDAO implements IStudyGroupDAO{
    private final static String EMPTY_DIRECTORY_ERROR = "Отсутствуют файлы.";

    private String pathToFile;
    public StudyGroupDAO(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public Set<StudyGroupDTO> getDTOs() throws DAOException {
        File directory = new File(pathToFile);
        File[] files =  directory.listFiles();

        if (files == null){
            throw new DAOException(EMPTY_DIRECTORY_ERROR);
        }

        List<File> studyGroupFiles = Arrays.asList(files);
        return deserialize(studyGroupFiles);
    }

    private Set<StudyGroupDTO> deserialize(List<File> studyGroupFiles)throws DAOException{
        Set<StudyGroupDTO> studyGroupDTOSet = new TreeSet<>();
        for (File studyGroupFile : studyGroupFiles) {
            try {
                JAXBContext context = JAXBContext.newInstance(StudyGroup.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StudyGroupDTO studyGroupDTO = (StudyGroupDTO) unmarshaller.unmarshal(studyGroupFile);
                studyGroupDTOSet.add(studyGroupDTO);
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }
        return studyGroupDTOSet;
    }

    @Override
    public void saveDTOs(Set<StudyGroupDTO> dto) throws DAOException {
        List<StudyGroupDTO> studyGroupDTOs = new ArrayList<>(dto);
        List<String> names = fillListByNames(studyGroupDTOs);
        List<File> files = createFiles(names);
        serialize(dto, files);
    }

    private List<String> fillListByNames(List<StudyGroupDTO> studyGroupDTOs){
        List<String> names = new LinkedList<>();
        for (StudyGroupDTO studyGroupDTO : studyGroupDTOs){
            names.add(pathToFile + "/" + "StudyGroup" + studyGroupDTO.id + ".xml");
        }
        return names;
    }

    private List<File> createFiles(List<String> names){
        List<File> files = new ArrayList<>();
        for (String name : names){
            File file = new File(name);
            files.add(file);
        }
        return files;
    }

    private void serialize(Set<StudyGroupDTO> studyGroupDTOs, List<File> files) throws DAOException {
        for (StudyGroupDTO studyGroupDTO : studyGroupDTOs) {
            try {
                JAXBContext context = JAXBContext.newInstance(StudyGroupDTO.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.marshal(studyGroupDTO, files.get(0));
                files.remove(0);
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }
    }
}
