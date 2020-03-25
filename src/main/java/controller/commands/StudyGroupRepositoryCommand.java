package controller.commands;

import app.query.Query;
import controller.response.Response;
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


    @Override
    public Response execute(Query query) {
        return Response.getResponse(responseDTO);
    }

}
