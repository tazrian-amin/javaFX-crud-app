package service;

import model.ComputerPart;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PartManager handles CRUD operations and file I/O
 * Demonstrates OOP concepts: Abstraction, File handling
 */
public class PartManager {
    private static final String DATA_FILE = "data/parts.txt";
    private List<ComputerPart> parts;
    
    public PartManager() {
        parts = new ArrayList<>();
        loadFromFile();
    }
    
    // CREATE: Add a new part
    public void addPart(ComputerPart part) {
        parts.add(part);
        saveToFile();
    }
    
    // READ: Get all parts
    public List<ComputerPart> getAllParts() {
        return new ArrayList<>(parts);
    }
    
    // READ: Get part by ID
    public ComputerPart getPartById(int id) {
        for (ComputerPart part : parts) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }
    
    // UPDATE: Update an existing part
    public boolean updatePart(int id, ComputerPart updatedPart) {
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getId() == id) {
                updatedPart.setId(id); // Keep the same ID
                parts.set(i, updatedPart);
                saveToFile();
                return true;
            }
        }
        return false;
    }
    
    // DELETE: Remove a part by ID
    public boolean deletePart(int id) {
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getId() == id) {
                parts.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }
    
    // Get next available ID
    public int getNextId() {
        int maxId = 0;
        for (ComputerPart part : parts) {
            if (part.getId() > maxId) {
                maxId = part.getId();
            }
        }
        return maxId + 1;
    }
    
    // Load data from file
    private void loadFromFile() {
        File file = new File(DATA_FILE);
        
        // Create data directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        
        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating data file: " + e.getMessage());
                return;
            }
        }
        
        // Read data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    ComputerPart part = ComputerPart.fromCSV(line);
                    if (part != null) {
                        parts.add(part);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }
    }
    
    // Save data to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (ComputerPart part : parts) {
                writer.write(part.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data file: " + e.getMessage());
        }
    }
}

