import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;


public class Main implements Serializable {


    public static ArrayList<Category> allProducts = new ArrayList<>();
    public static ArrayList<Customer> Customers = new ArrayList<>();
    public static Admin admin = new Admin("Admin", "Admin123", "Abdul", "Bari", "AbdulBari@gmail.com", "03153601709");


    public static void Initialize() {
        try {
            FileInputStream file1 = new FileInputStream("Products.ser");
            ObjectInputStream obj1 = new ObjectInputStream(file1);
            allProducts = (ArrayList<Category>) obj1.readObject();

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found, Creating new file.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println("Some Error Occured reading from file.");
        }

        try {
            FileInputStream file2 = new FileInputStream("Customers.ser");
            ObjectInputStream obj2 = new ObjectInputStream(file2);
            Customers = (ArrayList<Customer>) obj2.readObject();

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found, Creating new file.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println("Some Error Occured reading from file.");
        }

        try {
            FileInputStream file3 = new FileInputStream("Admin.ser");
            ObjectInputStream obj3 = new ObjectInputStream(file3);
            admin = (Admin)obj3.readObject();

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found, Creating new file.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println("Some Error Occured reading from file.");
        }
    }

    public static void storingData() {
        try {
            FileOutputStream file1 = new FileOutputStream("Products.ser");
            ObjectOutputStream obj1 = new ObjectOutputStream(file1);
            obj1.writeObject(allProducts);
        }
        catch (IOException e) {
             e.printStackTrace();
        }

        try {
            FileOutputStream file2 = new FileOutputStream("Customers.ser");
            ObjectOutputStream obj2 = new ObjectOutputStream(file2);
            obj2.writeObject(Customers);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream file3 = new FileOutputStream("Admin.ser");
            ObjectOutputStream obj3 = new ObjectOutputStream(file3);
            obj3.writeObject(admin);
        }
        catch (IOException e) {
             e.printStackTrace();
        }
    }

    public static void signUp() {
         Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = input.nextLine();
        String username = "";
        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();
            if (!verifyUsername(username)) {
                break;
            }
            else{
                System.out.println("Username already exists");
            }
        }
        System.out.print("Enter password: ");
        String password = input.nextLine();
        Address address = new Address();
        System.out.print("Enter house number: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for house number.");
            input.next();
        }
        address.setHouseNumber(input.nextInt());
        input.nextLine();

        System.out.print("Enter street: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for street.");
            input.next();
        }
        address.setStreet(input.nextInt());
        input.nextLine();

        System.out.print("Enter area: ");
        address.setArea(input.nextLine());

        System.out.print("Enter city: ");
        address.setCity(input.nextLine());

        System.out.print("Enter country: ");
        address.setCountry(input.nextLine());

        Random random = new Random();
        String customerID = firstName.substring(0,1)+random.nextInt(45);
        CustomerPortal customerPortal = new CustomerPortal(customerID);

        Customer customer = new Customer(username, password, firstName, lastName, email, phoneNumber, address,
                customerID, customerPortal);
        Customers.add(customer);
        System.out.println("Successfully created a account");
        try{
            Thread.sleep(1500);
        }
        catch(InterruptedException e){

        }
        loginAsCustomer();
    }

    private static boolean verifyUsername(String username) {
        if (username == null) {
            return false;
        } else {
            for (Customer customer : Customers) {
                if (customer.getUsername().equalsIgnoreCase(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkQuantity(CustomerPortal customerPortal, int quantity, Product product) {

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return false;
        }

        
        if (quantity > product.getQuantity()) {
            System.out.println("Requested quantity exceeds available stock.");
            return false;
        }


        Product prodInCart = customerPortal.getCart().findProduct(product);

        if (prodInCart != null) {
            if (prodInCart.getQuantity() == product.getQuantity()) {
                System.out.println("Cart already has max quantity of " + product.getname());
                try {
                    Thread.sleep(1500);

                } catch (Exception e) {

                }
                customerPortal.Menu();
            } else if (quantity + prodInCart.getQuantity() > product.getQuantity()) {
                System.out.println("Cannot add more to cart. Combined quantity exceeds stock limit.\nMax limit: "
                        + product.getQuantity());
                return false;
            }
        }
        return true;
    }

    public static Customer verifyCustomer(String username,String password){
        Customer member = null;
         for (Customer customer2 : Customers) {
                if (customer2.getUsername().equalsIgnoreCase(username)
                        && customer2.getPassword().equals(password)) {
                    member = customer2;
                }
            }
            return member;
    }

    public static void loginAsAdmin() {
        AdminLogin a = new AdminLogin();
    }

    public static void loginAsCustomer() {
        customerLogin2 c = new customerLogin2();
    }

    public static void viewCategories() {
        int i = 1;
        for (Category category : allProducts) {
            System.out.println(i + ". " + category.getName());
            i++;
        }
    }

    public static Category viewProducts() {
        Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        System.out.println("---------------- Categories ----------------------\n\n");
        viewCategories();
        System.out.println("\n");
        System.out.println();
        boolean flag = true;
        Category categoryForView = null;
        while (flag) {
            try {
                System.out.println("Enter the name of category");
                String categoryName = input.nextLine().trim();
                for (Category category : allProducts) {
                    if (category.getName().equalsIgnoreCase(categoryName)) {
                        categoryForView = category;
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Enter a valid category name");
                }
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        }
        categoryForView.viewCategory();
        return categoryForView;

    }
 public static void login(){
    loginGUI login = new loginGUI();
    }
    public static void main(String[] args) {
        Product product1 = new Product("P1-901", "Qmobile", "LOL", 1200, 50);
        Product product2 = new Product("L0-OP", "Ufone", "POP", 4500, 90);

        ArrayList<Product> electronicsProducts = new ArrayList<>(Arrays.asList(product1, product2));

        Category electronicsCategory = new Category("Electronics", electronicsProducts);

        allProducts.add(electronicsCategory);

        String customerID1 = "ID1";
        String customerID2 = "ID2";

        Customer customer1 = new Customer("user1", "password1", "Arslan", "Mahesar", "xyz@gmail.com", "03202705737",
                new Address(123, 43, "City1", "Local", "Country1"), customerID2,
                new CustomerPortal(customerID1));
        Customer customer2 = new Customer("user2", "password2", "Kabeer", "Malik", "abc@gmail.com", "03153601709",
                new Address(563, 42, "Country2", "London", "UK"), customerID1,
                new CustomerPortal(customerID2));

        Customers.add(customer2);
        Customers.add(customer1);
        Initialize();
        login();
        
        storingData();
        
    }
}