package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pineappleCheckBox;
    @FXML private CheckBox baconCheckBox;
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;
    @FXML private ComboBox comboBox;
    @FXML private Label comboboxLabel;
    @FXML private RadioButton cpp;
    @FXML private RadioButton java;
    @FXML private  RadioButton Python;
    @FXML private  RadioButton Ruby;
    @FXML private Label RadioButtonLable;
    private ToggleGroup Languages;
    @FXML private  ListView listView;
    @FXML private  TextArea textArea;

    public void pizzaOrderButtonPushed(){
        String Order = "Toppings are:";

        if(pepperoniCheckBox.isSelected()){
            Order += "\n Pepperoni";
        }
        if(pineappleCheckBox.isSelected()){
            Order += "\n Pineapple";
        }
        if (baconCheckBox.isSelected()){
            Order += "\n Bacon";
        }
        this.pizzaOrderLabel.setText(Order);
    }
    public void favrioutbuttonpushed(){

        this.choiceBoxLabel.setText(choiceBox.getValue().toString());

    }

    public void ComboBoxUpdated(){
        this.comboboxLabel.setText(comboBox.getValue().toString());
    }

    public void onButtonSelected(){
        if(this.Languages.getSelectedToggle().equals(this.cpp)){
            RadioButtonLable.setText("CPP");
        }
        if(this.Languages.getSelectedToggle().equals(this.java)){
            RadioButtonLable.setText("Java");
        }
        if(this.Languages.getSelectedToggle().equals(this.Python)){
            RadioButtonLable.setText("Python");
        }
        if(this.Languages.getSelectedToggle().equals(this.Ruby)){
            RadioButtonLable.setText("Ruby");
        }
    }
    public void onListViewButtonPushed(){
            StringBuilder sb = new StringBuilder();
        ObservableList observableList = listView.getSelectionModel().getSelectedItems();
        for(Object item : observableList){
            sb.append((String) item+"\n");
        }

        textArea.setText(sb.toString());
    }
    public void onTableButtonPushed(ActionEvent event){
        URL url = null;

        try{
            url = Paths.get("src/table/Table.fxml").toUri().toURL();

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
        pizzaOrderLabel.setText("");
        choiceBoxLabel.setText("");
        comboboxLabel.setText("");
        RadioButtonLable.setText("");

        choiceBox.getItems().addAll("apple","bannana","watermellon","orange","dragonfruit");
        choiceBox.setValue(choiceBox.getItems().get(0));

        comboBox.getItems().addAll("CSE 105","CSE 205","MAT 502");
        comboBox.setValue(comboBox.getItems().get(0));

        Languages = new ToggleGroup();
        this.cpp.setToggleGroup(Languages);
        this.java.setToggleGroup(Languages);
        this.Python.setToggleGroup(Languages);
        this.Ruby.setToggleGroup(Languages);

        listView.getItems().addAll("PUBG","Call of Duty","Battlefield","GTA");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
}
