package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModifyProducts {

    @FXML


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

    public void handleSaveProductsButton(ActionEvent actionEvent) {

    }

    @FXML
    public void handleCancelProductsButton() {
        cancelProductsButton.getScene().getWindow().hide();
    }

}
