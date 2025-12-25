import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.ComputerPart;
import service.PartManager;

/**
 * MainApp - JavaFX Application for Computer Parts Resale Store
 * Demonstrates GUI creation and event handling
 */
public class MainApp extends Application {
    
    private PartManager partManager;
    private TableView<ComputerPart> tableView;
    private TextField nameField, categoryField, priceField, quantityField;
    private Button addButton, updateButton, deleteButton, clearButton;
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        partManager = new PartManager();
        
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
        
        // Bottom - Status bar
        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        HBox statusBox = new HBox(statusLabel);
        statusBox.setPadding(new Insets(10, 0, 0, 0));
        root.setBottom(statusBox);
        
        // Create scene and show
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Computer Parts Store Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Load initial data
        refreshTable();
    }
    
    private TableView<ComputerPart> createTableView() {
        TableView<ComputerPart> table = new TableView<>();
        table.setStyle("-fx-background-color: white;");
        
        // ID Column
        TableColumn<ComputerPart, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        
        // Name Column
        TableColumn<ComputerPart, String> nameCol = new TableColumn<>("Part Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);
        
        // Category Column
        TableColumn<ComputerPart, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(120);
        
        // Price Column
        TableColumn<ComputerPart, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(80);
        
        // Quantity Column
        TableColumn<ComputerPart, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setPrefWidth(80);
        
        table.getColumns().addAll(idCol, nameCol, categoryCol, priceCol, quantityCol);
        
        // Handle row selection
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadPartToForm(newSelection);
            }
        });
        
        return table;
    }
    
    private VBox createFormPanel() {
        VBox formBox = new VBox(10);
        formBox.setPadding(new Insets(10, 0, 10, 15));
        formBox.setPrefWidth(300);
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 15;");
        
        Label formTitle = new Label("Part Details");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        // Input fields
        nameField = new TextField();
        nameField.setPromptText("Part Name");
        nameField.setStyle("-fx-padding: 8;");
        
        categoryField = new TextField();
        categoryField.setPromptText("Category (e.g., CPU, GPU, RAM)");
        categoryField.setStyle("-fx-padding: 8;");
        
        priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setStyle("-fx-padding: 8;");
        
        quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setStyle("-fx-padding: 8;");
        
        // Buttons
        addButton = createStyledButton("Add Part", "#27ae60");
        updateButton = createStyledButton("Update Part", "#3498db");
        deleteButton = createStyledButton("Delete Part", "#e74c3c");
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
            new Label("Name:"), nameField,
            new Label("Category:"), categoryField,
            new Label("Price:"), priceField,
            new Label("Quantity:"), quantityField,
            new Separator(),
            addButton, updateButton, deleteButton, clearButton
        );
        
        return formBox;
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
        try {
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            
            if (name.isEmpty() || category.isEmpty()) {
                showStatus("Please fill all fields!", "#e74c3c");
                return;
            }
            
            int id = partManager.getNextId();
            ComputerPart part = new ComputerPart(id, name, category, price, quantity);
            partManager.addPart(part);
            
            refreshTable();
            clearForm();
            showStatus("Part added successfully!", "#27ae60");
            
        } catch (NumberFormatException e) {
            showStatus("Invalid price or quantity!", "#e74c3c");
        }
    }
    
    private void handleUpdate() {
        ComputerPart selectedPart = tableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            showStatus("Please select a part to update!", "#e74c3c");
            return;
        }
        
        try {
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            
            if (name.isEmpty() || category.isEmpty()) {
                showStatus("Please fill all fields!", "#e74c3c");
                return;
            }
            
            ComputerPart updatedPart = new ComputerPart(selectedPart.getId(), name, category, price, quantity);
            partManager.updatePart(selectedPart.getId(), updatedPart);
            
            refreshTable();
            clearForm();
            showStatus("Part updated successfully!", "#3498db");
            
        } catch (NumberFormatException e) {
            showStatus("Invalid price or quantity!", "#e74c3c");
        }
    }
    
    private void handleDelete() {
        ComputerPart selectedPart = tableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            showStatus("Please select a part to delete!", "#e74c3c");
            return;
        }
        
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Delete");
        confirmDialog.setHeaderText("Delete Part");
        confirmDialog.setContentText("Are you sure you want to delete: " + selectedPart.getName() + "?");
        
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                partManager.deletePart(selectedPart.getId());
                refreshTable();
                clearForm();
                showStatus("Part deleted successfully!", "#e74c3c");
            }
        });
    }
    
    private void loadPartToForm(ComputerPart part) {
        nameField.setText(part.getName());
        categoryField.setText(part.getCategory());
        priceField.setText(String.valueOf(part.getPrice()));
        quantityField.setText(String.valueOf(part.getQuantity()));
    }
    
    private void clearForm() {
        nameField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();
        tableView.getSelectionModel().clearSelection();
        showStatus("Ready", "#27ae60");
    }
    
    private void refreshTable() {
        tableView.getItems().clear();
        tableView.getItems().addAll(partManager.getAllParts());
    }
    
    private void showStatus(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-weight: bold;");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

