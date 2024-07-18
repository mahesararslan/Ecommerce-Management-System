import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Admin extends person implements Serializable{
    private static final long serialVersionUID = 1L;
   

    private ArrayList<Order> pendingOrders;

    Admin() {
        pendingOrders = new ArrayList<>();

    }

    Admin(String username, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password, firstName, lastName, email, phoneNumber);
        pendingOrders = new ArrayList<>();
    }

    public void setPendingOrders(ArrayList<Order> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public ArrayList<Order> getPendingOrders() {
        return pendingOrders;
    }

    public void Menu() {
 Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("\033[H\033[2J"); // for clearing screen
            System.out.flush(); // for clearing screen
            System.out.println("------- MENU --------");
            System.out.println(
                    "1. Edit Categories\n2. Edit Products\n3. Orders Info\n4. View Customer Info\n5. Logout\n6. Exit");

            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    System.out.print("\033[H\033[2J"); // for clearing screen
                    System.out.flush(); // for clearing screen
                    System.out.println("Enter a valid choice");
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Enter your choice in numbers");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
                input.next();
            }
        }
        switch (choice) {
            case 1:
                EditCategories();
                break;
            case 2:
                EditProducts();
                break;
            case 3:
                orderInfo();
                break;
            case 4:
                viewCustomerInfo();
                break;
            case 5:
                Main.login();
                break;
            case 6:
                Main.storingData();
                return;
        }

    }

    public void EditCategories() {
        int choice = 0;
         Scanner input = new Scanner(System.in);
        while (true) {
            viewCategories();
            System.out.println("-----------------------------------");
            System.out.println("1. Add Category\n2. Remove Category\n3. Go Back");

            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.print("\033[H\033[2J"); // for clearing screen
                    System.out.flush(); // for clearing screen
                    System.out.println("Enter a valid choice");
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Enter your choice in numbers");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
                input.next();
            }
        }
        switch (choice) {
            case 1:
                addCategory();
                break;
            case 2:
                removeCategory();
                break;
            case 3:
                Menu();
                break;
        }
    }

    public void EditProducts() {
        int choice = 0;
         Scanner input = new Scanner(System.in);
        while (true) {
            viewCategories();
            System.out.println("-----------------------------------");
            System.out.println(
                    "1. View Products\n2. Add Product\n3. Remove Product\n4. Update Product Info\n5. Update Product Quantity\n6. Go Back");

            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    System.out.print("\033[H\033[2J"); // for clearing screen
                    System.out.flush(); // for clearing screen
                    System.out.println("Enter a valid choice");
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Enter your choice in numbers");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
                input.next();
            }
        }
        switch (choice) {
            case 1:
                viewProductInfo();
                break;
            case 2:
                addProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 4:
                updateProductInfo();
                break;
            case 5:
                updateStock();
                break;
            case 6:
                Menu();
                break;
        }
    }

    public void orderInfo() {
        int choice = 0;
         Scanner input = new Scanner(System.in);
        while (true) {
            
            System.out.println("-----------------------------------");
            System.out.println("1. View Pending Orders\n2. Process Order\n3. Go Back");

            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.print("\033[H\033[2J"); // for clearing screen
                    System.out.flush(); // for clearing screen
                    System.out.println("Enter a valid choice");
                    Thread.sleep(1500);
                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Enter your choice in numbers");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
                input.next();
            }
        }
        switch (choice) {
            case 1:
                viewPendingOrders();
                break;
            case 2:
                processOrder();
                break;
            case 3:
                Menu();
                break;
        }
    }

    public void viewCategories() {
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        int i = 1;
        System.out.println("-------------- Categories -------------");
        for (Category category : Main.allProducts) {
            System.out.println(i + ". " + category.getName());
            i++;
        }
    }

    public Category findCategory(String name) {
        for (Category category : Main.allProducts) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    public boolean existCategory(String name) {
        for (Category category : Main.allProducts) {
            if (category.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void viewProductInfo() {
         Scanner input = new Scanner(System.in);
        Category category = null;
        while (true) {
            viewCategories();
            System.out.println("Enter category name: ");
            String name = input.nextLine();
            category = findCategory(name);
            if (category != null) {
                break;
            } else {
                System.out.println("Enter a valid category name");
            }
        }
        category.viewCategory();
        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter 1 to go back: ");
                choice = input.nextInt();

                if (choice == 1) {
                    EditProducts();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
            }
        }

    }

    public Product findProduct(String name, Category category) {
        for (Product product : category.getProducts()) {
            if (product.getname().equalsIgnoreCase(name))
                return product;
        }
        return null;
    }

    public void addProduct() {
         Scanner input = new Scanner(System.in);
        String name = null;
        Category category = null;
        while (true) {
            viewCategories();
            System.out.println("Enter the name of the category");
            name = input.nextLine();
            category = findCategory(name);
            if (category != null) {
                break;
            } else {
                System.out.println("Enter a valid name");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
            }
        }
        category.addProduct();
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }
        EditProducts();
    }

    public void removeProduct() {
         Scanner input = new Scanner(System.in);
        String name = null;
        Category category = null;
        while (true) {
            viewCategories();
            System.out.println("Enter the name of the category");
            name = input.nextLine();
            category = findCategory(name);
            if (category != null) {
                break;
            } else {
                System.out.println("Enter a valid name");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
            }
        }
        if (category.getProducts().isEmpty()) {
            System.out.println("Category is empty.");
        } else {
            category.removeProduct();
        }

        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }
        EditProducts();
    }

    public void addCategory() {
         Scanner input = new Scanner(System.in);
        String name = null;
        while (true) {
            viewCategories();
            System.out.println("Enter name of the category: ");
            name = input.nextLine();
            if (!existCategory(name)) {
                break;
            } else {
                System.out.println("Category already exists, Enter name again");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
            }
        }
        Category category = new Category(name, new ArrayList<Product>());
        Main.allProducts.add(category);
        System.out.println("Successfully added Category: " + name);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException c) {

        }
        EditCategories();
    }

    public void removeCategory() {
 Scanner input = new Scanner(System.in);
        if (Main.allProducts.isEmpty()) {
            System.out.println("There are no categories.");
        } else {
            String name = null;
            Category category = null;
            while (true) {
                viewCategories();
                System.out.println("Enter name of the category: ");
                name = input.nextLine();
                category = findCategory(name);
                if (category != null) {
                    break;
                } else {
                    System.out.println("Category doesn't exist, Enter name again");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException c) {

                    }
                }
            }
            Main.allProducts.remove(category);
            System.out.println("Successfully removed Category: " + name);
        }
        try {Thread.sleep(1500);} catch (Exception e) {}
        EditCategories();
    }

    public void updateStock() {
         Scanner input = new Scanner(System.in);
        String categoryName = null;
        Category category = null;
        while (true) {
            viewCategories();
            System.out.println("Enter name of the category: ");
            categoryName = input.nextLine();
            category = findCategory(categoryName);
            if (category != null) {
                break;
            } else {
                System.out.println("Category doesn't exist, Enter name again");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
            }
        }

        String productName = null;
        Product product = null;
        while (true) {
            category.viewCategory();
            System.out.println("Enter name of Product: ");
            productName = input.nextLine();
            product = findProduct(productName, category);
            if (product != null) {
                break;
            } else {
                System.out.println("Product doesn't exist, Enter name again");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException c) {

                }
            }
        }

        int quantity = 0;
        while (true) {
            try {
                System.out.println("Enter amount of Quantity you want to add: ");
                quantity = input.nextInt();
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Enter a valid quantity.");
                }
            } catch (Exception e) {
                System.out.println("Enter Quantity in numbers.");
            }
        }

        product.updateQuantity(quantity);
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }
        EditProducts();
    }

    public void updateProductInfo() {
         Scanner input = new Scanner(System.in);
        String categoryName = null;
        Category category = null;
        while (true) {
            viewCategories();
            System.out.println("Enter name of the category: ");
            categoryName = input.nextLine();
            category = findCategory(categoryName);
            if (category != null) {
                break;
            } else {
                System.out.println("Category doesn't exist, Enter name again");
            }
        }
        category.viewCategory();

        String productName = null;
        Product product = null;
        while (true) {
            System.out.println("Enter name of Product: ");
            productName = input.nextLine();
            product = findProduct(productName, category);
            if (product != null) {
                break;
            } else {
                System.out.println("Product doesn't exist, Enter name again");
            }
        }

        product.updateInfo();
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }
        EditProducts();
    }

    public void viewCustomerInfo() {
         Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        System.out.println("--------Customers Details---------");
        int i = 1;
        for (Customer customer : Main.Customers) {
            System.out.println("\n--------Customer " + i + "---------");
            customer.viewInfo();
            i++;
        }

        int choice = 0;
            try {
                System.out.println("Enter 1 to go back: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1) {
                    Menu();
                }
                else{
                    viewCustomerInfo();
                }
            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();
                viewCustomerInfo();
            }
        
    }

    public void viewPendingOrders() {
         Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        System.out.println("-------- PENDING ORDERS ---------\n");

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders");
            try {
                Thread.sleep(1500);
            } catch (Exception e) {

            }
            orderInfo();

        } else {
            int orderNumber = 1;
            for (Order order : pendingOrders) {
                System.out.println(orderNumber + ". Order ID: " + order.getOrderID());
                System.out.println("   Total Cost: " + order.getCost());
                System.out.println("   Order Date: " + order.getDate());
                System.out.println(); // Empty line for better readability
                orderNumber++;
            }

            // Prompt the user to select an order for detailed view
            System.out.println("Enter the order number to view details (or enter 0 to go back to the main menu): ");
            int selectedOrderNumber = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (selectedOrderNumber >= 1 && selectedOrderNumber <= pendingOrders.size()) {
                // User selected a valid order
                Order selectedOrder = pendingOrders.get(selectedOrderNumber - 1);
                selectedOrder.getDetails();
                int choice1 = 0;
                while (true) {
                    try {
                        System.out.println("\n 1.Go back\n2. Go to Main Menu");
                        choice1 = input.nextInt();
                        input.nextLine();
                        if (choice1 == 1) {
                            viewPendingOrders();
                            break;
                        } else if (choice1 == 2) {
                            Menu();
                            break;
                        } else {
                            System.out.println("Enter a valid choice");
                        }
                    } catch (Exception e) {
                        System.out.println("Enter your input in numbers");
                        input.next();
                    }
                }
            } else if (selectedOrderNumber == 0) {
                // User wants to go back to the main menu
                orderInfo();
            } else {

                System.out.println("Invalid input. Please enter a valid order number.");
                viewPendingOrders();
            }
        }
    }

    public void processOrder() {
         Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        System.out.println("-------- PENDING ORDERS ---------\n");

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders");
            try {
                Thread.sleep(1500);
            } catch (Exception e) {

            }
            orderInfo();

        } else {
            int orderNumber = 1;
            for (Order order : pendingOrders) {
                System.out.println(orderNumber + ". Order ID: " + order.getOrderID());
                System.out.println("   Total Cost: " + order.getCost());
                System.out.println("   Order Date: " + order.getDate());
                System.out.println(); // Empty line for better readability
                orderNumber++;
            }

            // Prompt the user to select an order for detailed view
            System.out.println("Enter the order number to process Order (or enter 0 to go back to the main menu: ");
            int selectedOrderNumber = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (selectedOrderNumber >= 1 && selectedOrderNumber <= pendingOrders.size()) {
                // User selected a valid order
                pendingOrders.remove(selectedOrderNumber - 1);
                System.out.println("Succesfully Procesed Order.");

                int choice1 = 0;
                while (true) {
                    try {
                        System.out.println("\n 1.Continue Processing\n2. Go Back");
                        System.out.println("Enter your choice: ");
                        choice1 = input.nextInt();
                        input.nextLine();
                        if (choice1 == 1) {
                            processOrder();
                            break;
                        } else if (choice1 == 2) {
                            orderInfo();
                            break;
                        } else {
                            System.out.println("Enter a valid choice");
                        }
                    } catch (Exception e) {
                        System.out.println("Enter your input in numbers");
                        input.next();
                    }
                }
            } else if (selectedOrderNumber == 0) {
                // User wants to go back to the main menu
                orderInfo();
            } else {

                System.out.println("Invalid input. Please enter a valid order number.");
                viewPendingOrders();
            }
        }
    }

    public void viewInfo() {
        System.out.println("-------- Information------------");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }
}
