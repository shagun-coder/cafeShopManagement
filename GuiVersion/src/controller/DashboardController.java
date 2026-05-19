package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DashboardController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    public void addProduct() {

        System.out.println("ADD");
    }

    public void updateProduct() {

        System.out.println("UPDATE");
    }

    public void deleteProduct() {

        System.out.println("DELETE");
    }

    public void clearFields() {

        idField.clear();
        nameField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();
    }
}