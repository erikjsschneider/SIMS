package View_Controller;

import Model.*;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProducts implements Initializable {

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

    private int productId;

    public static int greatestProductId = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartTable.setItems(allAvailableParts);

        availPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        availPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availInventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availPricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private int generateProductId() throws IOException {
        for (int i = 0; i < Inventory.getAllProducts().stream().count(); i++) {
            if (Inventory.getAllParts().get(i).getId() > greatestProductId) {
                greatestProductId = Inventory.getAllParts().get(i).getId();
                System.out.println("For if assignment = " + greatestProductId);
            }
        }

        System.out.println("Greatest ID = " + greatestProductId);
        System.out.println("Size array = " + Inventory.getAllParts().size());
        System.out.println("Stream count = " + Inventory.getAllParts().stream().count());
        productId = greatestProductId + 1;
        greatestProductId = productId;
        System.out.println(productId);
        return productId;
    }

    @FXML
    public void handleSearchPartsButton(ActionEvent actionEvent) {

    }

//    @FXML
//    public void handleAddPartsButton(ActionEvent actionEvent) {
//
//    }

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
        allSelectedParts.remove(selectedPart);
        deletePartTable.setItems(allSelectedParts);
    }

    @FXML
    public void handleSaveProductsButton(ActionEvent actionEvent) throws IOException {
        String productName = productNameField.getText();
        String productInv = productInvField.getText();
        String productPrice = productPriceField.getText();
        String productMax = productMaxField.getText();
        String productMin = productMinField.getText();

        try {
            Product newProduct = new Product();

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

            productId = generateProductId();
            newProduct.setId(productId);
            Inventory.addProduct(newProduct);

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
        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();
    }
}
