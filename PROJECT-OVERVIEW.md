# Project Overview - Computer Parts Resale Store

## 🎯 Project Goal

A beginner-friendly Java application demonstrating **Object-Oriented Programming** fundamentals and **JavaFX GUI** development through a simple inventory management system.

---

## ✨ Features Implemented

### ✅ CRUD Operations
- **Create**: Add new computer parts to inventory
- **Read**: View all parts in an interactive table
- **Update**: Modify existing part details
- **Delete**: Remove parts with confirmation dialog

### ✅ Data Persistence
- Data stored in `data/parts.txt`
- Comma-separated values (CSV) format
- Automatic save after each operation
- Auto-load on application startup

### ✅ Modern GUI
- Clean, intuitive interface
- Color-coded action buttons
- Real-time table updates
- Status notifications
- Form validation
- Hover effects on buttons

### ✅ Code Organization
- **Model-View-Service** architecture
- Separated concerns (data, logic, presentation)
- Well-commented code
- Beginner-friendly implementation

---

## 📊 Architecture Diagram

```
┌─────────────────────────────────────────────────┐
│              MainApp.java (View/Controller)     │
│  ┌───────────────────────────────────────────┐  │
│  │  - TableView (displays data)              │  │
│  │  - Form Fields (input)                    │  │
│  │  - Buttons (actions)                      │  │
│  │  - Event Handlers (logic)                 │  │
│  └───────────────────────────────────────────┘  │
└──────────────────────┬──────────────────────────┘
                       │ uses
                       ↓
┌─────────────────────────────────────────────────┐
│         PartManager.java (Service Layer)        │
│  ┌───────────────────────────────────────────┐  │
│  │  - addPart()      (CREATE)                │  │
│  │  - getAllParts()  (READ)                  │  │
│  │  - updatePart()   (UPDATE)                │  │
│  │  - deletePart()   (DELETE)                │  │
│  │  - loadFromFile() (persistence)           │  │
│  │  - saveToFile()   (persistence)           │  │
│  └───────────────────────────────────────────┘  │
└──────────────────────┬──────────────────────────┘
                       │ manages
                       ↓
┌─────────────────────────────────────────────────┐
│         ComputerPart.java (Model/Entity)        │
│  ┌───────────────────────────────────────────┐  │
│  │  Properties:                              │  │
│  │  - id, name, category, price, quantity    │  │
│  │                                           │  │
│  │  Methods:                                 │  │
│  │  - getters/setters (encapsulation)        │  │
│  │  - toCSV()    (serialization)             │  │
│  │  - fromCSV()  (deserialization)           │  │
│  └───────────────────────────────────────────┘  │
└──────────────────────┬──────────────────────────┘
                       │ persisted to
                       ↓
┌─────────────────────────────────────────────────┐
│              data/parts.txt (Storage)           │
│  ┌───────────────────────────────────────────┐  │
│  │  id,name,category,price,quantity          │  │
│  │  1,Intel Core i7,CPU,399.99,15            │  │
│  │  2,NVIDIA RTX 4070,GPU,599.99,8           │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
```

---

## 🧩 OOP Concepts Demonstrated

### 1. **Encapsulation** 
   - **Where**: `ComputerPart.java`
   - **How**: Private fields with public getters/setters
   - **Why**: Protects data and controls access
   
```java
private int id;
private String name;

public int getId() { return id; }
public void setId(int id) { this.id = id; }
```

### 2. **Constructors**
   - **Where**: `ComputerPart.java`
   - **How**: Default and parameterized constructors
   - **Why**: Flexible object creation
   
```java
public ComputerPart() { ... }
public ComputerPart(int id, String name, ...) { ... }
```

### 3. **Abstraction**
   - **Where**: `PartManager.java`
   - **How**: Public CRUD methods, private file I/O
   - **Why**: Hides complexity from the user
   
```java
public void addPart(ComputerPart part) { ... }
private void saveToFile() { ... }  // Hidden complexity
```

### 4. **Static Methods**
   - **Where**: `ComputerPart.fromCSV()`
   - **How**: Factory method for object creation
   - **Why**: Utility function without object instance

### 5. **Collections**
   - **Where**: `PartManager.java`
   - **How**: `ArrayList<ComputerPart>` to store parts
   - **Why**: Dynamic list management

### 6. **File I/O**
   - **Where**: `PartManager.java`
   - **How**: `BufferedReader` and `BufferedWriter`
   - **Why**: Data persistence

---

## 🎨 GUI Components

### Layout Structure
```
┌────────────────────────────────────────────────────────────┐
│                  Computer Parts Resale Store               │
├──────────────────────────────────────┬─────────────────────┤
│  Current Inventory:                  │  Part Details       │
│  ┌────────────────────────────────┐  │  ┌───────────────┐  │
│  │ ID │ Name │ Category │ Price   │  │  │ Name:         │  │
│  ├────┼──────┼──────────┼─────────┤  │  │ [TextField]   │  │
│  │ 1  │ i7   │ CPU      │ 399.99  │  │  │               │  │
│  │ 2  │ RTX  │ GPU      │ 599.99  │  │  │ Category:     │  │
│  │ 3  │ RAM  │ RAM      │ 129.99  │  │  │ [TextField]   │  │
│  └────────────────────────────────┘  │  │               │  │
│                                      │  │ Price:        │  │
│                                      │  │ [TextField]   │  │
│                                      │  │               │  │
│                                      │  │ Quantity:     │  │
│                                      │  │ [TextField]   │  │
│                                      │  │               │  │
│                                      │  │ [Add Part]    │  │
│                                      │  │ [Update Part] │  │
│                                      │  │ [Delete Part] │  │
│                                      │  │ [Clear Form]  │  │
│                                      │  └───────────────┘  │
├──────────────────────────────────────┴─────────────────────┤
│  Status: Ready                                             │
└────────────────────────────────────────────────────────────┘
```

