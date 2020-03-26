package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.ResponseDTO;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public class AddCommand extends StudyGroupRepositoryCommand {
    public AddCommand(String type, Map<String, String> args, IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute(Query query) {
        responseDTO = new ResponseDTO();

        //studyGroupRepository.add();
        responseDTO.answer = "Группа добавлена.";
        responseDTO.status = Status.SUCCESSFULLY.getCode();

        return Response.getResponse(responseDTO);
    }
}
