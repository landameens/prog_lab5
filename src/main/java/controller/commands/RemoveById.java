package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public class RemoveById extends StudyGroupRepositoryCommand {

    public RemoveById(String type, Map<String, String> args, IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute(Query query) throws StudyGroupRepositoryException {

        Long id = Long.parseLong(args.get("id"));
        StudyGroup removableStudyGroup = studyGroupRepository.findStudyGroupById(id);
        studyGroupRepository.remove(removableStudyGroup);
        responseDTO.answer = "Группа удалена.";
        responseDTO.status = Status.SUCCESSFULLY.getCode();

        return Response.getResponse(responseDTO);
    }
}
