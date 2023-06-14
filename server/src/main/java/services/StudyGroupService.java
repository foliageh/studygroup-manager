package services;

import exceptions.NotFound;
import exceptions.PermissionDenied;
import exceptions.ServerException;
import models.Person;
import models.StudyGroup;
import models.User;
import repositories.StudyGroupRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

/** Класс, осуществляющий бизнес-логику команд.
 * Вызывается View, валидирует переданные параметры и кидает исключения. */
public class StudyGroupService {
    private final StudyGroupRepository repository = StudyGroupRepository.getInstance();

    private static final StudyGroupService instance = new StudyGroupService();
    private StudyGroupService() {}
    public static StudyGroupService getInstance() {
        return instance;
    }

    public void create(StudyGroup studyGroup, User user) {
        studyGroup.setCreator(user);
        repository.create(studyGroup);
    }

    public void update(long id, StudyGroup studyGroup, User user) throws ServerException {
        StudyGroup oldStudyGroup = repository.findById(id);
        if (oldStudyGroup == null)
            throw new NotFound("Group with id "+id+" doesn't exist.");
        if (!oldStudyGroup.getCreator().equals(user))
            throw new PermissionDenied("You don't have permission to update this group.");
        studyGroup.setId(id);
        studyGroup.setCreator(oldStudyGroup.getCreator());
        studyGroup.setCreationDate(oldStudyGroup.getCreationDate());
        repository.update(studyGroup);
    }

    public void removeById(long id, User user) throws ServerException {
        StudyGroup studyGroup = repository.findById(id);
        if (studyGroup == null)
            throw new NotFound("Group with id "+id+" doesn't exist.");
        if (!studyGroup.getCreator().equals(user))
            throw new PermissionDenied("You don't have permission to remove this group.");
        repository.remove(studyGroup);
    }

    public void removeGreater(StudyGroup studyGroup, User user) {
        repository.filterByCreator(user).filter(sg -> sg.compareTo(studyGroup) > 0).forEach(repository::remove);
    }

    public void removeLower(StudyGroup studyGroup, User user) {
        repository.filterByCreator(user).filter(sg -> sg.compareTo(studyGroup) < 0).forEach(repository::remove);
    }

    public void clear(User user) {
        repository.filterByCreator(user).forEach(repository::remove);
    }

    public String show() {
        return streamToString(repository.findAll());
    }

    public String filterByGroupAdmin(Person admin) {
        return streamToString(repository.filterByGroupAdmin(admin));
    }

    public String filterContainsName(String name) {
        return streamToString(repository.filterContainsName(name));
    }

    public String uniqueStudentsCountList() {
        return streamToString(repository.getUniqueStudentsCountList());
    }

    public String info() {
        int collectionSize = repository.getCollectionSize();
        Date lastSaveTime = repository.getLastSaveTime();
        return "Collection size: " + collectionSize + '\n' +
               "Last save time: " + (lastSaveTime != null ? new SimpleDateFormat("HH:mm dd.MM.yyyy").format(lastSaveTime) : "No saves yet");
    }

    private static String streamToString(Stream<?> stream) {
        String result = stream.map(Object::toString).reduce("", (a, b) -> a + "\n" + b).stripLeading();
        return result.isBlank() ? "No elements found." : result;
    }
}
