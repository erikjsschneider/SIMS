package sims;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddParts {

    @FXML
    public RadioButton inhouse;

    @FXML
    public RadioButton outsourced;

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

    private ToggleGroup partsGroup;

    @FXML
    private void initialize() {
        partsGroup = new ToggleGroup();
        this.inhouse.setToggleGroup(partsGroup);
        this.outsourced.setToggleGroup(partsGroup);
    }

    public void handleSavePartsButton(ActionEvent actionEvent) throws IOException {
        if (this.partsGroup.getSelectedToggle().equals(this.inhouse)
                || this.partsGroup.getSelectedToggle().equals(this.outsourced)) {
            this.savePartsButton.setDisable(false);
        } else {
            this.savePartsButton.setDisable(true);
        }

    }

    public void handleCancelPartsButton(ActionEvent actionEvent) throws IOException {
//        this.addPartStage.hide();
        Stage stage = (Stage) cancelPartsButton.getScene().getWindow();
        stage.hide();
    }

//    public void partsTypeRadioButton() {
//        if (inhouse.isSelected()) {
//            machineIdText.setVisible(true);
//            machineIdField.setVisible(true);
//            companyNameText.setVisible(false);
//            companyNameField.setVisible(false);
//        } else if (outsourced.isSelected()) {
//            companyNameText.setVisible(true);
//            companyNameField.setVisible(true);
//            machineIdText.setVisible(false);
//            machineIdField.setVisible(false);
//        }
//    }

    public void partsTypeRadioButtonChanged() throws IOException {
        if (this.partsGroup.getSelectedToggle().equals(this.inhouse)) {
            machineIdText.setVisible(true);
            machineIdField.setVisible(true);
            companyNameText.setVisible(false);
            companyNameField.setVisible(false);
        }

        if (this.partsGroup.getSelectedToggle().equals(this.outsourced)) {
            companyNameText.setVisible(true);
            companyNameField.setVisible(true);
            machineIdText.setVisible(false);
            machineIdField.setVisible(false);
        }
    }
}
