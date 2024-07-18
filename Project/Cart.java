import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
    private ArrayList<Product> products;
    
    Cart() {
        products = new ArrayList<>();
    }
    public Cart(Cart another) { // copy constructor
        this.products = new ArrayList<>();
        for (Product product : another.products) {
            this.products.add(new Product(product)); 
        }
    }

    Cart(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double TotalCost() {
        
        if (products == null) {
            System.out.println("Cart is empty");
            return 0.0;
        }
        double total = 0;
        for (Product product : products) {
            total += product.getPrice()*product.getQuantity();
        }
        return total;
    }

    public void DisplayCart() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("----------------Cart-----------------\n ");
        if (products == null) {
            System.out.println("Cart is empty");
            return;
        }
        for (Product product : products) {
            System.out.println("Product Name: " + product.getname() + "\tQuantity: " + product.getQuantity()
                    + "\tCost: Rs " + product.getPrice() * product.getQuantity());
        }
        System.out.println("\n******** TOTAL COST *******  Rs: "+TotalCost());
    }

    public Product findProduct(Product product) {
     
        if (products == null) {
            System.out.println("Cart is empty");
            return null;
        } else if (product == null) {
            System.out.println("Product not found");
            return null;
        }
        
        for (Product product2 : products) {
            if (product.equals(product2)) {      
                return product2;
            }
        }
        return null;
       
    }

   
public ArrayList<Product> cloneProductList() {
    ArrayList<Product> clonedList = new ArrayList<>();
    for (Product product : this.products) {
        clonedList.add(new Product(product));
    }
    return clonedList;
}


    public static void main(String[] args) {
        Product p1 = new Product("222", "jabba", "abc", 1200, 3);
        ArrayList<Product> p = new ArrayList<>();
        p.add(p1);
        Cart c = new Cart(p);
        c.TotalCost();
        c.DisplayCart();
    }

}
