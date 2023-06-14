package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudyGroup implements Model, Comparable<StudyGroup> {
    private long id; //cannot be null, must be greater than 0, must be unique, must be generated automatically
    private String name; //cannot be null, cannot be empty
    private Coordinates coordinates; //cannot be null
    private Date creationDate; //cannot be null, must be generated automatically
    private Long studentsCount; //must be greater than 0, can be null
    private FormOfEducation formOfEducation; //cannot be null
    private Semester semester; //cannot be null
    private Person groupAdmin; //cannot be null
    private User creator;

    public StudyGroup() {}
    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, FormOfEducation formOfEducation, Semester semester, Person groupAdmin) {
        if (name == null || coordinates == null || formOfEducation == null || semester == null || groupAdmin == null)
            throw new RuntimeException("Invalid person.");
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemester() {
        return semester;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        if (id < 1) throw new RuntimeException("Id must be greater than 0.");
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) throw new RuntimeException("Creation date can't be null.");
        this.creationDate = creationDate;
    }

    public void setCreator(User creator) {
        if (creator == null) throw new RuntimeException("Creator can't be null.");
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        String creationDateString = creationDate != null ? new SimpleDateFormat("dd.MM.yyyy").format(creationDate) : "null";
        return String.format("StudentGroup #%d:\n  id: %d\n  name: %s\n  coordinates: %s\n  creationDate: %s\n  studentsCount: %s\n  formOfEducation: %s\n  semester: %s\n  groupAdmin: %s\n creator: %s",
                id, id, name, coordinates, creationDateString, studentsCount, formOfEducation, semester, groupAdmin, creator);
    }

    @Override
    public int compareTo(StudyGroup o) {
        return Long.compare(id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
