package database;

import model.AdminUser;
import model.MediaFile;
import model.RegularUser;
import org.junit.*;
import util.Passwords;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataAcessObjectsTests {
    private static AdminUserDAO admins = AdminUserDAO.getInstance();
    private static RegularUserDAO users = RegularUserDAO.getInstance();
    private static MediaFileDAO mediafiles = MediaFileDAO.getInstance();
    private static List<AdminUser> adminsList = new ArrayList<>();
    private static List<RegularUser> usersList = new ArrayList<>();

    @BeforeClass
    public static void setUpBeforeAll() {
        adminsList.add(admins.get("hugo@uminho.pt"));
        adminsList.add(admins.get("nelson@uminho.pt"));
        adminsList.add(admins.get("pedro@uminho.pt"));
        adminsList.add(admins.get("rui@uminho.pt"));
        adminsList.add(admins.get("ricardo@uminho.pt"));

        usersList.add(users.get("hugo@uminho.pt"));
        usersList.add(users.get("nelson@uminho.pt"));
        usersList.add(users.get("pedro@uminho.pt"));
        usersList.add(users.get("rui@uminho.pt"));
        usersList.add(users.get("ricardo@uminho.pt"));

        for (int i = 0; i < adminsList.size(); i++) {
            System.out.println(adminsList.get(i).toString());
        }

        for (int i = 0; i < usersList.size(); i++) {
            System.out.println(usersList.get(i).toString());
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterAll() {
    }

    @Test
    public void getTest() {
        String email = "nelson@uminho.pt";
        String name = "Nelson Estevão";
        String password = "shareyourmedia";
        String salt = "vsoptxmt";
        RegularUser user = users.get(email);
        Assert.assertEquals(user.getEmail(), email);
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getSalt(), salt);
        Assert.assertTrue(Passwords.verify(password, user.getPassword(), user.getSalt()));
    }

    @Test
    public void putTest() {
        String email = "nelson@uminho.pt";
        String name = "Nelson Estevão";
        String password = "shareyourmedia";
        String salt = "vsoptxmt";
        RegularUser user1 = new RegularUser(email, name, Passwords.generate(password, salt), salt);
        RegularUser user2 = users.get(email);
        users.put(user2);
        Assert.assertEquals(user1, user2);
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(users.size(), 5);
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(users.containsKey("pedro@uminho.pt"));
    }

    @Test
    public void emptyTest() {
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void searchTest() {
        Set<RegularUser> usersComN = users.searchByName("N");
        for (RegularUser u : usersComN) {
            System.out.println(u.getName());
        }
        Assert.assertEquals(2, usersComN.size());
    }

    @Test
    public void removeTest() {
        String email = "nelson@uminho.pt";
        RegularUser user = users.get(email);
        users.remove(email);
        Assert.assertEquals(users.size(), 4);
        users.put(user);
        Assert.assertEquals(users.size(), 5);
    }
}
