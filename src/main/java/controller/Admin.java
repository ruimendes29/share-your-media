package controller;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

import model.MediaCenter;

public final class Admin {
    private static Helper helper;
    private static MediaCenter model;

    public static void init(final Helper hlpr, final MediaCenter mdl) {
        Admin.helper = hlpr;
        Admin.model = mdl;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    void logout(final ActionEvent event) {
        model.logout();
        helper.redirectTo("welcome");
    }
}
