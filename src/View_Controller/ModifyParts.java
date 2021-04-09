package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyParts extends Part {

    @FXML
    public RadioButton inhouse;

    @FXML
    public RadioButton outsourced;

    @FXML
    public TextField partIdField;

    @FXML
    public TextField partNameField;

    @FXML
    public TextField partInvField;

    @FXML
    public TextField partPriceField;

    @FXML
    public TextField partMaxField;

    @FXML
    public TextField partMinField;

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

//    private static Part modPart = null;

    public Part selectedPart;

    public void setSelectedPart(Part selectedPart) {
        this.selectedPart = selectedPart;
//    }
//        modPart = selectedPart;

        if (selectedPart instanceof Inhouse) {
            inhouse.setSelected(true);
            machineIdText.setVisible(true);
            machineIdField.setVisible(true);
            machineIdField.setText((new Integer(((Inhouse) selectedPart).getMachineId())).toString());
        }

        if (selectedPart instanceof Outsourced) {
            outsourced.setSelected(true);
            companyNameText.setVisible(true);
            companyNameField.setVisible(true);
            companyNameField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        partIdField.setText(new Integer(selectedPart.getId()).toString());
        partNameField.setText(selectedPart.getName());
        partInvField.setText(new Integer(selectedPart.getStock()).toString());
        partPriceField.setText(new Double(selectedPart.getPrice()).toString());
        partMaxField.setText(new Integer(selectedPart.getMax()).toString());
        partMinField.setText(new Integer(selectedPart.getMin()).toString());
//        String partName = partNameField.getText();
//        String partInv = partInvField.getText();
//        String partPrice = partPriceField.getText();
//        String partMax = partMaxField.getText();
//        String partMin = partMinField.getText();
    }

    @FXML
    private void initialize() {
        partsGroup = new ToggleGroup();
        this.inhouse.setToggleGroup(partsGroup);
        this.outsourced.setToggleGroup(partsGroup);

//        savePartsButton.disableProperty().bind(Bindings.isEmpty(partNameField.textProperty())
//                .or(Bindings.isEmpty(partInvField.textProperty())).or(Bindings.isEmpty(partPriceField.textProperty())
//                        .or(Bindings.isEmpty(partMaxField.textProperty()).or(Bindings.isEmpty(partMinField.textProperty()))
//                                .or(Bindings.isEmpty(machineIdField.textProperty())
//                                        .and(Bindings.isEmpty(companyNameField.textProperty()))))));

//        String result =
    }

    @FXML
    public void handleSavePartsButton(ActionEvent actionEvent) {
        String partName = partNameField.getText();
        String partInv = partInvField.getText();
        String partPrice = partPriceField.getText();
        String partMax = partMaxField.getText();
        String partMin = partMinField.getText();



        if (this.partsGroup.getSelectedToggle().equals(this.inhouse)) {
            try {
                String machineId = machineIdField.getText();

                Inhouse inhousePart = new Inhouse();

                inhousePart.setName(partName);
                inhousePart.setStock(Integer.parseInt(partInv));
                inhousePart.setPrice(Double.parseDouble(partPrice));
                inhousePart.setMax(Integer.parseInt(partMax));
                inhousePart.setMin(Integer.parseInt(partMin));
                inhousePart.setMachineId(Integer.parseInt(machineId));

                if (inhousePart.getStock() > inhousePart.getMax() || inhousePart.getStock() < inhousePart.getMin()) {
                    Alert invalid = new Alert(Alert.AlertType.ERROR);
                    invalid.setContentText("Invalid input amount. \n" +
                            "Please enter an amount within the bounds of max and min.");
                    invalid.showAndWait();
                    return;
                }

                Inventory.addPart(inhousePart);

                Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
                saveSuccess.setContentText(partName + " added successfully.");
                saveSuccess.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1200, 600);
                stage.setTitle("S.I.M.S.");
                stage.setScene(scene);
                stage.show();

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

                outsourcedPart.setName(partName);
                outsourcedPart.setStock(Integer.parseInt(partInv));
                outsourcedPart.setPrice(Double.parseDouble(partPrice));
                outsourcedPart.setMax(Integer.parseInt(partMax));
                outsourcedPart.setMin(Integer.parseInt(partMin));
                outsourcedPart.setCompanyName(companyName);

                Inventory.addPart(outsourcedPart);

                Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
                saveSuccess.setContentText(partName + " added successfully.");
                saveSuccess.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1200, 600);
                stage.setTitle("S.I.M.S.");
                stage.setScene(scene);
                stage.show();

            } catch(Exception e) {
                Alert wrongInput = new Alert(Alert.AlertType.ERROR);
                wrongInput.setContentText("Invalid input type. \n" +
                        "Please enter strings for part name and company name, and numbers for all other fields.");
                wrongInput.showAndWait();
            }
        }

    }

    @FXML
    public void handleCancelPartsButton(ActionEvent actionEvent) throws IOException {
        Alert cancel = new Alert(Alert.AlertType.INFORMATION);
        cancel.setContentText("Modify part cancelled. Navigating to the main menu.");
        cancel.showAndWait();

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
