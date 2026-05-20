package controller;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // Input UI Components
    @FXML
    private TextField prodIdField;
    @FXML
    private TextField prodNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private ComboBox<String> statusBox;

    // Table view references
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> colId;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, String> colCategory;
    @FXML
    private TableColumn<Product, Double> colPrice;
    @FXML
    private TableColumn<Product, Integer> colQuantity;
    @FXML
    private TableColumn<Product, String> colStatus;

    // Multiple screen switching handles
    @FXML
    private VBox productsForm;
    @FXML
    private VBox otherViewsForm;
    @FXML
    private Label otherViewLabel;

    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind column factories to Model fields
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Pull initial records out from database onto table view
        showProductsTable();
    }

    // Read values straight out from local database
    public void showProductsTable() {
        productList.clear();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null)
                return;

            while (rs.next()) {
                Product prod = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("status"));
                productList.add(prod);
            }
            productTable.setItems(productList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ➕ ADD PRODUCT ACTION LOGIC
    @FXML
    public void handleAddProduct() {
        String sql = "INSERT INTO products (product_id, name, category, price, quantity, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (validateInputs())
                return;

            stmt.setInt(1, Integer.parseInt(prodIdField.getText().trim()));
            stmt.setString(2, prodNameField.getText().trim());
            stmt.setString(3, categoryBox.getValue());
            stmt.setDouble(4, Double.parseDouble(priceField.getText().trim()));
            stmt.setInt(5, Integer.parseInt(quantityField.getText().trim()));
            stmt.setString(6, statusBox.getValue());

            stmt.executeUpdate();
            showAlert("Success", "Product successfully registered to records!", Alert.AlertType.INFORMATION);

            showProductsTable(); // Refresh table items
            handleClearFields(); // Clear data entry cells

        } catch (Exception e) {
            showAlert("Database Error", "Check database fields or duplicate entry ID. Trace: " + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    // 📝 UPDATE PRODUCT ACTION LOGIC
    @FXML
    public void handleUpdateProduct() {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ?, status = ? WHERE product_id = ?";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (validateInputs())
                return;

            stmt.setString(1, prodNameField.getText().trim());
            stmt.setString(2, categoryBox.getValue());
            stmt.setDouble(3, Double.parseDouble(priceField.getText().trim()));
            stmt.setInt(4, Integer.parseInt(quantityField.getText().trim()));
            stmt.setString(5, statusBox.getValue());
            stmt.setInt(6, Integer.parseInt(prodIdField.getText().trim()));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert("Success", "Product updated successfully!", Alert.AlertType.INFORMATION);
                showProductsTable();
                handleClearFields();
            } else {
                showAlert("Error", "Product ID not found to modify.", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            showAlert("Execution Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // 🗑️ DELETE PRODUCT ACTION LOGIC
    @FXML
    public void handleDeleteProduct() {
        String idText = prodIdField.getText().trim();
        if (idText.isEmpty()) {
            showAlert("Input Error", "Please input a valid Product ID to target deletion.", Alert.AlertType.WARNING);
            return;
        }

        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idText));
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                showAlert("Success", "Product removed from menu inventory.", Alert.AlertType.INFORMATION);
                showProductsTable();
                handleClearFields();
            } else {
                showAlert("Error", "No matching item found with that ID.", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            showAlert("Error Execution", "Failed deleting row target.", Alert.AlertType.ERROR);
        }
    }

    // 🔄 CLEAR FIELD CELL ELEMENT ENTRIES
    @FXML
    public void handleClearFields() {
        prodIdField.clear();
        prodNameField.clear();
        priceField.clear();
        quantityField.clear();
        categoryBox.setValue(null);
        statusBox.setValue(null);
    }

    // Autofill form fields when clicking on a table item row
    @FXML
    public void handleTableClick() {
        Product selectedProd = productTable.getSelectionModel().getSelectedItem();
        if (selectedProd != null) {
            prodIdField.setText(String.valueOf(selectedProd.getId()));
            prodNameField.setText(selectedProd.getName());
            categoryBox.setValue(selectedProd.getCategory());
            priceField.setText(String.valueOf(selectedProd.getPrice()));
            quantityField.setText(String.valueOf(selectedProd.getQuantity()));
            statusBox.setValue(selectedProd.getStatus());
        }
    }

    private boolean validateInputs() {
        if (prodIdField.getText().isEmpty() || prodNameField.getText().isEmpty() ||
                priceField.getText().isEmpty() || quantityField.getText().isEmpty() ||
                categoryBox.getValue() == null || statusBox.getValue() == null) {
            showAlert("Form Validation", "All workspace entry cells must be fully configured.",
                    Alert.AlertType.WARNING);
            return true;
        }
        return false;
    }

    private void showAlert(String title, String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    // ==========================================
    // 🧭 DYNAMIC SIDEBAR WINDOW SWITCH SWITCHES
    // ==========================================

    @FXML
    public void handleDashboardNav() {
        productsForm.setVisible(false);
        otherViewsForm.setVisible(true);
        otherViewLabel.setText("📊 Live Performance Dashboard");
    }

    @FXML
    public void handleProductsNav() {
        otherViewsForm.setVisible(false);
        productsForm.setVisible(true);
    }

    @FXML
    public void handleOrdersNav() {
        productsForm.setVisible(false);
        otherViewsForm.setVisible(true);
        otherViewLabel.setText("🛒 Active Kitchen Queue / POS Module");
    }

    @FXML
    public void handleCustomersNav() {
        productsForm.setVisible(false);
        otherViewsForm.setVisible(true);
        otherViewLabel.setText("👥 Customer Loyalty Accounts");
    }

    @FXML
    public void handleLogout() {
        try {
            Stage currentStage = (Stage) prodIdField.getScene().getWindow();
            currentStage.close();

            Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Cafe Management System - Login");
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}