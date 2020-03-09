package domain;

import domain.exception.CreationException;
import domain.exception.StudyGroupException;
import domain.exception.VerifyException;
import studyGroup.StudyGroup;
import studyGroup.StudyGroupDTO;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetStudyGroupRepository implements IStudyGroupRepository{

    private static final String INTERNAL_ERROR = "Внутренняя ошибка";
    private static final String NULL_ERROR = "Ошибка, нельзя добавить null группу.";
    private static final String REPEAT_ERROR = "Такая группа уже существует.";

    private IStudyGroupFactory studyGroupFactory;
    private TreeSet<StudyGroup> studyGroups;

    public TreeSetStudyGroupRepository(IStudyGroupFactory studyGroupFactory){
        this.studyGroupFactory = studyGroupFactory;
        studyGroups = new TreeSet<>();

    }
    @Override
    public void add(StudyGroupDTO studyGroupDTO) throws StudyGroupException {

        StudyGroup studyGroup;
        try {
            studyGroup = studyGroupFactory.createNewStudyGroup(studyGroupDTO);
        } catch (VerifyException | CreationException e) {
            throw new StudyGroupException(INTERNAL_ERROR);
        }

        if (studyGroup == null) {
            throw new StudyGroupException(NULL_ERROR);
        }

        if (studyGroups.contains(studyGroup)) {
            throw new StudyGroupException(REPEAT_ERROR);
        }
    }

    //TODO: реализовать собсна методы
    @Override
    public void remove(StudyGroup studyGroup) throws StudyGroupException {
        if (studyGroups.remove(studyGroup)){

        }
    }

    @Override
    public void update(int id, StudyGroupDTO studyGroupDTO) throws StudyGroupException {

    }

    @Override
    public Set<StudyGroup> getConcreteSetOfStudyGroups(ConcreteSet concreteSet) {

        //return;
    }
}
