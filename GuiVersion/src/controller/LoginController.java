package controller;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public TextField usernameField;
    public PasswordField passwordField;

    Connection connect;
    PreparedStatement prepare;
    ResultSet result;

    // LOGIN
    public void login(ActionEvent event) {

        try {

            connect = DBConnection.connect();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            prepare = connect.prepareStatement(sql);

            prepare.setString(1, usernameField.getText());
            prepare.setString(2, passwordField.getText());

            result = prepare.executeQuery();

            boolean check = result.next();

            System.out.println(check);

            if (check) {

                Parent root = FXMLLoader.load(
                        getClass().getResource("/view/dashboard.fxml"));

                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

            } else {

                System.out.println("Wrong Username or Password");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // OPEN SIGNUP PAGE
    public void openSignup(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/signup.fxml"));

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}