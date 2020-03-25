package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.ResponseDTO;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.Semester;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.AllSet;

import java.util.Map;

public class ShowCommand extends StudyGroupRepositoryCommand {
    private IStudyGroupRepository studyGroupRepository;

    public ShowCommand(String type,
                       Map<String, String> args,
                       IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
        this.studyGroupRepository = studyGroupRepository;
    }

    @Override
    public Response execute(Query query) {
        responseDTO = new ResponseDTO();

        responseDTO.status = Status.SUCCESSFULLY.getCode();
        responseDTO.answer = "info";

        return Response.getResponse(responseDTO);
    }


}
