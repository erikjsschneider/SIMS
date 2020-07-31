package sims;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class AddProducts {

    public Inventory inventory;

    public Button searchProducts;
    public Button addProducts;
    public Button deleteProducts;
    public Button saveProducts;
    public Button cancelProducts;

    public void searchProductsButton(ActionEvent actionEvent) {

    }

    public void addProductsButton(ActionEvent actionEvent) {

    }

    public void deleteProductsButton(ActionEvent actionEvent) {

    }

    public void checkProducts() {
        if (inventory.getAllProducts().isEmpty()) {
            deleteProducts.setDisable(true);
        }
    }
}
