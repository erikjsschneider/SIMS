package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TableView<Part> partsTable;

    @FXML
    public TableView<Product> prodTable;

    @FXML
    public TableColumn<Part, Integer> partId;

    @FXML
    public TableColumn<Part, String> partName;

    @FXML
    public TableColumn<Part, Integer> inventoryPart;

    @FXML
    public TableColumn<Part, Double> pricePart;

    @FXML
    public TableColumn<Product, Integer> productId;

    @FXML
    public TableColumn<Product, String> productName;

    @FXML
    public TableColumn<Product, Integer> inventoryProd;

    @FXML
    public TableColumn<Product, Double> priceProd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePart.setCellValueFactory(new PropertyValueFactory<>("price"));

//        TableView<Part> partsTable = new TableView<>(Inventory.getAllParts());
//        partId = new TableColumn<>("Part ID");
//        partId.setCellValueFactory(new PropertyValueFactory<>("partId"));
//        partsTable.getColumns().add(partId);
    }

    @FXML
    public void exit() throws IOException {
        // Pop up a new window and ask if user is sure they want to exit...Yes or No
        FXMLLoader exitVerifyLoader = new FXMLLoader(getClass().getResource("ExitVerify.fxml"));
        Parent exitVerifyRoot = (Parent) exitVerifyLoader.load();
        Stage exitVerifyStage = new Stage();
        exitVerifyStage.initModality(Modality.APPLICATION_MODAL);
        exitVerifyStage.setScene(new Scene(exitVerifyRoot, 400, 400));
        exitVerifyStage.show();
    }

    @FXML
    public void searchPartsButton(ActionEvent actionEvent) {
    }

//    @FXML
//    public void addPartButton(ActionEvent actionEvent) throws IOException {
//        FXMLLoader addPartLoader = new FXMLLoader(getClass().getResource("AddParts.fxml"));
//        Parent addPartRoot = (Parent) addPartLoader.load();
//        Stage addPartStage = new Stage();
//        addPartStage.initModality(Modality.APPLICATION_MODAL);
//        addPartStage.setScene(new Scene(addPartRoot, 600, 600));
//        addPartStage.show();
//
//    }

    @FXML
    public void addPartButton(ActionEvent actionEvent) throws IOException {
        Parent addPartRoot = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartRoot, 600, 600);
        stage.setTitle("Add Parts");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void modifyPartButton(ActionEvent actionEvent) throws IOException {
        Parent modPartLoader = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(modPartLoader, 600, 600);
        stage.setTitle("Add Parts");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deletePartButton(ActionEvent actionEvent) {
    }

    @FXML
    public void searchProductsButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addProductButton(ActionEvent actionEvent) throws IOException {
        Parent modPartLoader = FXMLLoader.load(getClass().getResource("AddProducts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(modPartLoader, 1200, 800);
        stage.setTitle("Add Products");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void modifyProductButton(ActionEvent actionEvent) throws IOException {
        Parent modPartLoader = FXMLLoader.load(getClass().getResource("ModifyProducts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(modPartLoader, 1200, 800);
        stage.setTitle("Modify Products");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteProductButton(ActionEvent actionEvent) {
    }
}
