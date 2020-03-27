package controller.commands;

import app.query.Query;
import controller.response.Response;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public class StudyGroupRepositoryCommand extends Command {
    protected IStudyGroupRepository studyGroupRepository;

    public StudyGroupRepositoryCommand(String type,
                                       Map<String, String> args,
                                       IStudyGroupRepository studyGroupRepository) {
        super(type, args);
        this.studyGroupRepository = studyGroupRepository;
    }


    //TODO: почему бы не сделать этот класс абстрактным, а этот метод тоже абстрактным?
    @Override
    public Response execute(Query query) throws StudyGroupRepositoryException {
        return Response.getResponse(responseDTO);
    }

}
