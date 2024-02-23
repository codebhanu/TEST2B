package com.example.test2b;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class LoginController implements Initializable {
    @FXML
    private Label errorText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    private int failedAttempts = 0;



    @FXML
    protected void onLoginClick() {
       String Username = null,Password=null;
       String nameforuser=null;

        if (failedAttempts>=5) {

            errorText.setText("YourAccount is locked!!!");
        }
        else{   // Get entered username and password
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            // Check if either username or password is empty
            if (enteredUsername.isEmpty()) {
                // Display error message for empty fields
                errorText.setText("Please Provide Username and Password.");
            } else {
                String  query= "SELECT * FROM User_login_info WHERE email = ?";
                String userName=usernameField.getText();
                try (Connection conn = Database.getConnection();
                     PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, userName);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Username=rs.getString("email");
                             Password=rs.getString("password");
                            nameforuser=rs.getString("username");


                        }else {
                            errorText.setText("User Name or Password is wrong");

                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Check if entered credentials match
                if (enteredUsername.equals(Username) && enteredPassword.equals(Password)) {
                    // Authentication success
                    failedAttempts=0;
                    try {
                        // Loading the second view
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                        Parent root = loader.load();


                        Stage stage = (Stage) usernameField.getScene().getWindow();
    // sending username to another controller
                        HelloController secondController  = loader.getController();

                        secondController.setUsername(nameforuser);
                        secondController.displayUsername();



                        Scene scene = new Scene(root);
                        stage.setScene(scene);

                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errorText.setText("Failed to load the view.");

                    }


                } else {

                    if (enteredUsername.equals(Username)){
                        failedAttempts++;
                        errorText.setText(" Either username or password is wrong "+failedAttempts+" out of 5");
                        return;

                    }
                    // Authentication failure
                    errorText.setText("User Name or Password is wrong");


                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}