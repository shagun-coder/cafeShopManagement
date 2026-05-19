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
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void login(ActionEvent event) {

        try {

            Connection connect = DBConnection.connect();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, usernameField.getText());
            prepare.setString(2, passwordField.getText());

            ResultSet result = prepare.executeQuery();

            if (result.next()) {

                File file = new File("src/view/dashboard.fxml");

                Parent root = FXMLLoader.load(file.toURI().toURL());

                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                usernameField.getScene().getWindow().hide();

            } else {

                System.out.println("Wrong Username or Password");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void openSignup(ActionEvent event) {

        try {

            File file = new File("src/view/signup.fxml");

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