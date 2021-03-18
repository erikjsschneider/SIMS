package View_Controller;

import Model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProducts implements Initializable {

    public Inventory inventory;

    @FXML
    private TableView<String> addPartTable;

    @FXML
    private TableView<String> deletePartTable;

    public Button searchProductsButton;
    public Button addProductsButton;
    public Button deleteProductsButton;
    public Button saveProductsButton;
    public Button cancelProductsButton;

    public void handleSearchProductsButton(ActionEvent actionEvent) {

    }

    public void handleAddProductsButton(ActionEvent actionEvent) {

    }

    public void handleDeleteProductsButton(ActionEvent actionEvent) {

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
            deleteProductsButton.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPartTable.getItems();
    }
}
