package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        this.user = new RegularUser("nelson@uminho.pt", "Nelson Estevão", "password");
    }

    @Test
    public void validate() {
        Assert.assertFalse(this.user.getPassword().equalsIgnoreCase("password"));
        Assert.assertTrue(this.user.validate("password"));
    }
}
