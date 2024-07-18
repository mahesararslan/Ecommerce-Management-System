import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer extends person implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Address address;
    private String customerID;
    
    
   
    private CustomerPortal customerPortal;
    Customer(){
        
        username = null;
        password = null;
        address = null;
        address = null;
        customerID = null;
        
        
    }
    Customer(String username,String password, String firstName, String lastName,String email,String phoneNumber,Address address,String customerID,CustomerPortal customerPortal){
        super(username,password,firstName,lastName,email,phoneNumber);
        this.address = address;
        this.customerID = customerID;
        this.customerPortal = customerPortal;
        
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public Address getAddress() {
        return address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public CustomerPortal getCustomerPortal() {
        return customerPortal;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public void setCustomerPortal(CustomerPortal customerPortal) {
        this.customerPortal = customerPortal;
    }
      
    public void viewInfo() {
        System.out.println("Name: " + firstName + " " +lastName + "\tEmail: " + email + "\tPhone Number: " + phoneNumber);
    }
   
    
}
