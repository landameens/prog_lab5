package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.ConcreteSet;
import domain.studyGroupRepository.concreteSet.MinSet;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AddIfMinCommand extends StudyGroupRepositoryCommand {
    public AddIfMinCommand(String type,
                           Map<String, String> args,
                           IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute(Query query) {

        Integer studentsCount = Integer.parseInt(args.get("studentsCount"));

        try {
            ConcreteSet minSet = new MinSet();
            Set<StudyGroup> minGroup = studyGroupRepository.getConcreteSetOfStudyGroups(minSet);
            boolean canBeAdded = true;
            for (StudyGroup studyGroup : minGroup){
                if (studyGroup.getStudentsCount() < studentsCount){
                    responseDTO.answer = "Существует группа с меньшим числом студентов, довавление невозможно.";
                    responseDTO.status = Status.PRECONDITION_FAILED.getCode();
                    canBeAdded = false;
                }
            }
//            Iterator<StudyGroup> it = minGroup.iterator();
//            while(it.hasNext()){
//                if (it.next().getStudentsCount()<studentsCount){
//
//                }
//            }

            if (canBeAdded){
                CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
                coordinatesDTO.x = Integer.parseInt(args.get("xCoordinate"));
                coordinatesDTO.y = Integer.parseInt(args.get("yCoordinate"));

                PersonDTO personDTO = new PersonDTO();
                personDTO.passportID = args.get("groupAdminPassportID");
                personDTO.name = args.get("groupAdminName");
                personDTO.nationality = args.get("groupAdminNationality");
                personDTO.height = Integer.parseInt(args.get("groupAdminHeight"));

                StudyGroupDTO studyGroupDTO = new StudyGroupDTO();
                studyGroupDTO.name =  args.get("StudyGroupName");
                studyGroupDTO.coordinates = coordinatesDTO;
                studyGroupDTO.studentsCount = Integer.parseInt(args.get("studentsCount"));
                studyGroupDTO.shouldBeExpelled = Long.parseLong(args.get("shouldBeExpelled"));
                studyGroupDTO.formOfEducation = args.get("formOfEducation");
                studyGroupDTO.semesterEnum = args.get("semesterEnum");
                studyGroupDTO.groupAdmin = personDTO;

                studyGroupRepository.add(studyGroupDTO);
                responseDTO.answer = "Группа добавлена";
                responseDTO.status = Status.SUCCESSFULLY.getCode();
            }

        } catch (StudyGroupRepositoryException e) {
            responseDTO.answer = e.getMessage();
            responseDTO.status = Status.INTERNAL_ERROR.getCode();
        }

        return Response.getResponse(responseDTO);

    }
}
