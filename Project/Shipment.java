
import java.io.Serializable;
import java.time.LocalDate;

public interface Shipment extends Serializable{
    LocalDate shippingDate = LocalDate.now();

    public abstract void viewDetails();
    public double getCost();
    public static void main(String[] args) {
        ShipmentLocal FedEx = new ShipmentLocal();

        FedEx.viewDetails();
    }
}

class ShipmentWorldwide implements Shipment,Serializable{
    private double shippingCost;

    ShipmentWorldwide() {
        shippingCost = 10000;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getCost() {
        return shippingCost;
    }
    

    public void viewDetails() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Service Name: FedEx\nShipping Date: " + shippingDate + "\nEstimated Arrival: "
                + shippingDate.plusDays(15) + "\nShipping Cost: Rs " + shippingCost);
    }

}

class ShipmentLocal implements Shipment,Serializable{
    private double shippingCost;

    ShipmentLocal() {
        shippingCost = 1000;

    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getCost() {
        return shippingCost;
    }

    public void viewDetails() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Service Name: TCS\nShipping Date: " + shippingDate + "\nEstimated Arrival: "
                + shippingDate.plusDays(5) + "\nShipping Cost: Rs " + shippingCost);
    }

}
