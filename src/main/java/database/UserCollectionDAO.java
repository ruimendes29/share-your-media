package database;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class UserCollectionDAO extends DataAcessObject<String, UserCollection> {

    private static UserCollectionDAO singleton = new UserCollectionDAO();

    private UserCollectionDAO() {
        super(new UserCollection(), "UPLOAD",
                Arrays.asList("REGULAR_USER_email", "MEDIAFILE_name", "MEDIAFILE_artist"));
    }

    public static UserCollectionDAO getInstance() {
        return UserCollectionDAO.singleton;
    }

    public List<UserCollection> get(final String email) {
        return super.search(email, 0).stream().collect(Collectors.toList());
    }

    public UserCollection put(final UserCollection value, final String email, final String name, final String artist) {
        return super.put(value, email, name, artist);
    }

    public UserCollection remove(final String email, final String name, final String artist) {
        return super.remove(email, name, artist);
    }

    public boolean containsKey(final String email, final String name, final String artist) {
        return super.containsKey(email, name, artist);
    }

}
