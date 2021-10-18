package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import exceptions.LackOfPermissions;

import model.MediaCenter;
import model.MediaTableRow;

import javax.imageio.ImageIO;

public final class Main {
    private static Helper helper;
    private static MediaCenter model;

    private static final String USER_DATA_DIR = System.getenv("SYM_USER_DATA_DIR");

    public static void init(final Helper hlpr, final MediaCenter mdl) {
        Main.helper = hlpr;
        Main.model = mdl;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private TextField nameUploadTextField;

    @FXML
    private TextField artistUploadTextField;

    @FXML
    private TextField albumUploadTextField;

    @FXML
    private TextField seriesUploadTextField;

    @FXML
    private Button uploadButton;

    @FXML
    private Button addFriendsButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button playButton;

    @FXML
    private TableView<MediaTableRow> musicTable;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView videoPlayer;

    @FXML
    private ImageView imageCoverAlbum;

    private String playingSong;

    @FXML
    private ProgressBar progressBar;

    @FXML
    void addFriends(final ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feature in development");
        alert.setHeaderText("Can't add friends yet");
        alert.showAndWait();
    }

    @FXML
    void logout(final ActionEvent event) {
        this.reset();
        model.logout();
        helper.redirectTo("welcome");
    }

    @FXML
    public void changeCellAlbum(final CellEditEvent cellEditEvent) {
        String oldValue = cellEditEvent.getOldValue().toString();
        MediaTableRow row = musicTable.getSelectionModel().getSelectedItem();
        row.setAlbum(cellEditEvent.getNewValue().toString());

        try {
            model.addMedia(row);
        } catch (LackOfPermissions e) {
            row.setAlbum(oldValue);
            musicTable.refresh();
            helper.error("Authentication Error", e.getMessage());
        }
    }

    @FXML
    public void changeCellSeries(final CellEditEvent cellEditEvent) {
        String oldValue = cellEditEvent.getOldValue().toString();
        MediaTableRow row = musicTable.getSelectionModel().getSelectedItem();
        row.setSeries(cellEditEvent.getNewValue().toString());

        try {
            model.addMedia(row);
        } catch (LackOfPermissions e) {
            row.setSeries(oldValue);
            musicTable.refresh();
            helper.error("Authentication Error", e.getMessage());
        }
    }

    @FXML
    public void changeCellCategory1(final CellEditEvent cellEditEvent) {
        String oldValue = cellEditEvent.getOldValue().toString();
        MediaTableRow row = musicTable.getSelectionModel().getSelectedItem();
        row.setCategory1(cellEditEvent.getNewValue().toString());

        try {
            model.addMedia(row);
        } catch (LackOfPermissions e) {
            row.setCategory1(oldValue);
            musicTable.refresh();
            helper.error("Authentication Error", e.getMessage());
        }
    }

    @FXML
    public void changeCellCategory2(final CellEditEvent cellEditEvent) {
        String oldValue = cellEditEvent.getOldValue().toString();
        MediaTableRow row = musicTable.getSelectionModel().getSelectedItem();
        row.setCategory2(cellEditEvent.getNewValue().toString());

        try {
            model.addMedia(row);
        } catch (LackOfPermissions e) {
            row.setCategory2(oldValue);
            musicTable.refresh();
            helper.error("Authentication Error", e.getMessage());
        }
    }

    @FXML
    public void changeCellCategory3(final CellEditEvent cellEditEvent) {
        String oldValue = cellEditEvent.getOldValue().toString();
        MediaTableRow row = musicTable.getSelectionModel().getSelectedItem();
        row.setCategory3(cellEditEvent.getNewValue().toString());

        try {
            model.addMedia(row);
        } catch (LackOfPermissions e) {
            row.setCategory3(oldValue);
            musicTable.refresh();
            helper.error("Authentication Error", e.getMessage());
        }
    }

    @FXML
    void search() {
        Set<MediaTableRow> rows = model.searchMediaByNameOrArtist(this.searchBar.getText());
        musicTable.getItems().clear();
        musicTable.getItems().addAll(rows);
        this.searchBar.clear();
        musicTable.setEditable(true);
    }

    void updateBar(final double currentTime) {
        this.progressBar.progressProperty().setValue(currentTime / mediaPlayer.getTotalDuration().toSeconds());
    }

    @FXML
    void play(final ActionEvent event) {

        MediaTableRow selectedSong = musicTable.getSelectionModel().getSelectedItem();

        if (this.mediaPlayer == null && selectedSong == null) {
            return;
        }

        if (this.mediaPlayer != null) {

            if (selectedSong.getName().equals(playingSong)) {
                if (this.mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                    this.mediaPlayer.pause();
                    this.playButton.setText("▸");
                } else {
                    this.mediaPlayer.play();
                    this.playButton.setText("II");
                }
            } else {
                this.mediaPlayer.stop();
                String name = selectedSong.getName();
                String artist = selectedSong.getArtist();
                try {
                    String fileLocation = model.downloadMedia(name, artist);
                    this.mediaPlayer = new MediaPlayer(new Media(new File(fileLocation).toURI().toString()));
                    mediaPlayer.currentTimeProperty()
                            .addListener((observable, oldValue, newValue) -> updateBar(newValue.toSeconds()));
                    this.mediaPlayer.setAutoPlay(true);
                    this.videoPlayer.setMediaPlayer(mediaPlayer);
                    this.setAlbumCover(fileLocation);
                    this.playingSong = name;
                    this.playButton.setText("II");
                } catch (IOException e) {
                    e.printStackTrace();
                    helper.error("Download error", e.getMessage());
                }
            }
        } else {
            String name = selectedSong.getName();
            String artist = selectedSong.getArtist();
            try {
                String fileLocation = model.downloadMedia(name, artist);
                this.mediaPlayer = new MediaPlayer(new Media(new File(fileLocation).toURI().toString()));
                mediaPlayer.currentTimeProperty()
                        .addListener((observable, oldValue, newValue) -> updateBar(newValue.toSeconds()));
                this.mediaPlayer.setAutoPlay(true);
                this.videoPlayer.setMediaPlayer(mediaPlayer);
                this.setAlbumCover(fileLocation);
                this.playingSong = name;
                this.playButton.setText("II");
            } catch (IOException e) {
                e.printStackTrace();
                helper.error("Download error", e.getMessage());
            }
        }

        this.mediaPlayer.setOnEndOfMedia(() -> {
            this.playingSong = "";
            this.playButton.setText("▸");
        });
    }

    void setAlbumCover(final String filename) {
        try {
            Mp3File song = new Mp3File(filename);
            if (song.hasId3v2Tag()) {
                ID3v2 id3v2tag = song.getId3v2Tag();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(id3v2tag.getAlbumImage()));
                File outputfile = new File(USER_DATA_DIR + "cover.jpg");
                ImageIO.write(img, "jpg", outputfile);
                this.imageCoverAlbum.setImage(new Image(outputfile.toURI().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            this.imageCoverAlbum.setImage(null);
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void upload(final ActionEvent event) {
        try {
            model.uploadMedia(nameUploadTextField.getText(), artistUploadTextField.getText(),
                    albumUploadTextField.getText(), seriesUploadTextField.getText(),
                    helper.selectFile("Choose media to upload"));
        } catch (IOException | LackOfPermissions e) {
            e.printStackTrace();
            helper.error("Upload error", e.getMessage());
        }
    }

    void reset() {
        this.playingSong = "";
        this.playButton.setText("▸");
        if (this.mediaPlayer != null)
            this.mediaPlayer.stop();
        this.videoPlayer.setMediaPlayer(null);
        this.imageCoverAlbum.setImage(null);
        musicTable.getItems().clear();
    }
}
