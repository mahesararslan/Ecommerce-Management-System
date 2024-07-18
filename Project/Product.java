import java.io.Serializable;
import java.util.Scanner;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    

    public Product() {
        this.productId = null;
        this.name = null;
        this.description = null;
        this.price = 0.0;
        this.quantity = 0;
    }

    public Product(Product original) {
        this.productId = original.productId;
        this.name = original.name;
        this.description = original.description;
        this.price = original.price;
        this.quantity = original.quantity;

    }

    public Product(String productId, String name, String description, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvaialableInStock() {
        if (quantity == 0) {
            return false;
        }
        return true;
    }

    public void updateQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        } else {
            this.quantity += quantity;
        }
    }

    public void updateInfo() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("1. Change price\n2. Change name\n3. Change description");
            System.out.println("Enter choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine();
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                if (choice >= 1 && choice <= 3) {
                    switch (choice) {
                        case 1:
                            System.out.println("Enter price: ");
                            double price = input.nextInt();
                            this.price = price;
                            break;
                        case 2:
                            System.out.println("Enter name: ");
                            String name = input.nextLine();
                            this.name = name;
                            break;
                        case 3:
                            System.out.println("Enter Description: ");
                            String description = input.nextLine();
                            this.description = description;
                            break;
                        default:
                            break;
                    }
                    flag = false;
                } else {
                    System.out.print("\033[H\033[2J"); // for clearing screen
                    System.out.flush(); // for clearing screen
                    System.out.println("Enter choice between 1 and 3");

                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Enter a valid choice");
                input.nextLine();
            }

        }
    }

    public void displayDetails() {
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush();
        System.out.println("----------------- Details -----------------");
        System.out.println(toString());
    }

    public boolean equals(Product product) {
        if (product == null) {
            return false;
        }
        return product.name.equals(name)  && product.description.equals(description) && product.price == price
                && product.productId.equals(productId);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nID: " + productId + "\nPrice: " + price + "\nDescription: " + description
                + "\nQuantity: " + quantity;
    }

}