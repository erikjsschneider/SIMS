package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
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
        String p = partSearchField.getText();

        ObservableList<Part> partSearched = searchParts(p);

        if (partSearched.size() == 0) {
            try {
                int pId = Integer.parseInt(p);
                Part pIdSearch = getIdSearch(pId);
                if (pIdSearch != null) {
                    partSearched.add(pIdSearch);
                }
//                int pInv = Integer.parseInt(p);
//                Part pInvSearch = getInvSearch(pInv);
//                if (pInvSearch != null) {
//                    partSearched.add(pInvSearch);
//                }
//                double pPrice = Double.parseDouble(p);
//                Part pPriceSearch = getPriceSearch(pPrice);
//                if (pPriceSearch != null) {
//                    partSearched.add(pPriceSearch);
//                }
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }

//        if (partSearched.size() == 0) {
//            try {
//                int pInv = Integer.parseInt(p);
//                Part pInvSearch = getInvSearch(pInv);
//                if (pInvSearch != null) {
//                    partSearched.add(pInvSearch);
//                }
//            }
//            catch (NumberFormatException e) {
//                // ignore
//            }
//        }
//
//        if (partSearched.size() == 0) {
//            try {
//                double pPrice = Double.parseDouble(p);
//                Part pPriceSearch = getPriceSearch(pPrice);
//                if (pPriceSearch != null) {
//                    partSearched.add(pPriceSearch);
//                }
//            }
//            catch (NumberFormatException e) {
//                // ignore
//            }
//        }

        partsTable.setItems(partSearched);
        partSearchField.setText("");

    }

    private ObservableList<Part> searchParts(String partial) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getName().contains(partial)) {
                results.add(part);
            }
        }

        return results;
    }

    private Part getIdSearch (int pId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (int i = 0; i < allParts.size(); i++) {
            Part pIdSearch = allParts.get(i);

            if (pIdSearch.getId() == pId) {
                return pIdSearch;
            }
        }

        return null;
    }

//    private Part getInvSearch (int pInv) {
//        ObservableList<Part> allParts = Inventory.getAllParts();
//
//        for (int i = 0; i < allParts.size(); i++) {
//            Part pIdSearch = allParts.get(i);
//
//            if (pIdSearch.getStock() == pInv) {
//                return pIdSearch;
//            }
//        }
//
//        return null;
//    }
//
//    private Part getPriceSearch (double pPrice) {
//        ObservableList<Part> allParts = Inventory.getAllParts();
//
//        for (int i = 0; i < allParts.size(); i++) {
//            Part pIdSearch = allParts.get(i);
//
//            if (pIdSearch.getPrice() == pPrice) {
//                return pIdSearch;
//            }
//        }
//
//        return null;
//    }

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

    @FXML
    public void modifyPartButton(ActionEvent actionEvent) throws IOException {
        Part selectedPartMod = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPartMod == null) {
            return;
        }

        

        Parent modPartLoader = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(modPartLoader, 600, 600);
        stage.setTitle("Add Parts");
        stage.setScene(scene);
        stage.show();
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
    }
}
