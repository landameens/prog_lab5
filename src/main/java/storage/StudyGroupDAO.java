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
        //TODO: в wildcard можно задать конкретный тип StudyGroupDTO, так же и для интерфеса, ведь он даже называется StudyGroupDAO
        List<?> dtoss = new ArrayList<>(dto);

        //TODO: нейминг: все коллекции (ибо там почти всегда хранится много объектов) принято называть во множественном числе ---> names
        List<String> name = new LinkedList<>();

        StringWriter writer = new StringWriter();
        String finalName;

        //TODO: требуется переделка. а именно
        // dtoss.get(i) вернет ахрененно огромную строку (будет вызываться studyGroupDTO.toString()), так что нужно у этой дтохи вызвать какой-то геттер, например геттер id
        // почему не использовать цикл for - each для dtoss ???
        for (int i = 0; i < name.size(); i++){
            finalName = pathToFile + "/" + dtoss.get(i) + ".xml";
            name.add(finalName);
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
                marshaller.setPropetry(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(studentGroupDTO, name.get(i));
            } catch (JAXBException e) {
                throw new DAOException(e);
            }
        }
    }
}
