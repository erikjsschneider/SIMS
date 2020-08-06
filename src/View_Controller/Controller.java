package View_Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
//        Platform.exit();
    }

    @FXML
    public void searchPartsButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addPartButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader addPartLoader = new FXMLLoader(getClass().getResource("AddParts.fxml"));
        Parent addPartRoot = (Parent) addPartLoader.load();
        Stage addPartStage = new Stage();
        addPartStage.initModality(Modality.APPLICATION_MODAL);
        addPartStage.setScene(new Scene(addPartRoot, 600, 600));
        addPartStage.show();
//        Parent addPartLoader = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
//        Scene addPartScene = new Scene(addPartLoader);
//        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        addPartStage.setScene(addPartScene);
//        addPartStage.show();

    }

    @FXML
    public void modifyPartButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader modPartLoader = new FXMLLoader(getClass().getResource("ModifyParts.fxml"));
        Parent modPartRoot = (Parent) modPartLoader.load();
        Stage modPartStage = new Stage();
        modPartStage.initModality(Modality.APPLICATION_MODAL);
        modPartStage.setScene(new Scene(modPartRoot, 600, 600));
        modPartStage.show();
    }

    @FXML
    public void deletePartButton(ActionEvent actionEvent) {
    }

    @FXML
    public void searchProductsButton(ActionEvent actionEvent) {
    }

    @FXML
    public void addProductButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader addProductLoader = new FXMLLoader(getClass().getResource("AddProducts.fxml"));
        Parent addProductRoot = (Parent) addProductLoader.load();
        Stage addProductStage = new Stage();
        addProductStage.initModality(Modality.APPLICATION_MODAL);
        addProductStage.setScene(new Scene(addProductRoot, 1200, 800));
        addProductStage.show();
    }

    @FXML
    public void modifyProductButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader modProductLoader = new FXMLLoader(getClass().getResource("ModifyProducts.fxml"));
        Parent modProductRoot = (Parent) modProductLoader.load();
        Stage modProductStage = new Stage();
        modProductStage.initModality(Modality.APPLICATION_MODAL);
        modProductStage.setScene(new Scene(modProductRoot, 1200, 800));
        modProductStage.show();
    }

    @FXML
    public void deleteProductButton(ActionEvent actionEvent) {
    }
}
