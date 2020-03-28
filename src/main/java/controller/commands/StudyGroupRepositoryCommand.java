package controller.commands;

import app.query.Query;
import controller.response.Response;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.util.Map;

public abstract class StudyGroupRepositoryCommand extends Command {
    protected IStudyGroupRepository studyGroupRepository;

    public StudyGroupRepositoryCommand(String type,
                                       Map<String, String> args,
                                       IStudyGroupRepository studyGroupRepository) {
        super(type, args);
        this.studyGroupRepository = studyGroupRepository;
    }


    @Override
    public abstract Response execute(Query query);

}
