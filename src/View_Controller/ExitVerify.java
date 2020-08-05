package View_Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExitVerify {

    @FXML
    public Button exitNoButton;

    @FXML
    public void handleExitYesButton() throws IOException {
        Platform.exit();
    }

    @FXML
    public void handleExitNoButton() throws IOException {
        exitNoButton.getScene().getWindow().hide();
    }
}
