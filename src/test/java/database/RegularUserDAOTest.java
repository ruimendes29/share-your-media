package database;

import model.MediaFile;
import model.RegularUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class RegularUserDAOTest {

    private RegularUserDAO regularUsers = RegularUserDAO.getInstance();
    private MediaFileDAO mediafiles = MediaFileDAO.getInstance();

    @BeforeClass
    public static void setupBeforeAll() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void get() {
        String email = "pedro@uminho.pt";
        RegularUser pedro = this.regularUsers.get(email);
        Assert.assertEquals(email, pedro.getEmail());
    }

    @Test
    public void getCollection() {
        String email = "pedro@uminho.pt";
        String[] combined = {"Miracle Drug", "Original of the Species", "U2", "U2"};

        RegularUser pedro = this.regularUsers.get(email);

        List<MediaFile> collection = pedro.getCollection(this.mediafiles);

        String[] combinedCompare = {collection.get(0).getName(), collection.get(1).getName(),
                collection.get(0).getArtist(), collection.get(1).getArtist()};

        Assert.assertArrayEquals(combined, combinedCompare);
    }
}
