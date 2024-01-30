package com.example.questionThree;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PNetController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Add options to combobox
        ObservableList<String> list = FXCollections.observableArrayList("1 - 5", "6 - 10", "11 - 20");
        experienceCombo.setItems(list);
    }

    @FXML
    private Button applyButton;

    @FXML
    private ComboBox<String> experienceCombo;

    @FXML
    private TextField nameText;

    @FXML
    private TextField positionText;

    //event-handler for the combobox
    public void SelectExperience(ActionEvent actionEvent) {
        String s = experienceCombo.getSelectionModel().getSelectedItem();
    }

    //declare local variable
    String username;
    String userPosition;

    @FXML
    public void applyOnClick(ActionEvent event) throws IOException {

        //local variables are equal to the data entered within the textfield
        username = nameText.getText();
        userPosition = positionText.getText();

        try {
            //if the user has failed to make a selection for any of the 3 fields, an alert will display and the application will not be sent
            if (username.isEmpty() || userPosition.isEmpty() || experienceCombo.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("One or more fields are empty");
                alert.showAndWait();
            }else {
                //If the user tries to enter a numerical value, an alert will display
                if (username.matches("[0-9]") || userPosition.matches("[0-9]")) {
                    Alert alertInt = new Alert(Alert.AlertType.ERROR);
                    alertInt.setTitle("Error");
                    alertInt.setHeaderText("Please avoid the user of numerical characters");
                    alertInt.showAndWait();
                } else {
                    //if all fields are completed correctly an alert will display with the details entered
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Application Sent");
                    alert.setHeaderText("Application Details");
                    alert.setContentText("Applicant Name: " + nameText.getText() + "\nPosition Applied: " + positionText.getText() + "\nYears of Experience: " + experienceCombo.getSelectionModel().getSelectedItem());
                    alert.showAndWait();
                    //upon a successful application submission, the app will restart so the user can send another application if they wish
                    System.out.println("Application restarted");
                    Stage stage = (Stage) applyButton.getScene().getWindow();
                    stage.close();
                    new PNetApplication().start(new Stage());
                }
            }
            //exception catch
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

