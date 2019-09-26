package table;

import database.DatabaseConnector;
import detailedview.DetailedViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pojo.Person;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML TableView<Person> tableView;
    @FXML
    TableColumn<Person, String> firstnameColumm;
    @FXML
    TableColumn<Person,String> lastnameColumn;
    @FXML
    TableColumn<Person, LocalDate> birthdayColumn;

    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    DatePicker birthday;

    @FXML
    Button detailedviewbutton;

    public void onSampleButtonPushed(ActionEvent event){
        URL url = null;

        try{
            url = Paths.get("src/sample/sample.fxml").toUri().toURL();

        }
        catch (MalformedURLException ex){
            ex.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        Parent parent = null;
        try {
            parent=loader.load(url);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        Scene TableScene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(TableScene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstnameColumm.setCellValueFactory(new PropertyValueFactory<Person,String >("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Person,String >("lastname"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person,LocalDate>("birthday"));
        ObservableList<Person> observableList = FXCollections.observableArrayList();



        try {
            Connection connection = DatabaseConnector.getConnnection();
            ResultSet re = connection.createStatement().executeQuery("select * FROM  data ");
            while (re.next()){
                observableList.add(new Person(re.getString("firstname"),re.getString("lastname"),re.getDate("birthday").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(observableList);
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        firstnameColumm.setCellFactory(TextFieldTableCell.forTableColumn());
        lastnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detailedviewbutton.setDisable(true);
    }

    public void userclickstable(){
        detailedviewbutton.setDisable(false);
    }

    public ObservableList<Person> getPeople(){
        ObservableList<Person> observableList = FXCollections.observableArrayList();

        observableList.add(new Person("Frank","Castle",LocalDate.of(1965, Month.DECEMBER,12),new Image("frank-castle.jpg")));
        observableList.add(new Person("Jim","Gordan",LocalDate.of(1965, Month.DECEMBER,12)));
        observableList.add(new Person("Bruce","Wane",LocalDate.of(1965, Month.DECEMBER,12)));
        observableList.add(new Person("Frank","Castle",LocalDate.of(1965, Month.DECEMBER,12)));
        observableList.add(new Person("Frank","Castle",LocalDate.of(1965, Month.DECEMBER,12)));
        return observableList;
    }

    public void changeFirstNameCellEvent(TableColumn.CellEditEvent cellEditEvent){
        Person personselected = tableView.getSelectionModel().getSelectedItem();
        personselected.setFirstname(cellEditEvent.getNewValue().toString());
    }
    public void changeLastNameCellEvent(TableColumn.CellEditEvent cellEditEvent){
        Person personselected = tableView.getSelectionModel().getSelectedItem();
        personselected.setLastname(cellEditEvent.getNewValue().toString());
    }
    public void addnewPerson() throws SQLException {
    Person newPerson = new Person(firstname.getText(),lastname.getText(),birthday.getValue());
    Connection connection = DatabaseConnector.getConnnection();
    String sql = "INSERT INTO `data` (`firstname`, `lastname`, `birthday`) VALUES ('"+firstname.getText()+"', '"+lastname.getText()+"', '"+ Date.valueOf(birthday.getValue())
            +"');";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    tableView.getItems().add(newPerson);
    }
    public void deleteSelectedPeople(){

        ObservableList<Person> allpeople,selectedPeople;

        allpeople = tableView.getItems();
        selectedPeople=tableView.getSelectionModel().getSelectedItems();

        for(Person person: selectedPeople){
            allpeople.remove(person);
        }

    }
    public void detailedPersonbuttonpushed(ActionEvent event){
        URL url = null;
        try {
            url = Paths.get("src/detailedview/detailedview.fxml")
                    .toUri().toURL();
        } catch (MalformedURLException ex) {
        ex.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(url);

        Parent parent = null;
        try {
            parent =loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene studentDashboardScene = new Scene(parent);
        DetailedViewController controller = loader.getController();

        controller.init(tableView.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();
    }
}
