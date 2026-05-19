package controller;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void signup(ActionEvent event) {

        try {

            Connection connect = DBConnection.connect();

            String sql = "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, usernameField.getText());
            prepare.setString(2, passwordField.getText());

            prepare.executeUpdate();

            System.out.println("Signup Successful");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void backToLogin(ActionEvent event) {

        try {

            File file = new File("src/view/login.fxml");

            Parent root = FXMLLoader.load(file.toURI().toURL());

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            usernameField.getScene().getWindow().hide();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}