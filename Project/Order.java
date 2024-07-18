import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Date;
public class Order implements Serializable  {
    private String orderID;
    private Customer customer;
    private ArrayList<Product> order;
    private LocalDate date;
    private PaymentMethod payment;
    private Shipment shipping;
    private double totalCost;


 
    public Order() {
        this.orderID = "DefaultOrderID";
        this.customer = null; 
        this.order = null;
        this.date = null; 
        this.shipping = null; 
        totalCost = 0;
    }

    
    public Order(String orderID, Customer customer, ArrayList<Product> order,Shipment shipping,PaymentMethod payment,double totalCost) {
        this.orderID = orderID;
        this.customer = customer;
        this.order = (order != null) ? order : new ArrayList<>();
        this.shipping = shipping;
        this.payment = payment;
        this.totalCost = totalCost;
        this.date = LocalDate.now();
    }

    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Product> order) {
        this.order = order;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Shipment getShipping() {
        return shipping;
    }

    public void setShipping(Shipment shipping) {
        this.shipping = shipping;
    }
    public PaymentMethod getPayment() {
        return payment;
    }
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }
    public double getCost(){
        if(order==null){
            System.out.println("No items ordered");
            return 0.0;
        }
        double sum=0;
        for(Product product: order){
            sum+=product.getPrice();
        }
        return sum;
    }
    public void getDetails() {
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
    
        String accountNumber = null;
    
        System.out.println("-----------Order-----------");
        System.out.println("");
        System.out.println("Order Id: " + orderID + "\n Customer Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n------ Products------" + "\n");
        for (Product product : order) {
            System.out.println("Name: " + product.getname() + "\t Quantity: " + product.getQuantity() + "x");
        }
        System.out.println("--------------------------------\n");
    
        System.out.println("Date: " + date);
    
        if (payment != null) {
            System.out.println("Payment Method: " + payment.getClass().getName());
            if (payment instanceof EasyPaisa) {
                accountNumber = ((EasyPaisa) payment).getPhoneNumber();
            } else if (payment instanceof CreditCard) {
                accountNumber = ((CreditCard) payment).getCreditCardNumber();
            }
            System.out.println("Account Number: " + accountNumber);
        } else {
            System.out.println("Payment information not available.");
        }
    
        if (shipping != null) {
            System.out.println("Shipment Type: " + shipping.getClass().getName());
            System.out.println("Total Cost: " + (totalCost + shipping.getCost()));
        } else {
            System.out.println("Shipping information not available.");
        }
    }
    
}
   

