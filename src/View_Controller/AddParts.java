package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddParts extends Part {

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

        savePartsButton.disableProperty().bind(Bindings.isEmpty(partNameField.textProperty())
                .or(Bindings.isEmpty(partInvField.textProperty())).or(Bindings.isEmpty(partPriceField.textProperty())
                        .or(Bindings.isEmpty(partMaxField.textProperty()).or(Bindings.isEmpty(partMinField.textProperty()))
                        .or(Bindings.isEmpty(machineIdField.textProperty())
                                .and(Bindings.isEmpty(companyNameField.textProperty()))))));
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
            try {
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

                Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
                saveSuccess.setContentText(partName + " added successfully.");
                saveSuccess.show();

                savePartsButton.getScene().getWindow().hide();

            } catch(Exception e) {
                Alert wrongInput = new Alert(Alert.AlertType.ERROR);
                wrongInput.setContentText("Invalid input type. \n" +
                        "Please enter a string for part name, and numbers for all other fields.");
                wrongInput.showAndWait();
            }
        }

        if (this.partsGroup.getSelectedToggle().equals(this.outsourced)) {
            try {
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

                Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
                saveSuccess.setContentText(partName + " added successfully.");
                saveSuccess.show();

                savePartsButton.getScene().getWindow().hide();

            } catch(Exception e) {
                Alert wrongInput = new Alert(Alert.AlertType.ERROR);
                wrongInput.setContentText("Invalid input type. \n" +
                        "Please enter strings for part name and company name, and numbers for all other fields.");
                wrongInput.showAndWait();
            }
        }

//        Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
//        saveSuccess.setContentText(partName + " added successfully.");
//        saveSuccess.show();
//
//        savePartsButton.getScene().getWindow().hide();
    }

    @FXML
    private void handleCancelPartsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();

//        cancelPartsButton.getScene().getWindow().hide();
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
