package model;

import exception.InvalidProductException;

/**
 * Accessory class extends Product
 * Demonstrates: Inheritance, Polymorphism, Method Overriding
 * Examples: Mouse, Keyboard, USB cables, etc.
 */
public class Accessory extends Product {
    private String brand;
    
    // Constructor with all parameters
    public Accessory(int id, String name, String brand, double price, int quantity) {
        super(id, name, price, quantity);  // Call parent constructor
        this.brand = brand;
    }
    
    // Default constructor
    public Accessory() {
        super();
        this.brand = "";
    }
    
    // Getter for brand
    public String getBrand() {
        return brand;
    }
    
    // Setter for brand
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    // Override abstract method from Product
    @Override
    public String getType() {
        return "Accessory";
    }
    
    // Override validate to add brand validation
    @Override
    public void validate() throws InvalidProductException {
        super.validate();  // Call parent validation first
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidProductException("Brand cannot be empty");
        }
    }
    
    // Convert object to CSV string for file storage
    @Override
    public String toCSV() {
        return getType() + "," + id + "," + name + "," + brand + "," + price + "," + quantity;
    }
    
    // Create object from CSV string
    public static Accessory fromCSV(String csvLine) throws InvalidProductException {
        try {
            String[] parts = csvLine.split(",");
            if (parts.length < 6) {
                throw new InvalidProductException("Invalid CSV format: insufficient fields");
            }
            
            // Skip the first field (type) and parse the rest
            int id = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
            String brand = parts[3].trim();
            double price = Double.parseDouble(parts[4].trim());
            int quantity = Integer.parseInt(parts[5].trim());
            
            Accessory accessory = new Accessory(id, name, brand, price, quantity);
            accessory.validate();  // Validate after creation
            return accessory;
            
        } catch (NumberFormatException e) {
            throw new InvalidProductException("Invalid number format in CSV: " + csvLine, e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidProductException("Invalid CSV format: " + csvLine, e);
        }
    }
    
    @Override
    public String toString() {
        return "Accessory{id=" + id + ", name='" + name + "', brand='" + brand + 
               "', price=" + price + ", quantity=" + quantity + "}";
    }
}

