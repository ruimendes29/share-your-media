package database;

import model.MediaFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MediaFileDAOTest {

    private MediaFileDAO mediafiles = MediaFileDAO.getInstance();

    @BeforeClass
    public static void setupBeforeAll() {
    }

    @After
    public void tearDown() {
        String email = "nelson@uminho.pt";
        String[] mediafileInfo = {"Video Games - Remastered", "Lana Del Rey"};
        String[] categories = {"Blues", "Pop", (java.lang.String) null};
        MediaFile m = this.mediafiles.get(mediafileInfo[0], mediafileInfo[1]);
        m.setCustomCategories(email, Arrays.stream(categories).collect(Collectors.toList()));
        m.removeUploader("pedro@uminho.pt");
        this.mediafiles.remove("Miracle Drug", "U2");
    }

    @Test
    public void get() {
        String[] mediafileInfo = {"Born To Die", "Lana Del Rey"};
        MediaFile mf = this.mediafiles.get(mediafileInfo[0], mediafileInfo[1]);
        String[] toCompare = {mf.getName(), mf.getArtist()};
        Assert.assertArrayEquals(mediafileInfo, toCompare);
    }

    @Test
    public void put() {
        String[] mediafileInfo = {"Miracle Drug", "U2", "How To Dismantle an Atomic Bomb", ""};
        MediaFile mf = new MediaFile(mediafileInfo[0], mediafileInfo[1], mediafileInfo[2], mediafileInfo[3]);
        this.mediafiles.put(mf, mediafileInfo[0], mediafileInfo[1]);
        mf = this.mediafiles.get(mediafileInfo[0], mediafileInfo[1]);
        String[] toCompare = {mf.getName(), mf.getArtist(), mf.getAlbum(), mf.getSeries()};
        Assert.assertArrayEquals(mediafileInfo, toCompare);
    }

    @Test
    public void values() {
    }

    @Test
    public void getUserCategories() {
        String email = "nelson@uminho.pt";
        String[] mediaFileInfo = {"Video Games - Remastered", "Lana Del Rey"};
        MediaFile m = this.mediafiles.get(mediaFileInfo[0], mediaFileInfo[1]);
        List<String> queriedCategories = m.getCustomCategories(email);
        List<String> categories = new ArrayList<>(Arrays.asList("Blues", "Pop", null));
        Assert.assertArrayEquals(Arrays.copyOfRange((categories.stream().toArray(String[]::new)), 0, 1),
                Arrays.copyOfRange((queriedCategories.stream().toArray(String[]::new)), 0, 1));
    }

    @Test
    public void updateUserCategories() {
        String email = "nelson@uminho.pt";
        String[] songInfo = {"Video Games - Remastered", "Lana Del Rey"};
        List<String> categories = new ArrayList<>(Arrays.asList("Pop Rock", "Dubstep", "Pimba"));
        MediaFile m = this.mediafiles.get(songInfo[0], songInfo[1]);
        m.setCustomCategories(email, categories);
        List<String> queriedCategories = m.getCustomCategories(email);
        Assert.assertArrayEquals(categories.stream().toArray(String[]::new),
                queriedCategories.stream().toArray(String[]::new));
    }

    @Test
    public void addMediafileUploader() {
        String email = "pedro@uminho.pt";
        String[] mediafileInfo = {"Video Games - Remastered", "Lana Del Rey"};
        MediaFile m = this.mediafiles.get(mediafileInfo[0], mediafileInfo[1]);
        m.addUploader(email);
        String[] uploaders = {"nelson@uminho.pt", "pedro@uminho.pt"};
        String[] queriedUploaders = m.getUploaders().stream().toArray(String[]::new);
        Assert.assertArrayEquals(uploaders, queriedUploaders);
    }

}
