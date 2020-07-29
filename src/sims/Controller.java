package sims;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void searchPartsButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addPartButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader addPartLoader = new FXMLLoader(getClass().getResource("AddParts.fxml"));
        Parent addPartRoot = (Parent) addPartLoader.load();
        Stage addPartStage = new Stage();
        addPartStage.setScene(new Scene(addPartRoot,600,600));
        addPartStage.showAndWait();

    }

    @FXML
    public void modifyPartButton(ActionEvent actionEvent) throws IOException {
        Stage modPartStage = new Stage();
        FXMLLoader modPartLoader = new FXMLLoader();
        Pane addPartRoot = modPartLoader.load(getClass().getResource("ModifyParts.fxml").openStream());
        modPartStage.setScene(new Scene(addPartRoot,600,600));
        modPartStage.showAndWait();
    }

    @FXML
    public void deletePartButton(ActionEvent actionEvent) {
    }

    @FXML
    public void searchProductsButton(ActionEvent actionEvent) {
    }

    @ FXML
    public void addProductButton(ActionEvent actionEvent) throws IOException {
        Stage addProductStage = new Stage();
        FXMLLoader addProductLoader = new FXMLLoader();
        Pane addPartRoot = addProductLoader.load(getClass().getResource("ModifyParts.fxml").openStream());
        addProductStage.setScene(new Scene(addPartRoot,600,600));
        addProductStage.showAndWait();
    }

    @FXML
    public void modifyProductButton(ActionEvent actionEvent) throws IOException {
        Stage modProductStage = new Stage();
        FXMLLoader modProductLoader = new FXMLLoader();
        Pane addPartRoot = modProductLoader.load(getClass().getResource("ModifyParts.fxml").openStream());
        modProductStage.setScene(new Scene(addPartRoot,600,600));
        modProductStage.showAndWait();
    }

    @FXML
    public void deleteProductButton(ActionEvent actionEvent) {
    }
}
