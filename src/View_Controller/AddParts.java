package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AddParts {

    @FXML
    private RadioButton inhouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInvField;

    @FXML
    private TextField partPriceField;

    @FXML
    private TextField partMaxField;

    @FXML
    private TextField partMinField;

    @FXML
    private Label companyNameText;

    @FXML
    private TextField companyNameField;

    @FXML
    private Label machineIdText;

    @FXML
    private TextField machineIdField;

    @FXML
    private Button savePartsButton;

    @FXML
    private Button cancelPartsButton;

    private ToggleGroup partsGroup;

    private int partId;

    @FXML
    private void initialize() {
        partsGroup = new ToggleGroup();
        this.inhouse.setToggleGroup(partsGroup);
        this.outsourced.setToggleGroup(partsGroup);

//        savePartsButton.disableProperty().bind(Bindings.isEmpty(partNameField.textProperty())
//                .or(Bindings.isEmpty(partInvField.textProperty())).or(Bindings.isEmpty(partPriceField.textProperty())
//                        .or(Bindings.isEmpty(partMaxField.textProperty()).or(Bindings.isEmpty(partMinField.textProperty()))
//                        .or(Bindings.isEmpty(machineIdField.textProperty())
//                                .and(Bindings.isEmpty(companyNameField.textProperty()))))));
    }

    private void generatePartId() throws IOException {

    }

    @FXML
    private void handleSavePartsButton() throws IOException {
        String partName = partNameField.getText();
        String partInv = partInvField.getText();
        String partPrice = partPriceField.getText();
        String partMax = partMaxField.getText();
        String partMin = partMinField.getText();
        partId++;

        if (this.partsGroup.getSelectedToggle().equals(this.inhouse)) {
            String machineId = machineIdField.getText();

            Inhouse inhousePart = new Inhouse();

            inhousePart.setId(partId);
            inhousePart.setName(partName);
            inhousePart.setStock(Integer.parseInt(partInv));
            inhousePart.setPrice(Double.parseDouble(partPrice));
            inhousePart.setMax(Integer.parseInt(partMax));
            inhousePart.setMin(Integer.parseInt(partMin));
            inhousePart.setMachineId(Integer.parseInt(machineId));

            Inventory.addPart(inhousePart);
        }

        if (this.partsGroup.getSelectedToggle().equals(this.outsourced)) {
            String companyName = companyNameField.getText();

            Outsourced outsourcedPart = new Outsourced();

            outsourcedPart.setId(partId);
            outsourcedPart.setName(partName);
            outsourcedPart.setStock(Integer.parseInt(partInv));
            outsourcedPart.setPrice(Double.parseDouble(partPrice));
            outsourcedPart.setMax(Integer.parseInt(partMax));
            outsourcedPart.setMin(Integer.parseInt(partMin));
            outsourcedPart.setCompanyName(companyName);

            Inventory.addPart(outsourcedPart);
        }

        Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        saveSuccess.setContentText(partName + " added successfully.");
        saveSuccess.show();

        savePartsButton.getScene().getWindow().hide();
    }

    @FXML
    private void handleCancelPartsButton() throws IOException {
        cancelPartsButton.getScene().getWindow().hide();
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
