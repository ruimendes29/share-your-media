import controller.*;
import model.MediaCenter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.core.util.FileUtils;

public final class App extends Application {
    private static final String USER_DATA_DIR = System.getenv("SYM_USER_DATA_DIR");

    private Helper helper;
    private MediaCenter model;

    public App() throws IOException {
        this.helper = new Helper();
        this.model = new MediaCenter();
    }

    @Override
    public void init() throws Exception {
        super.init();
        Welcome.init(this.helper, this.model);
        Main.init(this.helper, this.model);
        Login.init(this.helper, this.model);
        Admin.init(this.helper, this.model);
    }

    @SuppressWarnings("checkstyle:FinalParameters")
    public void start(Stage stage) throws Exception {
        FileUtils.mkdir(new File(USER_DATA_DIR), true);
        Helper.init(stage);
        stage.setTitle("Share Your Media");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("images/icon.png")));
        this.helper.redirectTo("welcome");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // deleting cache
        for (File f : new File(USER_DATA_DIR).listFiles()) {
            if (f.isFile()) {
                f.delete();
            }
        }
    }

    @SuppressWarnings("checkstyle:FinalParameters")
    public static void main(String[] args) {
        launch(args);
    }
}
