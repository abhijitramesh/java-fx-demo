package pojo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Person {
    SimpleStringProperty firstname;
    SimpleStringProperty lastname;
    LocalDate birthday;
    Image photo;


    public Person(String firstname, String lastname, LocalDate birthday) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.birthday = birthday;
        photo = new Image("defaultImage.png");
    }
    public Person(String firstname, String lastname, LocalDate birthday,Image image) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.birthday = birthday;
        this.photo = image;
    }


    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}
