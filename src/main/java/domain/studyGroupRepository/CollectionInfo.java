package domain.studyGroupRepository;

import storage.adapters.LocalDateTimeAdapter;
import storage.adapters.ZonedDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;

@XmlRootElement
@XmlType(name = "collectionInfo")
@XmlAccessorType(XmlAccessType.FIELD)

public final class CollectionInfo {
    public Class<?> type;
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    public ZonedDateTime creationDate;
    public int size;


    @Override
    public String toString() {
        return "CollectionInfo{" + System.lineSeparator() +
                "type='" + type + '\'' + System.lineSeparator() +
                ", creationDate=" + creationDate + System.lineSeparator() +
                ", size=" + size +
                '}';
    }
}



