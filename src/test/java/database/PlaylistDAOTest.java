package database;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaylistDAOTest {

    private PlaylistDAO playlists = PlaylistDAO.getInstance();
    private PlaylistMediafilesDAO mediafiles = PlaylistMediafilesDAO.getInstance();

    @BeforeClass
    public static void setupBeforeAll() {
    }

    @Test
    public void getPlaylistDAO() {
        String[] playlistInfo = {"Tastebreakers", "nelson@uminho.pt"};
        PlaylistLine pl = this.playlists.get(playlistInfo[0], playlistInfo[1]);
        String[] toCompare = {pl.getName(), pl.getCreator()};
        Assert.assertArrayEquals(playlistInfo, toCompare);
    }

    @Test
    public void getPlaylistMediafilesDAO() {
        String[] playlistInfo = {"Tastebreakers", "nelson@uminho.pt"};
        List<String> playlist = Arrays.asList("Born To Die", "Lana Del Rey");
        List<PlaylistMediafile> m = this.mediafiles.get(playlistInfo[0], playlistInfo[1]);
        List<String> mediafiles = new ArrayList<>();
        List<String> authors = new ArrayList<>();
        for (PlaylistMediafile pmf : m) {
            mediafiles.add(pmf.getMediafileName());
            authors.add(pmf.getMediafileArtist());
        }
        for (String c : authors)
            mediafiles.add(c);
        String[] toCompare = mediafiles.stream().toArray(String[]::new);
        String[] original = playlist.stream().toArray(String[]::new);
        Assert.assertArrayEquals(original, toCompare);
    }
}
