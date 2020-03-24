package controller.commands;

import app.query.Query;
import controller.response.Response;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public class StudyGroupRepositoryCommand extends Command {
    private IStudyGroupRepository studyGroupRepository;

    public StudyGroupRepositoryCommand(String type,
                                       Map<String, String> args) {
        super(type, args);
    }

    @Override
    public Response execute(Query query) {

        return Response.getResponse(responseDTO);
    }

}
