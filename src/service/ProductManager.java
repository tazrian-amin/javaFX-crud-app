package service;

import model.Product;
import model.ComputerPart;
import model.Accessory;
import exception.DataFileException;
import exception.InvalidProductException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductManager handles CRUD operations with polymorphism
 * Demonstrates: Polymorphism, Exception Handling, File I/O
 */
public class ProductManager {
    private static final String DATA_FILE = "data/products.txt";
    private List<Product> products;
    
    public ProductManager() throws DataFileException {
        products = new ArrayList<>();
        loadFromFile();
    }
    
    // CREATE: Add a new product (polymorphic parameter)
    public void addProduct(Product product) throws InvalidProductException, DataFileException {
        if (product == null) {
            throw new InvalidProductException("Product cannot be null");
        }
        product.validate();  // Validate before adding
        products.add(product);
        saveToFile();
    }
    
    // READ: Get all products (returns polymorphic list)
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    // READ: Get product by ID (polymorphic return type)
    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    // READ: Get only computer parts (demonstrates filtering by type)
    public List<ComputerPart> getComputerParts() {
        List<ComputerPart> parts = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof ComputerPart) {  // Polymorphism check
                parts.add((ComputerPart) product);
            }
        }
        return parts;
    }
    
    // READ: Get only accessories (demonstrates filtering by type)
    public List<Accessory> getAccessories() {
        List<Accessory> accessories = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Accessory) {  // Polymorphism check
                accessories.add((Accessory) product);
            }
        }
        return accessories;
    }
    
    // UPDATE: Update an existing product
    public void updateProduct(int id, Product updatedProduct) throws InvalidProductException, DataFileException {
        if (updatedProduct == null) {
            throw new InvalidProductException("Updated product cannot be null");
        }
        
        updatedProduct.validate();  // Validate before updating
        
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                updatedProduct.setId(id); // Keep the same ID
                products.set(i, updatedProduct);
                saveToFile();
                return;
            }
        }
        throw new InvalidProductException("Product with ID " + id + " not found");
    }
    
    // DELETE: Remove a product by ID
    public void deleteProduct(int id) throws DataFileException, InvalidProductException {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                saveToFile();
                return;
            }
        }
        throw new InvalidProductException("Product with ID " + id + " not found");
    }
    
    // Get next available ID
    public int getNextId() {
        int maxId = 0;
        for (Product product : products) {
            if (product.getId() > maxId) {
                maxId = product.getId();
            }
        }
        return maxId + 1;
    }
    
    // Calculate total inventory value (demonstrates polymorphism)
    public double getTotalInventoryValue() {
        double total = 0;
        for (Product product : products) {
            total += product.getTotalValue();  // Polymorphic method call
        }
        return total;
    }
    
    // Load data from file with proper exception handling
    public void loadFromFile() throws DataFileException {
        File file = new File(DATA_FILE);
        
        // Create data directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            if (!dataDir.mkdirs()) {
                throw new DataFileException("Failed to create data directory");
            }
        }
        
        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new DataFileException("Failed to create data file");
                }
                return; // Empty file, nothing to load
            } catch (IOException e) {
                throw new DataFileException("Error creating data file", e);
            }
        }
        
        // Read data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                
                try {
                    Product product = createProductFromCSV(line);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (InvalidProductException e) {
                    // Log error but continue loading other products
                    System.err.println("Error loading line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new DataFileException("Error reading data file", e);
        }
    }
    
    // Factory method to create products from CSV (demonstrates polymorphism)
    private Product createProductFromCSV(String csvLine) throws InvalidProductException {
        String[] parts = csvLine.split(",");
        if (parts.length < 1) {
            throw new InvalidProductException("Empty CSV line");
        }
        
        String type = parts[0].trim();
        
        // Polymorphic object creation based on type
        switch (type) {
            case "ComputerPart":
                return ComputerPart.fromCSV(csvLine);
            case "Accessory":
                return Accessory.fromCSV(csvLine);
            default:
                throw new InvalidProductException("Unknown product type: " + type);
        }
    }
    
    // Save data to file with proper exception handling
    public void saveToFile() throws DataFileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Product product : products) {
                writer.write(product.toCSV());  // Polymorphic method call
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DataFileException("Error saving data file", e);
        }
    }
}

