# Quick Reference Guide

## 📁 File Structure

```
computer-parts-resale-store/
│
├── src/                                 # Source code folder
│   ├── MainApp.java                    # Main application (GUI + Controller)
│   ├── model/                          # Model layer
│   │   └── ComputerPart.java          # Data model class
│   └── service/                        # Service layer
│       └── PartManager.java           # Business logic & CRUD operations
│
├── data/                               # Data storage
│   └── parts.txt                      # CSV file storing all parts
│
├── bin/                                # Compiled .class files (auto-generated)
│
├── README.md                           # Main documentation
├── SETUP-GUIDE.md                      # Detailed setup instructions
├── QUICK-REFERENCE.md                  # This file
├── compile-and-run.sh                  # Run script for Mac/Linux
├── compile-and-run.bat                 # Run script for Windows
└── .gitignore                          # Git ignore file
```

---

## 🎯 OOP Concepts Used

| Concept | Where | Example |
|---------|-------|---------|
| **Encapsulation** | `ComputerPart.java` | Private fields with public getters/setters |
| **Constructors** | `ComputerPart.java` | Default and parameterized constructors |
| **Abstraction** | `PartManager.java` | Hiding complex file I/O behind simple methods |
| **Collections** | `PartManager.java` | Using `ArrayList<ComputerPart>` |
| **Static Methods** | `ComputerPart.java` | `fromCSV()` method |
| **File I/O** | `PartManager.java` | `BufferedReader` and `BufferedWriter` |
| **Event Handling** | `MainApp.java` | Button click events, table selection |

---

## 🔑 Key Classes & Methods

### ComputerPart.java (Model)

```java
// Properties
private int id;
private String name;
private String category;
private double price;
private int quantity;

// Key Methods
public String toCSV()                    // Convert object to CSV string
public static ComputerPart fromCSV()     // Create object from CSV string
```

### PartManager.java (Service)

```java
// CRUD Operations
public void addPart(ComputerPart part)                    // CREATE
public List<ComputerPart> getAllParts()                   // READ
public ComputerPart getPartById(int id)                   // READ
public boolean updatePart(int id, ComputerPart updated)   // UPDATE
public boolean deletePart(int id)                         // DELETE

// Utility
public int getNextId()                   // Get next available ID
private void loadFromFile()              // Load data from file
private void saveToFile()                // Save data to file
```

### MainApp.java (View/Controller)

```java
// GUI Components
private TableView<ComputerPart> tableView;
private TextField nameField, categoryField, priceField, quantityField;
private Button addButton, updateButton, deleteButton, clearButton;

// Event Handlers
private void handleAdd()                 // Add new part
private void handleUpdate()              // Update selected part
private void handleDelete()              // Delete selected part
private void clearForm()                 // Clear input fields

// Utility
private void refreshTable()              // Reload table data
private void showStatus(String msg)      // Update status message
```

---

## 💾 Data Format

### parts.txt Format
```
id,name,category,price,quantity
```

### Example Data
```
1,Intel Core i7-13700K,CPU,399.99,15
2,NVIDIA RTX 4070,GPU,599.99,8
3,Corsair Vengeance 32GB,RAM,129.99,25
```

### Reading Process
1. `PartManager` constructor calls `loadFromFile()`
2. Reads each line from `parts.txt`
3. Calls `ComputerPart.fromCSV()` to create objects
4. Stores in `ArrayList<ComputerPart>`

### Writing Process
1. User performs add/update/delete
2. `PartManager` calls `saveToFile()`
3. Loops through all parts
4. Calls `part.toCSV()` for each object
5. Writes to file with `BufferedWriter`

---

## 🎨 Color Scheme

