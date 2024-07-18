import java.io.Serializable;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Category implements Serializable{
    private String name;
    private ArrayList<Product> products;

    

    public Category() {
        this.name = null;
        this.products = new ArrayList<>();
    }

    public Category(String name, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void viewCategory() {
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush();
        System.out.println(toString());
        System.out.println();
        System.out.println("------------------Products---------------");
        if (products == null) {
            System.out.println("Category is empty");
            return;
        }
        int i =1;
        for (Product product : products) {
            System.out.println(i+". "+product.getname()+" \tAvailabe quantity  "+product.getQuantity() + "\tPrice: " + product.getPrice());
            i++;
        }
    }

    public void addProduct() {
        Scanner input = new Scanner(System.in);
        String ID = "";
        boolean validID = false;
        while (!validID) {
            try {
                System.out.print("Product ID: ");
                ID = input.nextLine();

                validID = true; // If input is successful, set the flag to exit the loop
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid Product ID.");
                input.nextLine();
            }
        }
        String name = "";
        boolean validName = false;
        while (!validName) {
            try {
                System.out.print("Name: ");
                name = input.nextLine();

                validName = true; // If input is successful, set the flag to exit the loop
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid Product Name.");
                input.nextLine();
            }
        }

        double price = 0.0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Price: ");
                price = input.nextDouble();
                validPrice = true; // If input is successful, set the flag to exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                input.nextLine();
            }
        }
        input.nextLine();
        String description = "";
        boolean validDescription = false;
        while (!validDescription) {
            try {
                System.out.print("Description: ");
                description = input.nextLine();
                validDescription = true; // If input is successful, set the flag to exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid Description.");
                input.nextLine();
            }
        }

        int quantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                System.out.print("Quantity: ");
                quantity = input.nextInt();
                validQuantity = true; // If input is successful, set the flag to exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid quantity.");
                input.nextLine();
            }
        }
        Product product = new Product(ID, name, description, price, quantity);
        products.add(product);
        System.out.println("Successfully added a Product:\n" + product);
    }

    public void removeProduct() {
        Scanner input = new Scanner(System.in);
        
            String name = "";
            boolean isValidName = false;
            while (!isValidName) {
                this.viewCategory();
                System.out.println("Enter the name of the product you want to remove: ");
    
                try {
                    name = input.nextLine();
                    boolean productFound = false;
    
                    for (Product product : products) {
                        if (product.getname().equalsIgnoreCase(name)) {
                            isValidName = true;
                            products.remove(product);
                            System.out.println("Successfully removed " + product.getname());
                            Thread.sleep(2000);
                            productFound = true;
                            break;
                        }
                    }
    
                    if (!productFound) {
                        System.out.println("Product doesn't exist");
                        Thread.sleep(2000); // Delay for 2 seconds (adjust as needed)
                    }
    
                } catch (Exception e) {
                    
                    try{
                         Thread.sleep(2000); // Delay for 2 seconds (adjust as needed)
                    }
                   catch(Exception p){

                   }

                }
            }
        }    
        public void getNumberOfProducts(){
            if(products==null){
                System.out.println("Category is empty");
                return;
            }
            System.out.print("\033[H\033[2J"); // for clearing screen
            System.out.flush();
            int counter = 0;
            for(Product product: products){
                counter++;
            }
            System.out.println(name+" has "+counter+" products");
        }
    

    @Override
    public String toString() {
        return "-------------" + name + "-----------------";

    }

}