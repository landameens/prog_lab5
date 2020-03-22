package controller.commands;

import app.query.Query;
import controller.response.Response;
import domain.studyGroupRepository.TreeSetStudyGroupRepository;

import java.util.Map;

public class StudyGroupRepositoryCommand extends Command {
    public StudyGroupRepositoryCommand(String type, Map<String, String> args) {
        super(type, args);
    }

    private TreeSetStudyGroupRepository studyGroupRepository;

    @Override
    Response execute(Query query) {

        return Response.getResponse();
    }

}
