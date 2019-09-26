package detailedview;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pojo.Person;


import java.net.URL;
import java.util.ResourceBundle;

public class DetailedViewController implements Initializable {

@FXML Label firstnametext;
@FXML Label lastnametext;
@FXML Label birthdaytext;
@FXML
ImageView photo;


public void init(Person person){
    firstnametext.setText(person.getFirstname());
    lastnametext.setText(person.getLastname());
    birthdaytext.setText(person.getBirthday().toString());
    photo.setImage(person.getPhoto());
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
