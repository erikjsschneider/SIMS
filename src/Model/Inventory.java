package Model;

import View_Controller.Controller;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static Part partIdResult = null;

    public static Product productIdResult = null;

    public static ObservableList<Part> results = null;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            Part pIdSearch = allParts.get(i);

            if (pIdSearch.getId() == partId) {
                partIdResult = pIdSearch;
                return partIdResult;
            }

            if (pIdSearch.getId() != partId) {
                partIdResult = null;

            }
        }

        return partIdResult;
    }

    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            Product pIdSearch = allProducts.get(i);

            if (pIdSearch.getId() == productId) {
                productIdResult = pIdSearch;
                return productIdResult;
            }

            if (pIdSearch.getId() != productId) {
                productIdResult = null;

            }
        }

        return productIdResult;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> results = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                results.add(part);
            }
        }

        return results;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> results = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                results.add(product);
            }
        }

        return results;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        for (Product product: getAllProducts()) {
            if (product.getAllAssociatedParts().contains(selectedPart)) {
                product.getAllAssociatedParts().removeAll(selectedPart);
            }

        }
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
            allProducts.remove(selectedProduct);
            return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public void setAllParts (ObservableList<Part> allParts) {
        this.allParts = allParts;
    }

    public static ObservableList<Product> getAllProducts() { return allProducts; }

    static {
        addDummyData();
        addProductData();
    }

    public static void addDummyData() {
        Inhouse part1 = new Inhouse(1, "Wheel", 30, 200.00, 40, 1, 1001);
        Inventory.addPart(part1);
        Outsourced part2 = new Outsourced(2, "Brake", 50, 140.00, 100, 1, "Centrals");
        Inventory.addPart(part2);
    }

    public static void addProductData() {
        Product product1 = new Product(1, "Ford", 5, 18000, 25, 1);
        Inventory.addProduct(product1);
        Product product2 = new Product(2, "Tesla", 15, 48000, 25, 1);
        Inventory.addProduct(product2);
    }
}
