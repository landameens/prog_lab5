package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public class RemoveById extends StudyGroupRepositoryCommand {

    //TODO: опять в одну строку(
    public RemoveById(String type, Map<String, String> args, IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute(Query query) throws StudyGroupRepositoryException {
        //TODO: не забывай про ЛОГИЧЕСКИЕ ОТСТУПЫЫЫЫЫЫЫЫЫЫЫЫ
        Long id = Long.parseLong(args.get("id"));
        //TODO: хех, а зачем ты тогда писала ConcreteSetWithSpecialField????? переделывай поиск объекта, даже хотя это правильно)
        StudyGroup removableStudyGroup = studyGroupRepository.findStudyGroupById(id);
        studyGroupRepository.remove(removableStudyGroup);
        //TODO: НЕ ПРОКИДЫВАЙ ЭКСЕПШЕНЫ ДАЛЬШЕ - ЛОВИШЬ ИЗ ПРЯМО ТУТ И ФОРМИРЕШЬ СООТВЕТСВЕННО РЕСПОНЗ
        //TODO: ЭТО КАСАЕТСЯ ВСЕХ КОММАНД
        responseDTO.answer = "Группа удалена.";
        responseDTO.status = Status.SUCCESSFULLY.getCode();

        return Response.getResponse(responseDTO);
    }
}
