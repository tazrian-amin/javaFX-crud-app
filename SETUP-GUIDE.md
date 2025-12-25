# Setup Guide - Computer Parts Resale Store

This guide will help you set up and run the JavaFX application step by step.

## 📋 Prerequisites

Before you begin, make sure you have:
- Java JDK 11 or higher installed
- A Java IDE (IntelliJ IDEA, Eclipse, or NetBeans) **OR** command-line tools

## 🔍 Check Java Installation

Open terminal/command prompt and run:
```bash
java -version
```

You should see something like:
```
java version "17.0.1" 2021-10-19 LTS
```

If not installed, download from: [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)

---

## 🎨 Installing JavaFX

### Step 1: Download JavaFX SDK

1. Go to [https://openjfx.io/](https://openjfx.io/)
2. Click **"Download"**
3. Select your operating system (Windows, Mac, Linux)
4. Download the **SDK** (not jmods)
5. Extract the downloaded file to a location you'll remember

Example paths:
- **Windows**: `C:\Program Files\javafx-sdk-21`
- **Mac**: `/Users/yourusername/javafx-sdk-21`
- **Linux**: `/home/yourusername/javafx-sdk-21`

---

## 🚀 Running the Application

Choose the method that works best for you:

### Method 1: IntelliJ IDEA (Recommended for Beginners)

#### Step 1: Open Project
1. Open IntelliJ IDEA
2. Select **File → Open**
3. Navigate to the `computer-parts-resale-store` folder
4. Click **OK**

#### Step 2: Configure JavaFX
1. Go to **File → Project Structure** (or press `Cmd+;` on Mac, `Ctrl+Alt+Shift+S` on Windows)
2. Select **Libraries** in the left panel
3. Click the **+** button and select **Java**
4. Navigate to your JavaFX SDK folder and select the **lib** folder inside it
5. Click **OK** → **Apply** → **OK**

#### Step 3: Configure VM Options
1. Right-click on `MainApp.java` in the project explorer
2. Select **Modify Run Configuration** (or **Run → Edit Configurations**)
3. In the **VM options** field, add:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
   ```
   **Important**: Replace `/path/to/javafx-sdk/lib` with your actual JavaFX lib path
   
   Examples:
   - Windows: `--module-path "C:\Program Files\javafx-sdk-21\lib" --add-modules javafx.controls`
   - Mac: `--module-path /Users/yourusername/javafx-sdk-21/lib --add-modules javafx.controls`

4. Click **Apply** → **OK**

#### Step 4: Run
1. Right-click on `MainApp.java`
2. Select **Run 'MainApp.main()'**
3. The application window should open! 🎉

---

### Method 2: Eclipse

#### Step 1: Create Project
1. Open Eclipse
2. **File → New → Java Project**
3. Enter project name: `ComputerPartsStore`
4. Click **Finish**

#### Step 2: Import Source Files
1. Right-click on the project → **Import**
2. Select **General → File System**
3. Browse to your `computer-parts-resale-store` folder
4. Select the `src` folder
5. Click **Finish**

#### Step 3: Add JavaFX Library
1. Right-click on project → **Build Path → Configure Build Path**
2. Select **Libraries** tab
3. Click **Add External JARs**
4. Navigate to your JavaFX lib folder
5. Select **ALL** `.jar` files
6. Click **Open** → **Apply and Close**

#### Step 4: Configure Run Configuration
1. Right-click on `MainApp.java` → **Run As → Run Configurations**
2. Select **Java Application**
3. Go to **Arguments** tab
4. In **VM arguments**, add:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
   ```
5. Click **Apply** → **Run**

---

### Method 3: Command Line

#### Step 1: Navigate to Project
```bash
cd /path/to/computer-parts-resale-store
```

#### Step 2: Create bin Directory
```bash
mkdir bin
```

#### Step 3: Compile
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d bin src/model/*.java src/service/*.java src/*.java
```

**Replace** `/path/to/javafx-sdk/lib` with your actual path!

#### Step 4: Run
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp bin MainApp
```

---

### Method 4: Using the Shell Script (Mac/Linux)

#### Step 1: Edit the Script
1. Open `compile-and-run.sh` in a text editor
2. Find the line: `JAVAFX_PATH="/path/to/javafx-sdk/lib"`
3. Replace with your actual JavaFX lib path
4. Save the file

#### Step 2: Make it Executable
```bash
chmod +x compile-and-run.sh
```

#### Step 3: Run
```bash
./compile-and-run.sh
```

---

## ✅ Verifying Installation

When you run the application, you should see:
- A window titled **"Computer Parts Store Manager"**
- A table showing sample computer parts
- A form on the right side with input fields
- Buttons: Add Part, Update Part, Delete Part, Clear Form

---

## ❌ Troubleshooting

### Error: "JavaFX runtime components are missing"
**Solution**: You haven't configured JavaFX properly. Go back and add VM options.

### Error: "Error: Could not find or load main class MainApp"
**Solution**: Make sure you're in the correct directory and the bin folder contains compiled `.class` files.

### Error: "class file has wrong version"
**Solution**: Your Java version might be too old. Make sure you have JDK 11 or higher.

### Error: "Graphics Device initialization failed"
**Solution**: If running on a server or headless environment, JavaFX won't work. You need a graphical environment.

### VM Options Not Working in IntelliJ
**Solution**: Make sure there are no extra quotes or spaces in the VM options field.

---

## 🎯 Testing the Application

Once running, try these operations:

1. **Add a new part**:
   - Fill in: Name = "AMD Ryzen 9", Category = "CPU", Price = 499.99, Quantity = 10
   - Click "Add Part"
   - Verify it appears in the table

2. **Update a part**:
   - Click on any row in the table
   - Change the price
   - Click "Update Part"
   - Verify the change in the table

3. **Delete a part**:
   - Click on any row
   - Click "Delete Part"
   - Confirm the deletion
   - Verify it's removed from the table

4. **Check data persistence**:
   - Close the application
   - Open `data/parts.txt` to see the stored data
   - Run the application again
   - Verify the data is still there

---

## 📚 Next Steps

Now that your application is running:
1. Study the code in each file
2. Try modifying the GUI colors
3. Add a new field (e.g., "Brand")
4. Implement search functionality
5. Add data validation

---

## 🆘 Still Having Issues?

Common checks:
- ✅ Java JDK 11+ installed?
- ✅ JavaFX SDK downloaded and extracted?
- ✅ Correct path to JavaFX lib folder?
- ✅ VM options configured?
- ✅ All source files in the right folders?

If you're still stuck, double-check the paths and make sure there are no typos!

---

**Good luck! 🚀**
