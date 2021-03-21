package Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, int stock, double price, int max, int min, String companyName) {
        super(id, name, stock, price, max, min);
        this.companyName = companyName;

//        setCompanyName(companyName);
    }

    public Outsourced() {}

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
