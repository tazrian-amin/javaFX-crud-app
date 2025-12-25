# Computer Parts Resale Store

A simple JavaFX application demonstrating fundamental OOP concepts and CRUD operations for managing computer parts inventory.

## 🎯 Features

- **Create**: Add new computer parts to inventory
- **Read**: View all parts in a table format
- **Update**: Edit existing part information
- **Delete**: Remove parts from inventory
- **Data Persistence**: Store data in comma-separated text files

## 📚 OOP Concepts Demonstrated

1. **Encapsulation**: Private fields with public getters/setters in `ComputerPart` class
2. **Abstraction**: Business logic separated in `PartManager` class
3. **Constructors**: Multiple constructors (default and parameterized)
4. **File I/O**: Reading and writing data to `.txt` files
5. **Collections**: Using ArrayList to manage data
6. **Event Handling**: JavaFX button and table selection events

## 📁 Project Structure

```
computer-parts-resale-store/
│
├── src/
│   ├── MainApp.java              # JavaFX Application (GUI)
│   ├── model/
│   │   └── ComputerPart.java     # Model class representing a computer part
│   └── service/
│       └── PartManager.java      # Service class for CRUD operations
│
├── data/
│   └── parts.txt                 # Data storage file (CSV format)
│
└── README.md                     # This file
```

## 🚀 How to Run

### Prerequisites
- Java JDK 11 or higher
- Maven (recommended) OR JavaFX SDK

### Option 1: Using Maven (Recommended - Easiest!)

**This is the simplest way - no manual JavaFX download needed!**

1. **Open the project** in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. IDE will detect `pom.xml` and ask to import as Maven project - click **Yes**
3. Maven automatically downloads JavaFX
4. **Run** `MainApp.java`

**Command Line:**
```bash
mvn clean compile
mvn javafx:run
```

📖 **Detailed Maven setup**: See `MAVEN-SETUP.md`

### Option 2: Using IDE with Manual JavaFX Setup

1. **Open the project** in your IDE
2. **Configure JavaFX**:
   - Download JavaFX SDK from [openjfx.io](https://openjfx.io/)
   - Add JavaFX libraries to your project
   - For IntelliJ: File → Project Structure → Libraries → Add JavaFX lib folder
3. **Add VM Options** (if needed):
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
   ```
4. **Run** `MainApp.java`

### Option 2: Command Line

1. **Navigate to project directory**:
   ```bash
   cd computer-parts-resale-store
   ```

2. **Compile** (replace with your JavaFX path):
   ```bash
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d bin src/model/*.java src/service/*.java src/*.java
   ```

3. **Run**:
   ```bash
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp bin MainApp
   ```

## 💡 Usage Guide

### Adding a New Part
1. Fill in all fields in the form (Name, Category, Price, Quantity)
2. Click **"Add Part"** button
3. The new part will appear in the table

### Updating a Part
1. **Click** on a row in the table to select it
2. The form will auto-fill with the selected part's data
3. **Modify** the fields as needed
4. Click **"Update Part"** button

### Deleting a Part
1. **Click** on a row in the table to select it
2. Click **"Delete Part"** button
3. **Confirm** the deletion in the dialog

### Clearing the Form
- Click **"Clear Form"** button to reset all fields

## 📊 Data Storage Format

Data is stored in `data/parts.txt` with comma-separated values:

```
id,name,category,price,quantity
```

Example:
```
1,Intel Core i7-13700K,CPU,399.99,15
2,NVIDIA RTX 4070,GPU,599.99,8
```

## 🎨 GUI Features

- **Modern Design**: Clean and intuitive interface
- **Color-coded Buttons**: 
  - Green for Add
  - Blue for Update
  - Red for Delete
  - Gray for Clear
- **Hover Effects**: Buttons change shade on hover
- **Status Bar**: Shows operation feedback
- **Table View**: Easy-to-read inventory display

## 📝 Code Explanation

### ComputerPart.java (Model)
- Represents a computer part with properties: id, name, category, price, quantity
- Includes methods to convert to/from CSV format
- Demonstrates encapsulation with private fields and public methods

### PartManager.java (Service Layer)
- Handles all CRUD operations
- Manages file I/O operations
- Loads data on startup and saves after each modification
- Auto-generates unique IDs for new parts

### MainApp.java (View/Controller)
- JavaFX Application class
- Creates the GUI with TableView and Form
- Handles user interactions (button clicks, selections)
- Updates the UI based on operations

## 🔧 Customization

### Adding New Fields
1. Add field to `ComputerPart` class with getter/setter
2. Update `toCSV()` and `fromCSV()` methods
3. Add new column to TableView in `MainApp`
4. Add new TextField to the form

### Changing Colors
Edit the color codes in `createStyledButton()` method:
- Add: `#27ae60` (green)
- Update: `#3498db` (blue)
- Delete: `#e74c3c` (red)
- Clear: `#95a5a6` (gray)

## ⚠️ Notes

- Data is automatically saved after each operation
- The application creates the `data` folder if it doesn't exist
- ID numbers are auto-generated and sequential
- Validation ensures all fields are filled before adding/updating

## 📖 Learning Objectives

This project helps you learn:
- ✅ Object-Oriented Programming basics
- ✅ JavaFX GUI development
- ✅ File handling in Java
- ✅ CRUD operations
- ✅ Event-driven programming
- ✅ MVC-like architecture (Model-View-Controller)

## 🤝 Contributing

This is a learning project. Feel free to:
- Add new features (search, sort, filter)
- Improve the UI design
- Add input validation
- Implement exception handling
- Add more OOP features (inheritance, polymorphism)

## 📄 License

Free to use for educational purposes.

---

**Happy Coding! 🚀**
