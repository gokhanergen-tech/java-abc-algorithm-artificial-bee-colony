package com.guncetek.abc.app.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Border;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private ChoiceBox<String> choiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       listView.getItems().addAll("fddfsdfsd","fsdfsd");
       listView.setMaxHeight(200);
       listView.setPrefWidth(100);

       choiceBox.setValue("New Page");
       choiceBox.setItems(FXCollections.observableArrayList("New Page","Exit"));


    }
}
