package Model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

//    public Part lookupPart(int partId) {
//
//    }
//
//    public Product lookupProduct(int productId) {
//
//    }
//
//    public ObservableList<Part> lookupPart(String partName) {
//
//    }
//
//    public ObservableList<Product> lookupProduct(String productName) {
//
//    }

    public void updatePart(int index, Part selectedPart) {

    }

    public void updateProduct(int index, Product newProduct) {

    }

//    public boolean deletePart(Part selectedPart) {
//
//    }
//
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
        Inhouse part2 = new Inhouse(2, "Brake", 50, 140.00, 40, 1, 1002);
        Inventory.addPart(part2);
    }
}
