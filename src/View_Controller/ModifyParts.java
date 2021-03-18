package View_Controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyParts {

    @FXML
    public RadioButton inhouse;

    @FXML
    public RadioButton outsourced;

    @FXML
    public TextField partName;

    @FXML
    public TextField partInv;

    @FXML
    public TextField partPrice;

    @FXML
    public TextField partMax;

    @FXML
    public TextField partMin;

    @FXML
    public Label companyNameText;

    @FXML
    public TextField companyNameField;
    @FXML
    public Label machineIdText;

    @FXML
    public TextField machineIdField;

    @FXML
    public Button savePartsButton;

    @FXML
    public Button cancelPartsButton;

    @FXML
    private void initialize() {
        ToggleGroup partsGroup = new ToggleGroup();
        this.inhouse.setToggleGroup(partsGroup);
        this.outsourced.setToggleGroup(partsGroup);

//        savePartsButton.disableProperty().bind(Bindings.isEmpty(partName.textProperty())
//                .or(Bindings.isEmpty(partInv.textProperty())).or(Bindings.isEmpty(partPrice.textProperty())
//                        .or(Bindings.isEmpty(partMax.textProperty()).or(Bindings.isEmpty(partMin.textProperty()))
//                                .or(Bindings.isEmpty(machineIdField.textProperty())
//                                        .and(Bindings.isEmpty(companyNameField.textProperty()))))));
    }

    @FXML
    public void handleSavePartsButton(ActionEvent actionEvent) {

    }

    @FXML
    public void handleCancelPartsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();
    }

    public void partsTypeRadioButton() {
        if (inhouse.isSelected()) {
            machineIdText.setVisible(true);
            machineIdField.setVisible(true);
        } else if (outsourced.isSelected()) {
            companyNameText.setVisible(true);
            companyNameField.setVisible(true);
        }
    }
}
