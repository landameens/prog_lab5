package storage;

import storage.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.*;

public class StudyGroupDAO implements IStudyGroupDAO{

    private String pathToFile;

    public StudyGroupDAO(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public Set<?> getDTO() throws DAOException {
        return null;
    }

    @Override
    public void saveDTO(Set<?> dto) throws DAOException {
        List<?> dtoss = new ArrayList<>(dto);
        List<String> name = new LinkedList<>();

        StringWriter writer = new StringWriter();
        String finalName;

        for (int i = 0; i < name.size(); i++){
            finalName = pathToFile + "/" + dtoss.get(i) + ".xml";
            name.add(finalName);
        }

        for (int i = 0; i < dtoss.size(); i++){
            try {
                JAXBContext context = JAXBContext.newInstance(StudentGroupDTO);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setPropetry(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(studentGroupDTO, name.get(i));
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }
    }
}
