package model;

/**
 * ComputerPart class represents a computer part in the resale store
 * Demonstrates OOP concepts: Encapsulation, Constructors, Getters/Setters
 */
public class ComputerPart {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    
    // Constructor with all parameters
    public ComputerPart(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Default constructor
    public ComputerPart() {
        this(0, "", "", 0.0, 0);
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Convert object to CSV string for file storage
    public String toCSV() {
        return id + "," + name + "," + category + "," + price + "," + quantity;
    }
    
    // Create object from CSV string
    public static ComputerPart fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 5) {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String category = parts[2].trim();
            double price = Double.parseDouble(parts[3].trim());
            int quantity = Integer.parseInt(parts[4].trim());
            return new ComputerPart(id, name, category, price, quantity);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "ComputerPart{id=" + id + ", name='" + name + "', category='" + category + 
               "', price=" + price + ", quantity=" + quantity + "}";
    }
}

