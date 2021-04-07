package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    public Button searchParts;

    @FXML
    public TextField partSearchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        prodTable.setItems(Inventory.getAllProducts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryProd.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceProd.setCellValueFactory(new PropertyValueFactory<>("price"));
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
        String p = partSearchField.getText();

        ObservableList<Part> partSearched = Inventory.lookupPart(p);

        if (partSearched.size() == 0) {
            try {
                int pId = Integer.parseInt(p);
                Part pIdSearch = Inventory.lookupPart(pId);
                System.out.println(pIdSearch);
                if (pIdSearch != null) {
                    partSearched.add(pIdSearch);
                }
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }

        partsTable.setItems(partSearched);
        partSearchField.setText("");

        System.out.println(Inventory.getAllParts());
        System.out.println(partsTable.getItems());

    }

    public void enterPressedPartSearch(KeyEvent keyEvent) throws IOException {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                searchParts.fire();
                keyEvent.consume();
            }
    }

    @FXML
    public void addPartButton(ActionEvent actionEvent) throws IOException {
        Parent addPartRoot = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartRoot, 600, 600);
        stage.setTitle("Add Parts");
        stage.setScene(scene);
        stage.show();

    }

    public Part sendModPart(Part moddedPart) {
        Part selectedPartMod = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPartMod instanceof Inhouse) {
            System.out.println("Inhouse");
            ((Inhouse) selectedPartMod).getMachineId();
            System.out.println(((Inhouse) selectedPartMod).getMachineId());
            return selectedPartMod;
        }
        if (selectedPartMod instanceof Outsourced) {
            System.out.println("Outsourced");
            ((Outsourced) selectedPartMod).getCompanyName();
            System.out.println(((Outsourced) selectedPartMod).getCompanyName());
            return selectedPartMod;
        }

//        ModifyParts.getSelectedPart(selectedPartMod);
        System.out.println(selectedPartMod);
        return selectedPartMod;
    }

    @FXML
    public void modifyPartButton(ActionEvent actionEvent) throws IOException {
        Part selectedPartMod = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPartMod instanceof Inhouse) {
            System.out.println("Inhouse");
            ((Inhouse) selectedPartMod).getMachineId();
            System.out.println(((Inhouse) selectedPartMod).getMachineId());
        }
        if (selectedPartMod instanceof Outsourced) {
            System.out.println("Outsourced");
            ((Outsourced) selectedPartMod).getCompanyName();
            System.out.println(((Outsourced) selectedPartMod).getCompanyName());
        }

//        ModifyParts.getSelectedPart(selectedPartMod);
        System.out.println(selectedPartMod);

        if (selectedPartMod == null) {
            System.out.println("None");
            return;
        }

        
//        try {
            Parent modPartLoader = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(modPartLoader, 600, 600);
            stage.setTitle("Modify Parts");
            stage.setScene(scene);
            stage.show();
//            ModifyParts modifyParts = modPartLoader.getId();
            System.out.println("Showing " + selectedPartMod.getId() + ", " + selectedPartMod.getName() + ", " + selectedPartMod.getPrice() + ", " + selectedPartMod.getStock() + ", " + selectedPartMod.getMax() + ", " + selectedPartMod.getMin());
//        } catch(Exception e) {
//        }
    }

    @FXML
    public void deletePartButton(ActionEvent actionEvent) {
        Part selectedPartDel = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPartDel == null) {
            Alert noDelSelection = new Alert(Alert.AlertType.ERROR);
            noDelSelection.setContentText("No part selected to delete. \n" +
                    "Please select a part to delete.");
            noDelSelection.showAndWait();
            return;
        } else {
            Inventory.getAllParts().remove(selectedPartDel);
            partsTable.setItems(Inventory.getAllParts());
            System.out.println(Inventory.getAllParts());
            System.out.println(partsTable.getItems());
        }
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
        Product selectedProductDel = prodTable.getSelectionModel().getSelectedItem();

        if (selectedProductDel == null) {
            Alert noDelSelection = new Alert(Alert.AlertType.ERROR);
            noDelSelection.setContentText("No product selected to delete. \n" +
                    "Please select a product to delete.");
            noDelSelection.showAndWait();
            return;
        } else {
            Inventory.getAllProducts().remove(selectedProductDel);
            prodTable.setItems(Inventory.getAllProducts());
            System.out.println(Inventory.getAllProducts());
            System.out.println(prodTable.getItems());
        }
    }
}
