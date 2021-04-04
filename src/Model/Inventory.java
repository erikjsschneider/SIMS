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

    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static Part partIdResult = null;

    public static ObservableList<Part> results = null;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            Part pIdSearch = allParts.get(i);
            System.out.println("pidsearch " + pIdSearch.getId());

            if (pIdSearch.getId() == partId) {
                partIdResult = pIdSearch;
                System.out.println("result in loop = " + partIdResult);
                return partIdResult;
            }

            if (pIdSearch.getId() != partId) {
                partIdResult = null;

            }
        }

        System.out.println("result out of loop = " + partIdResult);
        return partIdResult;
    }
//
//    public Product lookupProduct(int productId) {
//
//    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> results = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                results.add(part);
            }
        }

        return results;
    }

//    public ObservableList<Product> lookupProduct(String productName) {
//
//    }

    public void updatePart(int index, Part selectedPart) {

    }

    public void updateProduct(int index, Product newProduct) {

    }

    public boolean deletePart(Part selectedPart) {
        if (selectedPart == null) {
            Alert noDelSelection = new Alert(Alert.AlertType.ERROR);
            noDelSelection.setContentText("No part selected to delete. \n" +
                    "Please select a part to delete.");
            noDelSelection.showAndWait();
            return false;
        } else {
            allParts.remove(selectedPart);
//            partsTable.setItems(Inventory.getAllParts());
            return true;
        }
    }

//    public boolean deleteProduct(Product selectedProduct) {
//
//    }
//
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public void setAllParts (ObservableList<Part> allParts) {
        this.allParts = allParts;
    }

    public ObservableList<Product> getAllProducts() {
        allProducts = new ObservableList<Product>() {
            @Override
            public void addListener(ListChangeListener<? super Product> listener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super Product> listener) {

            }

            @Override
            public boolean addAll(Product... elements) {
                return false;
            }

            @Override
            public boolean setAll(Product... elements) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends Product> col) {
                return false;
            }

            @Override
            public boolean removeAll(Product... elements) {
                return false;
            }

            @Override
            public boolean retainAll(Product... elements) {
                return false;
            }

            @Override
            public void remove(int from, int to) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Product> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Product product) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Product> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Product> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Product get(int index) {
                return null;
            }

            @Override
            public Product set(int index, Product element) {
                return null;
            }

            @Override
            public void add(int index, Product element) {

            }

            @Override
            public Product remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Product> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Product> listIterator(int index) {
                return null;
            }

            @Override
            public List<Product> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };

        return allProducts;
    }

    static {
        addDummyData();
    }

    public static void addDummyData() {
        Inhouse part1 = new Inhouse(1, "Wheel", 30, 200.00, 20, 1, 1001);
        Inventory.addPart(part1);
        Outsourced part2 = new Outsourced(2, "Brake", 50, 140.00, 40, 1, "Centrals");
        Inventory.addPart(part2);
    }
}
