package database;

import model.RegularUser;

import java.util.Arrays;
import java.util.Set;

public final class RegularUserDAO extends DataAcessObject<String, RegularUser> {

    private static RegularUserDAO singleton = new RegularUserDAO();
    private UserCollectionDAO collection;

    private RegularUserDAO() {
        super(new RegularUser(), "REGULAR_USER", Arrays.asList("email", "name", "password", "salt"));
        this.collection = UserCollectionDAO.getInstance();
    }

    public static RegularUserDAO getInstance() {
        return RegularUserDAO.singleton;
    }

    public Set<RegularUser> searchByNameOrEmail(final String value) {
        return super.search(value, 1, 0);
    }

    public Set<RegularUser> searchByEmail(final String value) {
        return super.search(value, 0);
    }

    public Set<RegularUser> searchByName(final String value) {
        return super.search(value, 1);
    }

    public RegularUser get(final String key) {
        return super.get(key);
    }

    public RegularUser put(final RegularUser value) {
        return super.put(value, value.getEmail());
    }

    public RegularUser remove(final String key) {
        return super.remove(key);
    }
}
