package controller.commands;

import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.AllSet;
import domain.studyGroupRepository.concreteSet.ConcreteSet;

import java.util.Map;
import java.util.Set;

public class ClearCommand extends StudyGroupRepositoryCommand {
    public ClearCommand(String type,
                        Map<String, String> args,
                        IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute() {
        try {
            ConcreteSet allSet = new AllSet();
            Set<StudyGroup> groupSet = studyGroupRepository.getConcreteSetOfStudyGroups(allSet);

            for (StudyGroup removableStudyGroup : groupSet) {
                studyGroupRepository.remove(removableStudyGroup);
            }

            return getSuccessfullyResponseDTO("Коллекция очищена.");

        } catch (StudyGroupRepositoryException e) {
            return getBadRequestResponseDTO(e.getMessage());
        }
    }
}
