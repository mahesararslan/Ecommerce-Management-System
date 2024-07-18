import java.io.Serializable;

public class Address implements Serializable {

        private int houseNumber;
        private int street;
        private String area;
        private String city;
        private String country;
    
        Address() {
            houseNumber = 0;
            street = 0;
            area = null;
            city = null;
            country = null;
        }
    
        public Address(int houseNumber, int street, String area, String city, String country) {
            this.houseNumber = houseNumber;
            this.street = street;
            this.area = area;
            this.city = city;
            this.country = country;
        }
    
        public int getHouseNumber() {
            return houseNumber;
        }
    
        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }
    
        public int getStreet() {
            return street;
        }
    
        public void setStreet(int street) {
            this.street = street;
        }
    
        public String getArea() {
            return area;
        }
    
        public void setArea(String area) {
            this.area = area;
        }
    
        public String getCity() {
            return city;
        }
    
        public void setCity(String city) {
            this.city = city;
        }
    
        public String getCountry() {
            return country;
        }
    
        public void setCountry(String country) {
            this.country = country;
        }
    
        @Override
        public String toString() {
            return "Address{" +
                    "houseNumber=" + houseNumber +
                    ", street=" + street +
                    ", area='" + area + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
    

