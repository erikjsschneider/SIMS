package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProducts implements Initializable {

    @FXML
    public Button searchAvailablePartsButton;

    @FXML
    public TextField searchAvailableParts;

    @FXML
    public TextField productIdField;

    @FXML
    public TextField productNameField;

    @FXML
    public TextField productInvField;

    @FXML
    public TextField productPriceField;

    @FXML
    public TextField productMaxField;

    @FXML
    public TextField productMinField;

    @FXML
    private TableView<Part> addPartTable;

    @FXML
    private TableView<Part> deletePartTable;

    @FXML
    public TableColumn<Part, Integer> availPartId;

    @FXML
    public TableColumn<Part, String> availPartName;

    @FXML
    public TableColumn<Part, Integer> availInventoryPart;

    @FXML
    public TableColumn<Part, Double> availPricePart;

    @FXML
    public TableColumn<Part, Integer> selPartId;

    @FXML
    public TableColumn<Part, String> selPartName;

    @FXML
    public TableColumn<Part, Integer> selInventoryPart;

    @FXML
    public TableColumn<Part, Double> selPricePart;

    private static ObservableList<Part> allAvailableParts = Inventory.getAllParts();

    private static ObservableList<Part> allSelectedParts = FXCollections.observableArrayList();

    @FXML
    public Button searchPartsButton;

    @FXML
    public Button addPartsButton;

    @FXML
    public Button deletePartsButton;

    @FXML
    public Button saveProductsButton;

    @FXML
    public Button cancelProductsButton;

    private static Product modProduct = null;

    private static int modProductIndex = 0;

    public static void modSelectedPart(Product product) {
        modProduct = product;
    }

    public static void modSelProductIndex(int index) {
        modProductIndex = index;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartTable.setItems(allAvailableParts);

        productIdField.setText(Integer.toString(modProduct.getId()));
        productNameField.setText(modProduct.getName());
        productInvField.setText(Integer.toString(modProduct.getStock()));
        productPriceField.setText(Double.toString(modProduct.getPrice()));
        productMaxField.setText(Integer.toString(modProduct.getMax()));
        productMinField.setText(Integer.toString(modProduct.getMin()));

        for (Part part : modProduct.getAllAssociatedParts()) {
            allSelectedParts.add(part);
            deletePartTable.setItems(allSelectedParts);
        }

        availPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        availPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availInventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availPricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
        selPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        selPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        selInventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selPricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void handleSearchPartsButton(ActionEvent actionEvent) {
        String p = searchAvailableParts.getText();

        ObservableList<Part> partSearched = Inventory.lookupPart(p);

        if (partSearched.size() == 0) {
            try {
                int pId = Integer.parseInt(p);
                Part pIdSearch = Inventory.lookupPart(pId);
                if (pIdSearch != null) {
                    partSearched.add(pIdSearch);
                }
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }

        addPartTable.setItems(partSearched);
        searchAvailableParts.setText("");
    }

    public void enterPressedforSearch(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (searchAvailableParts.isFocused()) {
                searchAvailablePartsButton.fire();
            }
            keyEvent.consume();
        }
    }

    @FXML
    public void handleAddPartsButton(ActionEvent actionEvent) {
        Part selectedPart = addPartTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            allSelectedParts.add(selectedPart);
            deletePartTable.setItems(allSelectedParts);
        }

        selPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        selPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        selInventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selPricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void handleDeletePartsButton(ActionEvent actionEvent) {
        Part selectedPart = deletePartTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert noDelSelection = new Alert(Alert.AlertType.ERROR);
            noDelSelection.setContentText("No part selected to remove. \n" +
                    "Please select a part to remove.");
            noDelSelection.showAndWait();
        } else {
            allSelectedParts.remove(selectedPart);
            deletePartTable.setItems(allSelectedParts);

            Alert deleted = new Alert(Alert.AlertType.CONFIRMATION);
            deleted.setContentText(selectedPart.getName() + " was removed.");
            deleted.showAndWait();
        }
    }

    @FXML
    public void handleSaveProductsButton(ActionEvent actionEvent) {
        String productId = productIdField.getText();
        String productName = productNameField.getText();
        String productInv = productInvField.getText();
        String productPrice = productPriceField.getText();
        String productMax = productMaxField.getText();
        String productMin = productMinField.getText();

        try {
            Product newProduct = new Product();

            newProduct.setId(Integer.parseInt(productId));
            newProduct.setName(productName);
            newProduct.setStock(Integer.parseInt(productInv));
            newProduct.setPrice(Double.parseDouble(productPrice));
            newProduct.setMax(Integer.parseInt(productMax));
            newProduct.setMin(Integer.parseInt(productMin));

            if (newProduct.getStock() > newProduct.getMax() || newProduct.getStock() < newProduct.getMin()) {
                Alert invalid = new Alert(Alert.AlertType.ERROR);
                invalid.setContentText("Invalid input amount. \n" +
                        "Please enter an amount within the bounds of max and min.");
                invalid.showAndWait();
                return;
            }

            for (Part part : allSelectedParts) {
                newProduct.addAssociatedPart(part);
            }


            Inventory.updateProduct(modProductIndex, newProduct);
            Inventory.deleteProduct(modProduct);

            allSelectedParts.clear();

            Alert saveSuccess = new Alert(Alert.AlertType.CONFIRMATION);
            saveSuccess.setContentText(productName + " added successfully.");
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
                    "Please enter a string for product name, and numbers for all other fields.");
            wrongInput.showAndWait();
        }
    }

    @FXML
    public void handleCancelProductsButton(ActionEvent actionEvent) throws IOException {
        Alert cancel = new Alert(Alert.AlertType.INFORMATION);
        cancel.setContentText("Modify product cancelled. Navigating to the main menu.");
        cancel.showAndWait();

        allSelectedParts.clear();

        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();
    }
}
