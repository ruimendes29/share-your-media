package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.util.Map.entry;

public final class Helper {
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 800;

    private static Stage stage;

    public static void init(final Stage stg) {
        Helper.stage = stg;
    }

    private final Map<String, Scene> scenes = Map.ofEntries(//
            entry("welcome", newScene("welcome.fxml")), //
            entry("login", newScene("login.fxml")), //
            entry("main", newScene("main.fxml")), //
            entry("admin", newScene("admin.fxml")) //
    );

    public Helper() throws IOException {
    }

    private Scene newScene(final String path) throws IOException {
        return new Scene(FXMLLoader.load(getClass().getResource("../views/" + path)), WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void redirectTo(final String pane) {
        stage.setScene(this.scenes.get(pane));
    }

    public File selectFile(final String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Music Files", "*.mp3"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4"));
        return fileChooser.showOpenDialog(new Stage());
    }

    public void error(final String title, final String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
}
