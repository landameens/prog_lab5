package controller.commands;

import controller.response.Response;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;
import storage.exception.DAOException;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class SaveCommand extends StudyGroupRepositoryCommand {
    public SaveCommand(String type,
                       Map<String, String> args,
                       IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute() {
        try {
            studyGroupRepository.save();

            return getSuccessfullyResponseDTO("Коллекция сохранена в файл.");
        } catch (DAOException e) {
            return getInternalErrorResponseDTO(e.getMessage());
        }
    }

}
