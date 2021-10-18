package controller;

import exceptions.AuthenticationException;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.MediaCenter;

public final class Login {
    private static Helper helper;
    private static MediaCenter model;

    public static void init(final Helper hlpr, final MediaCenter mdl) {
        Login.helper = hlpr;
        Login.model = mdl;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    void back(final ActionEvent event) {
        helper.redirectTo("welcome");
    }

    @FXML
    void login(final ActionEvent event) {
        if (model.isAdmin()) {
            try {
                model.loginAdmin(emailTextField.getText(), passwordPasswordField.getText());
                helper.redirectTo("admin");
            } catch (AuthenticationException e) {
                helper.error("Authentication Error", e.getMessage());
            }
        } else {
            try {
                model.loginUser(emailTextField.getText(), passwordPasswordField.getText());
                helper.redirectTo("main");
            } catch (AuthenticationException e) {
                helper.error("Authentication Error", e.getMessage());
            }
        }
    }
}
