package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Person implements Model {
    private String name; //cannot be null, cannot be empty
    private Date birthday; //can be null
    private Color hairColor; //cannot be null
    private Location location; //cannot be null

    public Person() {}
    public Person(String name, Date birthday, Color hairColor, Location location) {
        if (name == null || name.isBlank() || hairColor == null || location == null)
            throw new RuntimeException("Invalid person.");
        this.name = name;
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!name.equals(person.name)) return false;
        return Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String birthdayString = birthday != null ? new SimpleDateFormat("dd.MM.yyyy").format(birthday) : "null";
        return String.format("\n    name: %s\n    birthday: %s\n    hairColor: %s\n    location: %s", name, birthdayString, hairColor, location);
    }
}
