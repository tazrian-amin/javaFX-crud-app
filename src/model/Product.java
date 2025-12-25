package model;

import exception.InvalidProductException;

/**
 * Abstract Product class - parent class for all products
 * Demonstrates: Polymorphism, Abstraction, Inheritance
 */
public abstract class Product {
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;
    
    // Constructor
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Default constructor
    public Product() {
        this(0, "", 0.0, 0);
    }
    
    // Abstract methods - must be implemented by child classes
    public abstract String getType();
    public abstract String toCSV();
    
    // Concrete method - can be overridden by child classes
    public void validate() throws InvalidProductException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }
        if (price < 0) {
            throw new InvalidProductException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new InvalidProductException("Quantity cannot be negative");
        }
    }
    
    // Common getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Common setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Calculate total value (price × quantity)
    public double getTotalValue() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + 
               ", quantity=" + quantity + ", type='" + getType() + "'}";
    }
}

