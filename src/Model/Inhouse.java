package Model;

public class Inhouse extends Part {

    private int machineId;

    public Inhouse (int id, String name, int stock, double price, int max, int min, int machineId) {
        super(id, name, stock, price, max, min);
        setMachineId(machineId);
    }

    public Inhouse() {

    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}
