package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.MediaCenter;

public final class Welcome {
    private static Helper helper;
    private static MediaCenter model;

    public static void init(final Helper hlpr, final MediaCenter mdl) {
        Welcome.helper = hlpr;
        Welcome.model = mdl;
    }

    @FXML
    private Button loginButton;

    @FXML
    private Button adminButton;

    @FXML
    private Button guestButton;

    public void gotoLogin() {
        model.setAdmin(false);
        helper.redirectTo("login");
    }

    public void gotoAdmin() {
        model.setAdmin(true);
        helper.redirectTo("login");
    }

    public void gotoGuest() {
        model.logout();
        helper.redirectTo("main");
    }
}
