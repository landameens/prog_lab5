package controller.commands;

import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.ConcreteSet;
import domain.studyGroupRepository.concreteSet.ConcreteSetWithSpecialField;

import java.util.Map;
import java.util.Set;

public class RemoveByIdCommand extends StudyGroupRepositoryCommand {

    public RemoveByIdCommand(String type,
                             Map<String, String> args,
                             IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute(){
        Long id = Long.parseLong(args.get("id"));
        ConcreteSet removableStudyGroupSet = new ConcreteSetWithSpecialField(StudyGroup.class, "id", id);

        try {
            Set<StudyGroup> groupSet = studyGroupRepository.getConcreteSetOfStudyGroups(removableStudyGroupSet);


            for (StudyGroup studyGroup : groupSet) {
                studyGroupRepository.remove(studyGroup);
            }

            responseDTO.answer = "Группа удалена.";
            responseDTO.status = Status.SUCCESSFULLY.getCode();

            if (groupSet.isEmpty()) {
                responseDTO.answer = "Группы с таким id не существует.";
                responseDTO.status = Status.BAD_REQUEST.getCode();
            }

        } catch (StudyGroupRepositoryException e) {
            responseDTO.answer = e.getMessage();
            responseDTO.status = Status.BAD_REQUEST.getCode();
        }

        return Response.getResponse(responseDTO);
    }
}
