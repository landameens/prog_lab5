package app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static app.CommandName.*;

/**
 * This class is responsible for displaying invitation messages before user's input or command results in console.
 */
public final class Viewer {
    private final Map<String, String> addCommandMessages = new LinkedHashMap<String, String>() {
        {
            put("StudyGroupName","Введите название группы: ");
            put("xCoordinate","Введите ");
            put("yCoordinate","");
            put("studentsCount","Введите количество студентов в группе: ");
            put("shouldBeExpelled","Введите количество студентов, которых скоро отчислят:");
            put("formOfEducation","Введите форму образования: {DISTANCE_EDUCATION; FULL_TIME_EDUCATION; EVENING_CLASSES;} ");
            put("semesterEnum","Введите номер семестра: {FIRST; SECOND; FOURTH; EIGHTH;} ");
            put("groupAdminName","Введите имя администратора группы: ");
            put("groupAdminHeight","Введите рост администратора группы: ");
            put("groupAdminPassportID","Введите паспортные данные администратора группы: ");
            put("groupAdminNationality","Введите национальность администратора группы: {UNITED_KINGDOM; GERMANY; VATICAN; SOUTH_KOREA; JAPAN;} ");
        }
    };

    private final Map<String, String> countByGroupAdminCommandMessages = new LinkedHashMap<String, String>() {
        {
            put("groupAdminName","Введите имя администратора группы: ");
            put("groupAdminHeight","Введите рост администратора группы: ");
            put("groupAdminPassportID","Введите паспортные данные администратора группы: ");
            put("groupAdminNationality","Введите национальность администратора группы: {UNITED_KINGDOM; GERMANY; VATICAN; SOUTH_KOREA; JAPAN;} ");
        }
    };

    private final Map<CommandName, Map<String, String>> inputMessagesMap = new HashMap<CommandName, Map<String, String>>() {
        {
            put(ADD, addCommandMessages);
            put(ADD_IF_MIN, addCommandMessages);
            put(REMOVE_LOWER, addCommandMessages);
            put(COUNT_BY_GROUP_ADMIN, countByGroupAdminCommandMessages);  //???
            put(UPDATE, addCommandMessages); //не забыть что там еще id был

        }
    };

    public Map<CommandName, Map<String, String>> getInputMessagesMap() {
        return inputMessagesMap;
    }


}
