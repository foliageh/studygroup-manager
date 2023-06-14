package repositories;

import dao.StudyGroupDao;
import models.Person;
import models.StudyGroup;
import models.User;

import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

/** Класс для взаимодействия с коллекцией через Dao, предоставляющий дополнительный функционал
 * для коллекции, согласно заданию лабораторной работы. */
public class StudyGroupRepository {
    private final ConcurrentSkipListSet<StudyGroup> collection;
    private Date lastSaveTime;

    private final StudyGroupDao studyGroupDao = new StudyGroupDao();

    private static final StudyGroupRepository instance = new StudyGroupRepository();
    private StudyGroupRepository() {
        collection = new ConcurrentSkipListSet<>(studyGroupDao.findAll());
    }
    public static StudyGroupRepository getInstance() {
        return instance;
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public Date getLastSaveTime() {
        return lastSaveTime;
    }

    public Stream<StudyGroup> findAll() {
        return collection.stream().sorted(Comparator.comparing(StudyGroup::getCoordinates));
    }

    public StudyGroup findById(long id) {
        return findAll().filter(sg -> sg.getId() == id).findAny().orElse(null);
    }

    public Stream<StudyGroup> filterByCreator(User user) {
        return findAll().filter(sg -> sg.getCreator().equals(user));
    }

    public Stream<StudyGroup> filterByGroupAdmin(Person groupAdmin) {
        return findAll().filter(sg -> sg.getGroupAdmin().equals(groupAdmin));
    }

    public Stream<StudyGroup> filterContainsName(String name) {
        return findAll().filter(sg -> sg.getName().contains(name));
    }

    public Stream<Long> getUniqueStudentsCountList() {
        return findAll().map(StudyGroup::getStudentsCount).distinct();
    }

    public void create(StudyGroup studyGroup) {
        studyGroupDao.create(studyGroup);
        collection.add(studyGroup);
        lastSaveTime = new Date();
    }

    public void update(StudyGroup studyGroup) {
        studyGroupDao.update(studyGroup);
        collection.remove(studyGroup);
        collection.add(studyGroup);
    }

    public void remove(StudyGroup studyGroup) {
        studyGroupDao.remove(studyGroup);
        collection.remove(studyGroup);
    }
}
