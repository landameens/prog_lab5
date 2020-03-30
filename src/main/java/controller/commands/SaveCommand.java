package controller.commands;

import controller.response.Response;
import controller.response.Status;
import domain.studyGroupRepository.IStudyGroupRepository;
import storage.exception.DAOException;

import java.util.Map;

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
