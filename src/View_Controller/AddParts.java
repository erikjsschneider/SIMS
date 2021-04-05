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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

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

    public static int greatestPartId = 0;

    DecimalFormat currencyFormat = new DecimalFormat("###,##0.00");

    @FXML
    private void initialize() {
        partsGroup = new ToggleGroup();
        this.inhouse.setToggleGroup(partsGroup);
        this.outsourced.setToggleGroup(partsGroup);
        inhouse.setSelected(true);
        machineIdText.setVisible(true);
        machineIdField.setVisible(true);

        savePartsButton.disableProperty().bind(Bindings.isEmpty(partNameField.textProperty())
                .or(Bindings.isEmpty(partInvField.textProperty())).or(Bindings.isEmpty(partPriceField.textProperty())
                        .or(Bindings.isEmpty(partMaxField.textProperty()).or(Bindings.isEmpty(partMinField.textProperty()))
                        .or(Bindings.isEmpty(machineIdField.textProperty())
                                .and(Bindings.isEmpty(companyNameField.textProperty()))))));
    }

    private int generatePartId() throws IOException {
        for (int i = 0; i < Inventory.getAllParts().stream().count(); i++) {
            if (Inventory.getAllParts().get(i).getId() > greatestPartId) {
                greatestPartId = Inventory.getAllParts().get(i).getId();
                System.out.println("For if assignment = " + greatestPartId);
            }
        }

        System.out.println("Greatest ID = " + greatestPartId);
        System.out.println("Size array = " + Inventory.getAllParts().size());
        System.out.println("Stream count = " + Inventory.getAllParts().stream().count());
        partId = greatestPartId + 1;
        greatestPartId = partId;
        System.out.println(partId);
        return partId;
    }

    @FXML
    private void handleSavePartsButton(ActionEvent actionEvent) throws IOException {
        String partName = partNameField.getText();
        String partInv = partInvField.getText();
        String partPrice = partPriceField.getText();
        String partMax = partMaxField.getText();
        String partMin = partMinField.getText();

//        for (int i = 0; i < Inventory.getAllParts().stream().count(); i++) {
//            if (Inventory.getAllParts().get(i).getId() > greatestPartId) {
//                greatestPartId = Inventory.getAllParts().get(i).getId();
//                System.out.println("For if assignment = " + greatestPartId);
//            }
//        }

//        System.out.println("Greatest ID = " + greatestPartId);
//        System.out.println("Size array = " + Inventory.getAllParts().size());
//        System.out.println("Stream count = " + Inventory.getAllParts().stream().count());
//        partId = greatestPartId + 1;
//        greatestPartId = partId;
//        System.out.println(partId);


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

                partId = generatePartId();
                inhousePart.setId(partId);
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

                if (outsourcedPart.getStock() > outsourcedPart.getMax() || outsourcedPart.getStock() < outsourcedPart.getMin()) {
                    Alert invalid = new Alert(Alert.AlertType.ERROR);
                    invalid.setContentText("Invalid input amount. \n" +
                            "Please enter an amount within the bounds of max and min.");
                    invalid.showAndWait();
                    return;
                }

                partId = generatePartId();
                outsourcedPart.setId(partId);
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
    private void handleCancelPartsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();
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

    public void enterPressed(KeyEvent keyEvent) throws IOException {
        if (savePartsButton.isVisible()) {
            if ((partNameField != null && partInvField != null && partPriceField != null && partMaxField != null &&
                    partMinField != null && (companyNameField != null || machineIdField != null))
                    && keyEvent.getCode() == KeyCode.ENTER) {
                savePartsButton.fire();
                keyEvent.consume();
            }
        }
    }

    public void updateText(KeyEvent keyEvent) {
        this.partPriceField.setText(currencyFormat.format(Double.valueOf(partPriceField.getText())));
    }
}
