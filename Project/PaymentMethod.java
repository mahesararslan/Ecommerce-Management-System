import java.io.Serializable;

interface PaymentMethod extends Serializable {
    
    public boolean validatePaymentMethod(String accountNumber);
    public void accountInfo();
}
class EasyPaisa implements PaymentMethod,Serializable{
    private String name;
    private String phoneNumber;
    EasyPaisa(){
        phoneNumber = null;
    }
    EasyPaisa(String phoneNumber, String name){
    if (validatePaymentMethod(phoneNumber)){
        this.phoneNumber = phoneNumber;
        this.name = name; // Also set the name
    } else {
        this.phoneNumber = "0"; // Set to "0" only if validation fails
    }
}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public boolean validatePaymentMethod(String number) {
    if (number != null && number.matches("^[0-9]{11}$")) {
        return true;
    } else {
        
        return false;
    }
}

public void accountInfo(){
    
    System.out.println("Payment Method: "+getClass().getName());
    System.out.println("Phone Number: "+phoneNumber);

}
}
class CreditCard implements PaymentMethod,Serializable{
    
    private String creditCardNumber;
    private String name;
    CreditCard(){
        creditCardNumber = null;
    }
    CreditCard(String creditCardNumber,String name){
        if(validatePaymentMethod(creditCardNumber)){
         this.creditCardNumber = creditCardNumber;
         this.name = name;     
        }
        else{
            this.creditCardNumber = "0";
        }

       
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public boolean validatePaymentMethod(String creditCardNumber){
    String cleanedNumber = creditCardNumber.replaceAll("\\D", "");

        // Check if the cleaned number is numeric and has a valid length
        if (cleanedNumber.matches("\\d{13,19}") && isValidLuhn(cleanedNumber)) {
            
            return true;
        } else {
            
            return false;
        }
    }

    private  boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(number.substring(i, i + 1));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
    public void accountInfo(){
    System.out.println("-------------- Account Info-----------------\n");
    System.out.println("Payment Method: "+getClass().getName());
    System.out.println("Account name: "+name);
    System.out.println("Card Number: "+ creditCardNumber);
}
    
}

