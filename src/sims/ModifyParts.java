package sims;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifyParts {

    @FXML
    public RadioButton inhouse;

    @FXML
    public RadioButton outsourced;

    @FXML
    public Text companyNameText;

    @FXML
    public TextField companyNameField;
    @FXML
    public Text machineIdText;

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
//        if (outsourced.isSelected()) {
//            companyNameText.setVisible(true);
//            companyNameField.setVisible(true);
//        }
    }

    @FXML
    public void handleSavePartsButton(ActionEvent actionEvent) {

    }

    @FXML
    public void handleCancelPartsButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPartsButton.getScene().getWindow();
        stage.hide();
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
