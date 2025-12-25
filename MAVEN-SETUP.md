# Maven Setup Guide (Recommended)

This is the **easiest way** to run the project without manually downloading JavaFX!

## ✅ Prerequisites

- Java JDK 11 or higher
- Maven installed (or use IDE's built-in Maven)

### Check if Maven is installed:
```bash
mvn -version
```

If not installed: [https://maven.apache.org/install.html](https://maven.apache.org/install.html)

---

## 🚀 Quick Start

### Option 1: Using IntelliJ IDEA (Recommended)

1. **Open Project**
   - File → Open
   - Select the `computer-parts-resale-store` folder
   - IntelliJ will auto-detect `pom.xml` and ask to import as Maven project
   - Click **"Import"** or **"Load Maven Project"**

2. **Wait for Dependencies**
   - Maven will automatically download JavaFX (see bottom right progress bar)
   - This may take 1-2 minutes on first run

3. **Run the Application**
   - Right-click on `src/MainApp.java`
   - Select **"Run 'MainApp.main()'"**
   - Done! 🎉

### Option 2: Using Eclipse

1. **Import Maven Project**
   - File → Import → Maven → Existing Maven Projects
   - Browse to `computer-parts-resale-store` folder
   - Click **Finish**

2. **Wait for Dependencies**
   - Eclipse will download JavaFX automatically
   - Check bottom right for progress

3. **Run**
   - Right-click `MainApp.java` → Run As → Java Application

### Option 3: Using VS Code

1. **Install Extensions**
   - Extension Pack for Java
   - Maven for Java

2. **Open Folder**
   - File → Open Folder → Select project folder
   - VS Code will detect `pom.xml`

3. **Run**
   - Click Run button above `public static void main` in MainApp.java

### Option 4: Command Line

1. **Navigate to project folder**
   ```bash
   cd computer-parts-resale-store
   ```

2. **Clean and compile**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

---

## 🔧 Troubleshooting

### Error: "mvn command not found"
**Solution**: Install Maven from [maven.apache.org](https://maven.apache.org/install.html)

### Error: "Failed to execute goal"
**Solution**: Make sure you have JDK 11+ (not JRE)
```bash
java -version  # Should show 11 or higher
```

### IntelliJ doesn't detect Maven project
**Solution**: 
1. Right-click on `pom.xml`
2. Select **"Add as Maven Project"**

### Dependencies not downloading
**Solution**: 
1. Right-click on project → Maven → Reload Project
2. Or run: `mvn clean install -U`

---

## 🎯 Why Maven?

✅ **No manual JavaFX download needed**  
✅ **Automatic dependency management**  
✅ **Works on any OS (Windows, Mac, Linux)**  
✅ **No VM options needed**  
✅ **Industry standard tool**  

---

## 📦 What Maven Does

1. Reads `pom.xml` configuration
2. Downloads JavaFX libraries automatically
3. Sets up classpath correctly
4. Compiles and runs your project

---

## 🎓 Maven Commands

| Command | Purpose |
|---------|---------|
| `mvn clean` | Remove compiled files |
| `mvn compile` | Compile source code |
| `mvn javafx:run` | Run the JavaFX application |
| `mvn package` | Create JAR file |
| `mvn clean install` | Full build |

---

**This is now the recommended way to run the project!** 🚀
