package domain.studyGroupRepository;

import domain.studyGroupRepository.concreteSet.ConcreteSet;
import domain.exception.CreationException;
import domain.exception.StudyGroupRepositoryException;
import domain.exception.VerifyException;
import domain.studyGroupFactory.IStudyGroupFactory;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import storage.IStudyGroupDAO;
import storage.Saveable;
import storage.StudyGroupDAO;
import storage.exception.DAOException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import static domain.studyGroup.StudyGroup.getStudyGroupDTO;

/**
 * These class are realize IStudyGroupRepository interface for working with Tree set.
 */
public class TreeSetStudyGroupRepository implements IStudyGroupRepository, Saveable {

    private static final String INTERNAL_ERROR_MESSAGE = "Внутренняя ошибка данные не подходят под описание.";
    private static final String TRYING_ADD_NULL_GROUP_ERROR_MESSAGE = "Ошибка, нельзя добавить null группу.";
    private static final String SUCH_GROUP_EXIST_ERROR_MESSAGE = "Такая группа уже существует.";
    private static final String NO_SUCH_STUDY_GROUP_ERROR_MESSAGE = "Такой группы нет в репозитории.";

    private IStudyGroupFactory studyGroupFactory;
    private Set<StudyGroup> studyGroups;
    private IStudyGroupDAO studyGroupDAO;

    public TreeSetStudyGroupRepository(IStudyGroupFactory studyGroupFactory,
                                       String path) throws DAOException, VerifyException {
        this.studyGroupFactory = studyGroupFactory;
        studyGroupDAO = new StudyGroupDAO(path);

        Comparator<StudyGroup> studyGroupComparator = new StudyGroup.StudyGroupComparator();
        File directory = new File(path);
        studyGroups = getInitialFiles(directory, studyGroupComparator);
    }

    private Set<StudyGroup> getInitialFiles(File directory,
                                            Comparator<StudyGroup> studyGroupComparator) throws DAOException, VerifyException {
        if (!(directory.listFiles().length == 0)) {
            Set<StudyGroupDTO> studyGroupDTOSet;
            Set<StudyGroup> studyGroups = new TreeSet<>(studyGroupComparator);

            try {
                studyGroupDTOSet = studyGroupDAO.getDTOs();
            } catch (JAXBException e) {
                throw new DAOException(e);
            }

            for (StudyGroupDTO studyGroupDTO : studyGroupDTOSet) {
                studyGroups.add(StudyGroup.getStudyGroup(studyGroupDTO));
            }
            return studyGroups;
        }

        return new TreeSet<>(studyGroupComparator);
    }

    /**
     * Adds a Study Group to the collection using StudyGroupDTO, if it isn't there.
     * If it is in the collection, or it is null, then it isn't added.
     * @param studyGroupDTO
     * @throws StudyGroupRepositoryException
     */
    @Override
    public void add(StudyGroupDTO studyGroupDTO) throws StudyGroupRepositoryException {

        StudyGroup studyGroup;
        try {
            studyGroup = studyGroupFactory.createNewStudyGroup(studyGroupDTO);
        } catch (VerifyException | CreationException e) {
            throw new StudyGroupRepositoryException(INTERNAL_ERROR_MESSAGE);
        }

        if (studyGroup == null) {
            throw new StudyGroupRepositoryException(TRYING_ADD_NULL_GROUP_ERROR_MESSAGE);
        }

        if (!studyGroups.add(studyGroup)) {
            throw new StudyGroupRepositoryException(SUCH_GROUP_EXIST_ERROR_MESSAGE);
        }
    }

    /**
     * Method for deleting a Study Group from the collection.
     * @param studyGroup
     * @throws StudyGroupRepositoryException
     */
    @Override
    public void remove(StudyGroup studyGroup) throws StudyGroupRepositoryException {
        if (!studyGroups.remove(studyGroup)){
            throw new StudyGroupRepositoryException(NO_SUCH_STUDY_GROUP_ERROR_MESSAGE);
        }
    }

    /**
     * Updates Study Group in the collection.
     * By replacing it with a new Study Group.
     * @param studyGroup
     * @throws StudyGroupRepositoryException
     */
    @Override
    public void update(StudyGroup studyGroup) throws StudyGroupRepositoryException {
        StudyGroup studyGroupExist = findStudyGroup(studyGroup);

        if (studyGroupExist == null){
            throw new StudyGroupRepositoryException(NO_SUCH_STUDY_GROUP_ERROR_MESSAGE);
        }

        studyGroups.remove(studyGroupExist);

        if(!studyGroups.add(studyGroup)){
            throw new StudyGroupRepositoryException(INTERNAL_ERROR_MESSAGE);
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

    /**
     * Get set of Study Group from the collection.
     * @param concreteSet
     * @return
     * @throws StudyGroupRepositoryException
     */
    @Override
    public Set<StudyGroup> getConcreteSetOfStudyGroups(ConcreteSet concreteSet) throws StudyGroupRepositoryException{
        if (studyGroups.isEmpty()){
            return new TreeSet<>();
        }

        return concreteSet.execute(studyGroups);
    }

    /**
     * Saves Study Groups using StudyGroupDAO
     * @throws DAOException
     */
    @Override
    public void save() throws DAOException {
        Set<StudyGroupDTO> studyGroupDTOSet = new LinkedHashSet<>();
        for (StudyGroup studyGroup : studyGroups) {
            studyGroupDTOSet.add(getStudyGroupDTO(studyGroup));
        }
        studyGroupDAO.saveDTOs(studyGroupDTOSet);
    }

}
