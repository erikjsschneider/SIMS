package View_Controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public void handleCancelPartsButton(ActionEvent actionEvent) {
        cancelPartsButton.getScene().getWindow().hide();
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
