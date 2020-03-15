package storage;

import domain.studyGroup.StudyGroupDTO;
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
    public void saveDTO(Set<StudyGroupDTO> dto) throws DAOException {
        //TODO: нэйминг, сладкая
        // что-то по типу listOfDTOs или что-то в таком ключе, так же неплохо было бы поменять нейминг и в аргументе данного метода (saveDTO)
        List<StudyGroupDTO> dtoss = new ArrayList<>(dto);

        List<String> names = new LinkedList<>();

        StringWriter writer = new StringWriter();
        String finalName;

        //TODO: требуется переделка. а именно
        // dtoss.get(i) вернет ахрененно огромную строку (будет вызываться studyGroupDTO.toString()), так что нужно у этой дтохи вызвать какой-то геттер, например геттер id
        // почему не использовать цикл for - each для dtoss ???
        for (int i = 0; i < names.size(); i++){
            finalName = pathToFile + "/" + dtoss + ".xml";
            names.add(finalName);
        }

        //TODO: аналогично про цикл for - each, откуда удобно можно ссылаться studentGroupDTO
        // marshaller.marshal(studentGroupDTO, name.get(i)); не будет работать, ибо name.get(i) не вернет объект типа File
        // тепе нужна коллекция List<File>, заполненная пустыми объектам File, причем в каждыый файл должно быть загнано имя finalName
        // File file = new File(finalName) создаст объект пустого файла с именем finalName
        for (int i = 0; i < dtoss.size(); i++){
            try {
                JAXBContext context = JAXBContext.newInstance(StudentGroupDTO);
                Marshaller marshaller = context.createMarshaller();
                //TODO: можно и бнз setPropetry обойтись
                marshaller.marshal(studentGroupDTO, names.get(i));
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }
    }
}
