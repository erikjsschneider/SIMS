package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void handleCancelProductsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sims.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("S.I.M.S.");
        stage.setScene(scene);
        stage.show();
    }

}
