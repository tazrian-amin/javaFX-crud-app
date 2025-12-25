import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Product;
import model.ComputerPart;
import model.Accessory;
import service.ProductManager;
import exception.DataFileException;
import exception.InvalidProductException;

/**
 * MainApp - JavaFX Application with Multithreading and Exception Handling
 * Demonstrates: GUI, Event Handling, Multithreading, Exception Handling, Polymorphism
 */
public class MainApp extends Application {
    
    private ProductManager productManager;
    private TableView<Product> tableView;
    private TextField nameField, categoryField, priceField, quantityField;
    private ComboBox<String> typeComboBox;
    private Button addButton, updateButton, deleteButton, clearButton;
    private Label statusLabel;
    private ProgressIndicator progressIndicator;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize with loading indicator
        showLoadingScreen(primaryStage);
        
        // Load data in background thread (Multithreading)
        Task<Void> loadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    productManager = new ProductManager();
                } catch (DataFileException e) {
                    throw e;
                }
                return null;
            }
            
            @Override
            protected void succeeded() {
                Platform.runLater(() -> showMainScreen(primaryStage));
            }
            
            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    showErrorDialog("Startup Error", 
                        "Failed to load data: " + getException().getMessage());
                    primaryStage.close();
                });
            }
        };
        
        // Start background loading
        new Thread(loadTask).start();
    }
    
    private void showLoadingScreen(Stage stage) {
        VBox loadingBox = new VBox(20);
        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setStyle("-fx-background-color: #f5f5f5;");
        
        Label loadingLabel = new Label("Loading Computer Parts Store...");
        loadingLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        ProgressIndicator loading = new ProgressIndicator();
        loading.setMaxSize(60, 60);
        
        loadingBox.getChildren().addAll(loading, loadingLabel);
        
        Scene scene = new Scene(loadingBox, 400, 300);
        stage.setTitle("Computer Parts Store Manager");
        stage.setScene(scene);
        stage.show();
    }
    
    private void showMainScreen(Stage primaryStage) {
        // Main container
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Title
        Label titleLabel = new Label("Computer Parts Resale Store");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 15, 0));
        root.setTop(titleBox);
        
        // Center - Table View
        tableView = createTableView();
        VBox tableBox = new VBox(10, new Label("Current Inventory:"), tableView);
        tableBox.setPadding(new Insets(10));
        root.setCenter(tableBox);
        
        // Right - Form for CRUD operations
        VBox formBox = createFormPanel();
        root.setRight(formBox);
        
        // Bottom - Status bar with progress indicator
        HBox statusBox = createStatusBar();
        root.setBottom(statusBox);
        
        // Create scene and show
        Scene scene = new Scene(root, 1100, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Computer Parts Store Manager");
        
        // Load initial data
        refreshTable();
    }
    
    private TableView<Product> createTableView() {
        TableView<Product> table = new TableView<>();
        table.setStyle("-fx-background-color: white;");
        
        // ID Column
        TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        
        // Type Column (demonstrates polymorphism)
        TableColumn<Product, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setPrefWidth(100);
        
        // Name Column
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);
        
        // Category/Brand Column (polymorphic display)
        TableColumn<Product, String> detailCol = new TableColumn<>("Details");
        detailCol.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            if (product instanceof ComputerPart) {
                return new javafx.beans.property.SimpleStringProperty(
                    ((ComputerPart) product).getCategory());
            } else if (product instanceof Accessory) {
                return new javafx.beans.property.SimpleStringProperty(
                    ((Accessory) product).getBrand());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        detailCol.setPrefWidth(120);
        
        // Price Column
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(80);
        
        // Quantity Column
        TableColumn<Product, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setPrefWidth(80);
        
        // Total Value Column
        TableColumn<Product, Double> valueCol = new TableColumn<>("Total Value");
        valueCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(
                cellData.getValue().getTotalValue()).asObject());
        valueCol.setPrefWidth(100);
        
        table.getColumns().add(idCol);
        table.getColumns().add(typeCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(detailCol);
        table.getColumns().add(priceCol);
        table.getColumns().add(quantityCol);
        table.getColumns().add(valueCol);
        
        // Handle row selection
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadProductToForm(newSelection);
            }
        });
        
        return table;
    }
    
    private VBox createFormPanel() {
        VBox formBox = new VBox(10);
        formBox.setPadding(new Insets(10, 0, 10, 15));
        formBox.setPrefWidth(320);
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 15;");
        
        Label formTitle = new Label("Product Details");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        // Product Type Selection
        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Computer Part", "Accessory");
        typeComboBox.setValue("Computer Part");
        typeComboBox.setMaxWidth(Double.MAX_VALUE);
        typeComboBox.setStyle("-fx-padding: 8;");
        typeComboBox.setOnAction(e -> updateFormLabels());
        
        // Input fields
        nameField = new TextField();
        nameField.setPromptText("Product Name");
        nameField.setStyle("-fx-padding: 8;");
        
        categoryField = new TextField();
        categoryField.setPromptText("Category (for Computer Part)");
        categoryField.setStyle("-fx-padding: 8;");
        
        priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setStyle("-fx-padding: 8;");
        
        quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setStyle("-fx-padding: 8;");
        
        // Buttons
        addButton = createStyledButton("Add Product", "#27ae60");
        updateButton = createStyledButton("Update Product", "#3498db");
        deleteButton = createStyledButton("Delete Product", "#e74c3c");
        clearButton = createStyledButton("Clear Form", "#95a5a6");
        
        // Button actions
        addButton.setOnAction(e -> handleAdd());
        updateButton.setOnAction(e -> handleUpdate());
        deleteButton.setOnAction(e -> handleDelete());
        clearButton.setOnAction(e -> clearForm());
        
        // Add all to form
        formBox.getChildren().addAll(
            formTitle,
            new Separator(),
            new Label("Product Type:"), typeComboBox,
            new Label("Name:"), nameField,
            new Label("Category/Brand:"), categoryField,
            new Label("Price:"), priceField,
            new Label("Quantity:"), quantityField,
            new Separator(),
            addButton, updateButton, deleteButton, clearButton
        );
        
        return formBox;
    }
    
    private HBox createStatusBar() {
        HBox statusBox = new HBox(10);
        statusBox.setPadding(new Insets(10, 0, 0, 0));
        statusBox.setAlignment(Pos.CENTER_LEFT);
        
        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        
        progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(20, 20);
        progressIndicator.setVisible(false);
        
        statusBox.getChildren().addAll(statusLabel, progressIndicator);
        return statusBox;
    }
    
    private void updateFormLabels() {
        String type = typeComboBox.getValue();
        if ("Computer Part".equals(type)) {
            categoryField.setPromptText("Category (e.g., CPU, GPU, RAM)");
        } else {
            categoryField.setPromptText("Brand (e.g., Logitech, Razer)");
        }
    }
    
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; " +
                       "-fx-font-weight: bold; -fx-padding: 10; -fx-background-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: derive(" + color + ", -10%); -fx-text-fill: white; " +
            "-fx-font-weight: bold; -fx-padding: 10; -fx-background-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: " + color + "; -fx-text-fill: white; " +
            "-fx-font-weight: bold; -fx-padding: 10; -fx-background-radius: 5;"));
        return button;
    }
    
    private void handleAdd() {
        // Multithreading: Perform add operation in background
        Task<Void> addTask = new Task<Void>() {
            private String errorMessage;
            
            @Override
            protected Void call() throws Exception {
                try {
                    String name = nameField.getText().trim();
                    String detail = categoryField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    
                    if (name.isEmpty() || detail.isEmpty()) {
                        throw new InvalidProductException("Please fill all fields!");
                    }
                    
                    int id = productManager.getNextId();
                    Product product;
                    
                    // Polymorphism: Create appropriate product type
                    if ("Computer Part".equals(typeComboBox.getValue())) {
                        product = new ComputerPart(id, name, detail, price, quantity);
                    } else {
                        product = new Accessory(id, name, detail, price, quantity);
                    }
                    
                    productManager.addProduct(product);  // May throw exceptions
                    
                } catch (NumberFormatException e) {
                    errorMessage = "Invalid price or quantity format!";
                    throw new Exception(errorMessage);
                } catch (InvalidProductException | DataFileException e) {
                    errorMessage = e.getMessage();
                    throw e;
                }
                return null;
            }
            
            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    refreshTable();
                    clearForm();
                    showStatus("Product added successfully!", "#27ae60");
                });
            }
            
            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    showErrorDialog("Add Product Error", errorMessage != null ? errorMessage : getException().getMessage());
                    showStatus("Failed to add product", "#e74c3c");
                });
            }
        };
        
        executeTask(addTask, "Adding product...");
    }
    
    private void handleUpdate() {
        Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showErrorDialog("Update Error", "Please select a product to update!");
            return;
        }
        
        // Multithreading: Perform update operation in background
        Task<Void> updateTask = new Task<Void>() {
            private String errorMessage;
            
            @Override
            protected Void call() throws Exception {
                try {
                    String name = nameField.getText().trim();
                    String detail = categoryField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    
                    if (name.isEmpty() || detail.isEmpty()) {
                        throw new InvalidProductException("Please fill all fields!");
                    }
                    
                    Product updatedProduct;
                    
                    // Polymorphism: Create appropriate product type
                    if ("Computer Part".equals(typeComboBox.getValue())) {
                        updatedProduct = new ComputerPart(selectedProduct.getId(), name, detail, price, quantity);
                    } else {
                        updatedProduct = new Accessory(selectedProduct.getId(), name, detail, price, quantity);
                    }
                    
                    productManager.updateProduct(selectedProduct.getId(), updatedProduct);
                    
                } catch (NumberFormatException e) {
                    errorMessage = "Invalid price or quantity format!";
                    throw new Exception(errorMessage);
                } catch (InvalidProductException | DataFileException e) {
                    errorMessage = e.getMessage();
                    throw e;
                }
                return null;
            }
            
            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    refreshTable();
                    clearForm();
                    showStatus("Product updated successfully!", "#3498db");
                });
            }
            
            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    showErrorDialog("Update Product Error", errorMessage != null ? errorMessage : getException().getMessage());
                    showStatus("Failed to update product", "#e74c3c");
                });
            }
        };
        
        executeTask(updateTask, "Updating product...");
    }
    
    private void handleDelete() {
        Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showErrorDialog("Delete Error", "Please select a product to delete!");
            return;
        }
        
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Delete");
        confirmDialog.setHeaderText("Delete Product");
        confirmDialog.setContentText("Are you sure you want to delete: " + selectedProduct.getName() + "?");
        
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Multithreading: Perform delete operation in background
                Task<Void> deleteTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            productManager.deleteProduct(selectedProduct.getId());
                        } catch (DataFileException | InvalidProductException e) {
                            throw e;
                        }
                        return null;
                    }
                    
                    @Override
                    protected void succeeded() {
                        Platform.runLater(() -> {
                            refreshTable();
                            clearForm();
                            showStatus("Product deleted successfully!", "#e74c3c");
                        });
                    }
                    
                    @Override
                    protected void failed() {
                        Platform.runLater(() -> {
                            showErrorDialog("Delete Product Error", getException().getMessage());
                            showStatus("Failed to delete product", "#e74c3c");
                        });
                    }
                };
                
                executeTask(deleteTask, "Deleting product...");
            }
        });
    }
    
    private void loadProductToForm(Product product) {
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantity()));
        
        // Polymorphism: Load type-specific details
        if (product instanceof ComputerPart) {
            typeComboBox.setValue("Computer Part");
            categoryField.setText(((ComputerPart) product).getCategory());
        } else if (product instanceof Accessory) {
            typeComboBox.setValue("Accessory");
            categoryField.setText(((Accessory) product).getBrand());
        }
    }
    
    private void clearForm() {
        nameField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();
        typeComboBox.setValue("Computer Part");
        tableView.getSelectionModel().clearSelection();
        showStatus("Ready", "#27ae60");
    }
    
    private void refreshTable() {
        tableView.getItems().clear();
        tableView.getItems().addAll(productManager.getAllProducts());
        
        // Update status with total inventory value
        double totalValue = productManager.getTotalInventoryValue();
        showStatus(String.format("Ready | Total Inventory Value: $%.2f", totalValue), "#27ae60");
    }
    
    private void showStatus(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-weight: bold;");
    }
    
    private void executeTask(Task<?> task, String statusMessage) {
        showStatus(statusMessage, "#3498db");
        progressIndicator.setVisible(true);
        
        task.setOnSucceeded(e -> progressIndicator.setVisible(false));
        task.setOnFailed(e -> progressIndicator.setVisible(false));
        
        // Run task in background thread
        new Thread(task).start();
    }
    
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
