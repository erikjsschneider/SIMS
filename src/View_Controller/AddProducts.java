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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProducts implements Initializable {

    public Inventory inventory;

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


//    private static ObservableList<Part> allAvailableParts = FXCollections.observableArrayList();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartTable.setItems(allAvailableParts);

        availPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        availPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availInventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availPricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
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
        allSelectedParts.add(selectedPart);
        deletePartTable.setItems(allSelectedParts);

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
    public void handleSaveProductsButton() {
//        saveProductsButton.getScene().getWindow().hide();
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

    public void checkProducts() {
        if (inventory.getAllProducts().isEmpty()) {
            deletePartsButton.setDisable(true);
        }
    }
}