### Components Used
- **BorderPane**: Main layout container
- **TableView**: Display inventory data
- **VBox/HBox**: Organize components
- **TextField**: User input fields
- **Button**: Action triggers
- **Label**: Text displays

---

## 📈 Data Flow

### Adding a New Part
```
User Input → Form Fields
     ↓
User Clicks "Add Part"
     ↓
handleAdd() validates input
     ↓
Create ComputerPart object
     ↓
partManager.addPart(part)
     ↓
Add to ArrayList<ComputerPart>
     ↓
saveToFile() writes to parts.txt
     ↓
refreshTable() updates GUI
     ↓
showStatus("Part added!")
```

### Loading Data on Startup
```
Application starts
     ↓
new PartManager()
     ↓
loadFromFile() called
     ↓
Read parts.txt line by line
     ↓
ComputerPart.fromCSV() for each line
     ↓
Add to ArrayList<ComputerPart>
     ↓
refreshTable() populates TableView
```

---

## 🎓 Learning Outcomes

After studying this project, you should understand:

### Programming Concepts
- ✅ Classes and objects
- ✅ Encapsulation with private fields
- ✅ Constructors (default and parameterized)
- ✅ Getters and setters
- ✅ Static vs instance methods
- ✅ Collections (ArrayList)
- ✅ String manipulation (split, trim)
- ✅ Type conversion (parsing)

### File I/O
- ✅ Reading from text files
- ✅ Writing to text files
- ✅ BufferedReader and BufferedWriter
- ✅ Exception handling (try-catch)
- ✅ CSV format

### JavaFX GUI
- ✅ Application lifecycle
- ✅ Scene and Stage
- ✅ Layout managers (BorderPane, VBox, HBox)
- ✅ TableView and TableColumn
- ✅ Event handling (lambda expressions)
- ✅ Property binding
- ✅ CSS styling in Java

### Software Architecture
- ✅ Separation of concerns
- ✅ Model-View-Controller pattern
- ✅ Service layer pattern
- ✅ Data persistence

---

## 📦 Files Explained

| File | Purpose | Lines | Complexity |
|------|---------|-------|------------|
| `ComputerPart.java` | Data model | ~100 | ⭐ Simple |
| `PartManager.java` | Business logic | ~150 | ⭐⭐ Moderate |
| `MainApp.java` | GUI + Controller | ~280 | ⭐⭐⭐ Complex |
| `parts.txt` | Data storage | Variable | ⭐ Simple |

### Suggested Study Order
1. Start with `ComputerPart.java` (understand the model)
2. Move to `PartManager.java` (learn CRUD and file I/O)
3. Finally study `MainApp.java` (explore GUI and events)

---

## 🚀 Running the Project

### Quick Start (3 Steps)
1. **Download JavaFX** from [openjfx.io](https://openjfx.io/)
2. **Configure IDE** (add JavaFX lib, set VM options)
3. **Run** `MainApp.java`

📖 **Detailed instructions**: See `SETUP-GUIDE.md`

---

## 🎯 Project Requirements Met

| Requirement | Status | Implementation |
|------------|---------|----------------|
| Basic CRUD | ✅ | Add, Read, Update, Delete in `PartManager` |
| Text file storage | ✅ | `parts.txt` with comma-separated values |
| Simple modern GUI | ✅ | JavaFX with color-coded buttons |
| Organized codebase | ✅ | Model-Service-View separation |
| Minimal code | ✅ | ~530 total lines, no complex patterns |
| Beginner-friendly | ✅ | Clear comments, simple logic |

---

## 🔍 Code Statistics

- **Total Files**: 3 Java files
- **Total Lines**: ~530 lines
- **Classes**: 3
- **Methods**: ~25
- **GUI Components**: 10+
- **OOP Concepts**: 6+

---

## 💡 Extension Ideas

### Easy Additions
- Add a "Condition" field (New/Used)
- Add "Brand" field
- Change color theme
- Add more validation

### Medium Additions
- Search functionality
- Sort by any column
- Filter by category
- Calculate total inventory value
- Export to CSV

### Advanced Additions
- Use SQLite database
- Add images for parts
- Implement user login
- Generate reports (PDF)
- Multi-language support

---

## 📚 Additional Resources

- **JavaFX Documentation**: [openjfx.io/javadoc](https://openjfx.io/javadoc/17/)
- **Java File I/O**: [docs.oracle.com/javase/tutorial/essential/io](https://docs.oracle.com/javase/tutorial/essential/io/)
- **OOP Concepts**: [docs.oracle.com/javase/tutorial/java/concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)

---

## ❓ FAQ

**Q: Why JavaFX and not Swing?**  
A: JavaFX is more modern, has better styling, and is Oracle's recommended GUI toolkit.

**Q: Why text files instead of a database?**  
A: Simpler for beginners to understand. Easy to see and edit data directly.

**Q: Can I use this for my school project?**  
A: Yes! Feel free to extend and customize it. Just understand the code first.

**Q: How do I add more features?**  
A: Start by adding a new field to `ComputerPart`, then update the CSV methods, then add a column to the table.

**Q: Is this production-ready?**  
A: No, it's a learning project. For production, you'd need: database, security, error handling, logging, testing, etc.

---

## 🎉 Success Criteria

You've successfully understood this project when you can:
- [ ] Explain what each class does
- [ ] Add a new field to the model
- [ ] Modify the GUI layout
- [ ] Understand the file I/O flow
- [ ] Implement a search feature
- [ ] Explain OOP concepts used

---

**Enjoy your learning journey! 🚀**

If you have questions, study the code, read the comments, and experiment!
