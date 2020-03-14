package domain.studyGroupRepository;

import domain.studyGroupRepository.concreteSet.ConcreteSet;
import domain.exception.CreationException;
import domain.exception.StudyGroupRepositoryException;
import domain.exception.VerifyException;
import domain.studyGroupFactory.IStudyGroupFactory;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetStudyGroupRepository implements IStudyGroupRepository {

    //TODO: нэйминг поменять, например INTERNAL_ERROR_MESSAGE, чтобы было понятно из контекста
    private static final String INTERNAL_ERROR = "Внутренняя ошибка";
    private static final String NULL_ERROR = "Ошибка, нельзя добавить null группу.";
    private static final String REPEAT_ERROR = "Такая группа уже существует.";
    private static final String EMPTY_STUDY_GROUP = "Такой группы нет в репозитории.";
    private static final String IMPOSSIBLE_CREATE = "Нельзя обновить study group, такой группы не существует.";

    private IStudyGroupFactory studyGroupFactory;
    private Set<StudyGroup> studyGroups;

    public TreeSetStudyGroupRepository(IStudyGroupFactory studyGroupFactory){
        this.studyGroupFactory = studyGroupFactory;
        studyGroups = new TreeSet<>();

    }
    @Override
    public void add(StudyGroupDTO studyGroupDTO) throws StudyGroupRepositoryException {

        StudyGroup studyGroup;
        try {
            studyGroup = studyGroupFactory.createNewStudyGroup(studyGroupDTO);
        } catch (VerifyException | CreationException e) {
            throw new StudyGroupRepositoryException(INTERNAL_ERROR);
        }

        if (studyGroup == null) {
            throw new StudyGroupRepositoryException(NULL_ERROR);
        }

        if (studyGroups.contains(studyGroup)) {
            throw new StudyGroupRepositoryException(REPEAT_ERROR);
        }

        studyGroups.add(studyGroup);
    }

    @Override
    public void remove(StudyGroup studyGroup) throws StudyGroupRepositoryException {
        if (!studyGroups.remove(studyGroup)){
            throw new StudyGroupRepositoryException(EMPTY_STUDY_GROUP);
        }
    }

    @Override
    public void update(StudyGroup studyGroup) throws StudyGroupRepositoryException {
        StudyGroup studyGroupExist = findStudyGroup(studyGroup);

        if (studyGroupExist == null){
            throw new StudyGroupRepositoryException(IMPOSSIBLE_CREATE);
        }

        studyGroups.remove(studyGroupExist);

        if(!studyGroups.add(studyGroup)){
            throw new StudyGroupRepositoryException(INTERNAL_ERROR);
        }
    }

    private StudyGroup findStudyGroup(StudyGroup studyGroup){
        for (StudyGroup currentStudyGroup : studyGroups){
            if (currentStudyGroup.getId().equals(studyGroup.getId())){
                return currentStudyGroup;
            }
        }
        return null;
    }

    @Override
    public Set<StudyGroup> getConcreteSetOfStudyGroups(ConcreteSet concreteSet) throws StudyGroupRepositoryException{
        if (studyGroups.isEmpty()){
            return new TreeSet<>();
        }

        return concreteSet.execute(studyGroups);
    }
}
