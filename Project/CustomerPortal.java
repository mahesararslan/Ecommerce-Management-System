import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class CustomerPortal implements Serializable{
    private String customerID;
    private Cart cart;
    private ArrayList<PaymentMethod> paymentMethods; // used polymorphism as it stores EasyPaisa and Credit card
    private ArrayList<Order> orders;
   

    CustomerPortal(String customerID) {
        this.customerID = customerID;
        cart = new Cart();
        paymentMethods = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public Cart getCart() {
        return cart;
    }

    public String getCustomerID() {
        return customerID;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void Menu() {
          Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("\033[H\033[2J"); // for clearing screen
            System.out.flush(); // for clearing screen
            System.out.println("------- MENU --------");
            System.out.println("1. View Products\n2. View Cart\n3. Checkout\n4. View Order History\n5. Go back\n6. Exit");

            try {
                System.out.println("Enter your choice: ");
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
                ViewProducts();
                break;
            case 2:
                viewCart();

                break;
            case 3:
                checkout();
                break;
            case 4:
                orderHistory();
                break;
            case 5:
                Main.login();
                break;
            case 6:
                Main.storingData();
                return;   

        }
    }

    private Customer findCustomerById(String customerID) {
        for (Customer customer : Main.Customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    public void addToCart(Category category) {
         Scanner input = new Scanner(System.in);
        boolean flag = true;
        Product product = null;
        while (flag) {
            System.out.println("Enter the name of the product: ");
            String name = input.nextLine().trim();

            for (Product product2 : category.getProducts()) {
                if (name.equalsIgnoreCase(product2.getname())) {
                    product = product2;
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println("\n\nEnter a valid name");

                boolean flag2 = true;
                while (flag2) {
                    System.out.println("\n1. Try again\n2. Go back");
                    int choice;
                    try {
                        choice = input.nextInt();
                        input.nextLine();
                        if (choice == 1 || choice == 2) {
                            switch (choice) {
                                case 1:
                                    category.viewCategory();
                                    flag2 = false;
                                    break;
                                case 2:
                                    Main.viewProducts();
                                    flag2 = false;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            System.out.println("Enter your choice between 1 and 2");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter your choice in numbers");
                        input.next(); // Consume the invalid input
                    }
                }
            }
        }

        boolean flag3 = true;
        int quantity = 0;

        while (flag3) {
            try {
                System.out.println("Enter quantity: ");
                quantity = input.nextInt();
                input.nextLine();

                if (Main.checkQuantity(this, quantity, product)) {
                    flag3 = false;
                    break;
                } else {
                    System.out.println("Enter a valid quantity");
                    Thread.sleep(2000);
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter quantity in numbers");
                input.next(); // Consume the invalid input
            } catch (InterruptedException e) {
                System.out.println("Thread sleep interrupted");
            }
        }

        Product productInCart = cart.findProduct(product);

        if (productInCart != null) {
            productInCart.updateQuantity(quantity);
            System.out.print("\033[H\033[2J"); // for clearing screen
            System.out.flush(); // for clearing screen
            System.out.println("Successfully added " + quantity + " units of " + productInCart.getname());
            try {
                Thread.sleep(1500);
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } catch (InterruptedException c) {

            }

        } else {
            Product product2 = new Product(product);
            product2.setQuantity(quantity);
            try {
                cart.getProducts().add(product2);
                System.out.print("\033[H\033[2J"); // for clearing screen
                System.out.flush(); // for clearing screen
                System.out.println("Successfully added " + product2.getname() + " to the cart");
                Thread.sleep(1500);
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (Exception e) {
                System.out.println("Product couldn't be added");
            }
        }

        // Continue with the Menu or any other necessary actions
        Menu();
    }

    public void ViewProducts() {
         Scanner input = new Scanner(System.in);
        Category category = Main.viewProducts();
        boolean flag2 = true;
        int choice = 0;
        while (flag2) {
            System.out.print("\033[H\033[2J"); // for clearing screen
            System.out.flush(); // for clearing screen
            category.viewCategory();
            System.out.println("\n\n");
            System.out.println("1. Add to Cart");
            System.out.println("2. Go back");

            try {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();

                // Consume the newline character
                input.nextLine();

                if (choice == 1 || choice == 2) {
                    flag2 = false;
                    break;
                }
                System.out.println("Enter a valid choice");
                Thread.sleep(2000);

            } catch (InputMismatchException e) {
                System.out.println("Enter your input in numbers");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException c) {

                }

                // Consume the invalid input
                input.nextLine();

            } catch (InterruptedException p) {
                System.out.println("");
            }
        }

        switch (choice) {
            case 1:
                addToCart(category);
                break;
            case 2:
                Menu();
                break;
            default:
                break;
        }
    }

    public void viewCart() {
         Scanner input = new Scanner(System.in);
        boolean flag = true;
        int choice = 0;
        while (flag) {
            try {
                Thread.sleep(1000);

            } catch (Exception e) {

            }
            cart.DisplayCart();
            System.out.println("\n\n");
            System.out.println("1. Remove a product from cart\n2. Empty cart\n3. Go back");
            try {
                System.out.println("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1 || choice == 2 || choice == 3) {
                    flag = false;
                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }

            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();
            }
        }
        switch (choice) {
            case 1:
                if(cart.getProducts().isEmpty()){
                       System.out.print("\033[H\033[2J");
                        System.out.flush();
                    System.out.println("Cart is empty");
                    try{
                        Thread.sleep(1500);
                    }
                    catch(InterruptedException e){

                    }
                    viewCart();
                }
                else{
                removeFromCart();
                }
                Menu();
                break;
            case 2:
                if(cart.getProducts().isEmpty()){
                       System.out.print("\033[H\033[2J");
                        System.out.flush();
                    System.out.println("Cart is empty");
                    try{
                        Thread.sleep(1500);
                    }
                    catch(InterruptedException e){

                    }
                    viewCart();
                }
                else{
                emptyCart();
                Menu();
                }
                break;
            case 3:
                Menu();
                break;
            default:
                break;
        }

    }

    public void removeFromCart() {
         Scanner input = new Scanner(System.in);
        boolean flag = true;
        Product product = null;
        while (flag) {
            System.out.println("Enter the name of the product: ");
            String name = input.nextLine().trim();

            for (Product product2 : cart.getProducts()) {
                if (name.equalsIgnoreCase(product2.getname())) {
                    product = product2;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("\n\nEnter a valid name");
                boolean flag2 = true;
                while (flag2) {
                    System.out.println("\n1. Try again\n2. Go back");
                    int choice;
                    try {
                        choice = input.nextInt();
                        input.nextLine();
                        if (choice == 1 || choice == 2) {
                            switch (choice) {
                                case 1:
                                    cart.DisplayCart();
                                    flag2 = false;
                                    break;
                                case 2:
                                   viewCart();
                                    flag2 = false;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            System.out.println("Enter your choice between 1 and 2");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter your choice in numbers");
                        input.next(); // Consume the invalid input
                    }
                }
            }
        }
        boolean flag3 = true;
        int quantity = 0;

        while (flag3) {
            try {
                System.out.println("Enter quantity: ");
                quantity = input.nextInt();

                if (quantity > product.getQuantity() || quantity < 1) {
                    System.out.println("Enter a valid quantity");
                    Thread.sleep(1000);

                } else {
                    flag3 = false;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter quantity in numbers");
                input.next(); // Consume the invalid input
            } catch (InterruptedException e) {
                System.out.println("Thread sleep interrupted");
            }
        }
        if (quantity == product.getQuantity()) {
            cart.getProducts().remove(product);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Successfully removed " + product.getname() + " from cart");
            try {
                Thread.sleep(1500);
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } catch (Exception e) {

            }
        } else {
            int updatedQuantity = product.getQuantity() - quantity;
            product.setQuantity(updatedQuantity);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Removed " + quantity + " units of " + product.getname());
            try {
                Thread.sleep(1500);
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } catch (Exception e) {

            }
        }
    }

    public void emptyCart() {
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        if (cart.getProducts() == null) {
            System.out.println("Cart is empty");

        } else {
            cart.getProducts().clear();
            System.out.println("Successfully cleared cart");
        }
        try {
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
        }
        Menu();
    }

    private void addEasyPaisa() {
         Scanner input = new Scanner(System.in);
        String phoneNumber = null;
        String accountName = null;
        while (true) {
            System.out.print("Enter your phone number: ");
            phoneNumber = input.nextLine().trim();
            System.out.print("Account name: ");
            accountName = input.nextLine().trim();

            EasyPaisa easyPaisa = new EasyPaisa(phoneNumber, accountName);
            if (!"0".equals(easyPaisa.getPhoneNumber())) {
                if (paymentMethods.isEmpty()) {
                    paymentMethods.add(easyPaisa);
                     System.out.println("Successfully added an EasyPaisa account to your payment methods.");
                     break;
                } else {
                    if (!paymentMethodsExists(easyPaisa)) {
                        paymentMethods.add(easyPaisa);
                           System.out.print("\033[H\033[2J");
                            System.out.flush();
                        System.out.println("Successfully added an EasyPaisa account to your payment methods.");
                        
                        break; // Exit the loop after successful addition
                    } else {
                           System.out.print("\033[H\033[2J");
                            System.out.flush();
                        System.out.println("An EasyPaisa account with this phone number already exists.");

                    }
                }
            } else {
                   System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Invalid phone number. Please try again.");
            }
        }
    }

    private void addCreditCard() {
         Scanner input = new Scanner(System.in);
        String accNumber = null;
        String name = null;
        while (true) {
            System.out.print("Enter your Credit Card number: ");
            accNumber = input.nextLine().trim();
            System.out.print("Account name: ");
            name = input.nextLine().trim();

            CreditCard card = new CreditCard(accNumber, name);
            if (!"0".equals(card.getCreditCardNumber())) {
                if (paymentMethods.isEmpty()) {
                    paymentMethods.add(card);
                     System.out.println("Successfully added a Credit card to your payment methods.");
                     break;
                } else {
                    if (!paymentMethodsExists(card)) {
                        paymentMethods.add(card);
                        System.out.println("Successfully added a Credit card to your payment methods.");
                        break; // Exit the loop after successful addition
                    } else {
                        System.out.println("This credit card already exists.");

                    }
                }
            } else {
                System.out.println("Invalid credit card number. Please try again.");
            }
        }
        
    }

    public void addPaymentMethods() {
         Scanner input = new Scanner(System.in);
        int choice1 = 0;
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Add a payment Method\n");
                System.out.println("1. EasyPaisa\n2. Credit Card\n3. Go back to Menu");
                System.out.println("Enter your choice: ");
                choice1 = input.nextInt();
                input.nextLine();
                if (choice1 == 1 || choice1 == 2 || choice1 == 3) {
                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }
            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();
            }
        }
        switch (choice1) {
            case 1:
                addEasyPaisa();
                break;
            case 2:
                addCreditCard();
                break;
            case 3:
                Menu();
                break;

        }

        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }

    }

    public boolean paymentMethodsExists(PaymentMethod newPaymentMethods) {
        if (paymentMethods.isEmpty()) {
            return false;
        }
        for (PaymentMethod existingMethod : paymentMethods) {

            if (existingMethod instanceof EasyPaisa && newPaymentMethods instanceof EasyPaisa) {
                EasyPaisa existingEasyPaisa = (EasyPaisa) existingMethod;
                EasyPaisa newEasyPaisa = (EasyPaisa) newPaymentMethods;
                if (existingEasyPaisa.getPhoneNumber().equals(newEasyPaisa.getPhoneNumber())) {
                    return true; // Found an existing EasyPaisa with the same phone number
                }
            } else if (existingMethod instanceof CreditCard && newPaymentMethods instanceof CreditCard) {
                CreditCard existingCreditCard = (CreditCard) existingMethod;
                CreditCard newCreditCard = (CreditCard) newPaymentMethods;
                if (existingCreditCard.getCreditCardNumber().equals(newCreditCard.getCreditCardNumber())) {
                    return true; // Found an existing CreditCard with the same number
                }
            }
        }
        return false; // No matching payment method found
    }

    public void checkout() {
         Scanner input = new Scanner(System.in);
        if (cart.getProducts().isEmpty()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Cart is empty");
            try {
                Thread.sleep(1500);

            } catch (Exception e) {

            }
            Menu();
            return;
        }

        int choice4 = 0;

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("-------------Checkout----------------");
                cart.DisplayCart();
                System.out.println("\n");
                System.out.println("1. World wide shipment\n2. Local Shipment");
                System.out.println("Enter your choice");
                choice4 = input.nextInt();
                input.nextLine();
                if (choice4 == 1 || choice4 == 2) {

                    break;
                } else {
                    System.out.println("Enter a valid choice ");

                }
            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();
            }
        }
        Shipment shipment = null;
        switch (choice4) {
            case 1:
                shipment = new ShipmentWorldwide(); // polymorphism
                break;
            case 2:
                shipment = new ShipmentLocal(); // polymorphism
                break;
            default:
                break;
        }
        cart.DisplayCart();
        System.out.println("");
        System.out.println("Total cost: " + (cart.TotalCost() + shipment.getCost()));
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException p){
            
        }
        if (paymentMethods.isEmpty()) {
            addPaymentMethods();
        }

        int choice = 0;
        while (true) {
            try {
                Thread.sleep(1500);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("1. Use an existing Payment Method\n2. Add a new Payment Method");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1 || choice == 2) {

                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }
            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();
            }
        }

        PaymentMethod selectedPaymentMethod = null;
        switch (choice) {
            case 1:
                break;
            case 2:
                addPaymentMethods();
                break;

        }
        int i = 1;
        for (PaymentMethod paymentMethod : paymentMethods) {
            System.out.println("--------- Account number " + i + "------------");
            paymentMethod.accountInfo();
            System.out.println("");
            i++;
        }
        int Choice = 0;
        while (true) {
            try {
                System.out.println("Which account to use. Use numbers to input");
                Choice = input.nextInt();
                input.nextLine();
                if (Choice >= 1 && Choice < i) {
                    selectedPaymentMethod = paymentMethods.get(choice - 1);
                    System.out.println("");
                    System.out.println("------- Selected Account Details ---------- ");
                    selectedPaymentMethod.accountInfo();
                    Thread.sleep(2000);
                    break;
                } else {
                    System.out.println("Enter a valid choice");

                }

            } catch (Exception e) {
                System.out.println("Enter your input in numbers");
                input.next();

            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Customer customer = findCustomerById(customerID);
        String orderID = "ORDER-" + UUID.randomUUID().toString();
        ArrayList<Product> copyCart = cart.cloneProductList();
        Order order = new Order(orderID, customer, copyCart, shipment, selectedPaymentMethod, cart.TotalCost());
        order.getDetails();
        boolean flag5 = true;
        int choice0 = 0;
        while (flag5) {
            try {
                System.out.println("1. Would you like to place this order\n2. Go back to main menu");
                System.out.print("Enter your choice: ");
                choice0 = input.nextInt();
                input.nextLine();
                if (choice0 == 1 || choice0 == 2) {
                    flag5 = false;
                    break;
                } else {
                    System.out.println("Enter a valid choice");

                }
            } catch (InputMismatchException e) {
                System.out.println("Enter your input in numbers");
                input.next();
            }

        }
        switch (choice0) {
            case 1:
                Main.admin.getPendingOrders().add(order);
                updateQuantity();
                cart.getProducts().clear();
                orders.add(order);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Successfully placed Order");
                try {
                    Thread.sleep(1500);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                } catch (Exception e) {

                }
                Menu();
                break;
            case 2:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                Menu();

        }

    }

    public void updateQuantity() {
        for (Product product2 : cart.getProducts()) {
            for (Category category : Main.allProducts) {
                for (Product product3 : category.getProducts()) {
                    if (product3.equals(product2)) {
                        int quantity = product3.getQuantity() - product2.getQuantity();
                        product3.setQuantity(quantity);
                    }

                }
            }

        }
    }

    public void orderHistory() {
         Scanner input = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // for clearing screen
        System.out.flush(); // for clearing screen
        System.out.println("-------- ORDER HISTORY ---------\n");

        if (orders.isEmpty()) {
            System.out.println("No order history");
            try {
                Thread.sleep(1500);
            }
            catch(Exception e){

            }
            Menu();

        } else {
            int orderNumber = 1;
            for (Order order : orders) {
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

            if (selectedOrderNumber >= 1 && selectedOrderNumber <= orders.size()) {
                // User selected a valid order
                Order selectedOrder = orders.get(selectedOrderNumber - 1);
                selectedOrder.getDetails();
                int choice1 = 0;
                while (true) {
                    try {
                        System.out.println("\n 1.Go back\n2. Main Menu");
                        choice1 = input.nextInt();
                        input.nextLine();
                        if (choice1 == 1) {
                            orderHistory();
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
                Menu();
            } else {

                System.out.println("Invalid input. Please enter a valid order number.");
                orderHistory();
            }
        }
    }
}