| Element | Color Code | Usage |
|---------|-----------|-------|
| Add Button | `#27ae60` | Green - positive action |
| Update Button | `#3498db` | Blue - modification action |
| Delete Button | `#e74c3c` | Red - destructive action |
| Clear Button | `#95a5a6` | Gray - neutral action |
| Title Text | `#2c3e50` | Dark blue-gray |
| Success Message | `#27ae60` | Green |
| Error Message | `#e74c3c` | Red |
| Background | `#f5f5f5` | Light gray |

---

## ⌨️ Common Commands

### Compile (Mac/Linux)
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d bin src/model/*.java src/service/*.java src/*.java
```

### Run (Mac/Linux)
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp bin MainApp
```

### Compile (Windows)
```cmd
javac --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls -d bin src\model\*.java src\service\*.java src\*.java
```

### Run (Windows)
```cmd
java --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls -cp bin MainApp
```

---

## 🔄 Application Flow

### Startup
1. `MainApp.main()` launches JavaFX
2. `start()` method creates GUI
3. `PartManager` constructor loads data from `parts.txt`
4. `refreshTable()` populates TableView

### Adding a Part
1. User fills form fields
2. User clicks "Add Part" button
3. `handleAdd()` validates input
4. Creates new `ComputerPart` object
5. Calls `partManager.addPart()`
6. `PartManager` adds to ArrayList and saves to file
7. `refreshTable()` updates display

### Updating a Part
1. User selects row in table
2. `loadPartToForm()` fills form with data
3. User modifies fields
4. User clicks "Update Part"
5. `handleUpdate()` validates input
6. Calls `partManager.updatePart()`
7. File is saved and table refreshed

### Deleting a Part
1. User selects row
2. User clicks "Delete Part"
3. Confirmation dialog appears
4. If confirmed, `handleDelete()` is called
5. Calls `partManager.deletePart()`
6. File is saved and table refreshed

---

## 🧪 Testing Checklist

- [ ] Add a new part with all fields
- [ ] Add a part with missing fields (should show error)
- [ ] Add a part with invalid price/quantity (should show error)
- [ ] Select a row and verify form auto-fills
- [ ] Update a part's information
- [ ] Delete a part and confirm dialog works
- [ ] Clear the form
- [ ] Close and reopen app to verify data persistence
- [ ] Manually edit `parts.txt` and verify changes load
- [ ] Add 10+ parts to test scrolling
- [ ] Test button hover effects

---

## 🛠️ Extension Ideas

### Beginner Level
1. Add a "Brand" field
2. Change button colors
3. Add more sample data
4. Add input field validation (min/max values)

### Intermediate Level
1. Add search functionality
2. Add sort by price/name/category
3. Add filter by category dropdown
4. Export data to CSV
5. Add "Total Value" calculation (price × quantity)

### Advanced Level
1. Add user authentication
2. Multiple data files (categories)
3. Database instead of text file
4. Charts/graphs for inventory
5. Print functionality
6. Barcode generation

---

## 📊 Class Relationships

```
MainApp (Controller/View)
    |
    | uses
    ↓
PartManager (Service)
    |
    | manages
    ↓
ComputerPart (Model)
    |
    | persisted in
    ↓
parts.txt (Data Storage)
```

---

## 🚨 Common Errors & Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| "JavaFX runtime components missing" | JavaFX not configured | Add VM options with module-path |
| "Cannot find symbol" during compile | Wrong directory or missing files | Ensure all .java files are in place |
| Data not persisting | File permission issue | Check `data/` folder exists and is writable |
| Table not updating | Forgot to call `refreshTable()` | Add refresh call after CRUD operations |
| NumberFormatException | Invalid input in price/quantity | Add try-catch and validation |

---

## 📖 Learning Path

1. **Week 1**: Understand the model class (`ComputerPart.java`)
2. **Week 2**: Study CRUD operations (`PartManager.java`)
3. **Week 3**: Learn file I/O (read/write methods)
4. **Week 4**: Explore JavaFX components (`MainApp.java`)
5. **Week 5**: Practice event handling
6. **Week 6**: Customize and extend

---

**Happy Learning! 🚀**
