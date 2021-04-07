package Model;

import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;

    public Product(int id, String name, int stock, double price, int max, int min) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part) {

    }

//    public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
//
//    }
//
//    public ObservableList<Part> getAllAssociatedParts() {
//
//    }
}
