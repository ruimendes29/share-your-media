package database;

import java.util.Arrays;
import java.util.List;

public final class UploadersDAO extends DataAcessObject<String, Uploader> {

    private static UploadersDAO singleton = new UploadersDAO();

    private UploadersDAO() {
        super(new Uploader(), "UPLOAD", Arrays.asList("REGULAR_USER_email", "MEDIAFILE_name", "MEDIAFILE_artist"));
    }

    public static UploadersDAO getInstance() {
        return UploadersDAO.singleton;
    }

    public List<Uploader> getUploadersOfMediafile(final String name, final String artist) {
        return super.find("%", name, artist);
    }

    public List<Uploader> get(final String name, final String artist) {
        return super.find("%", name, artist);
    }

    public Uploader put(final Uploader u) {
        return super.put(u, u.getUploader(), u.getName(), u.getArtist());
    }

    public Uploader remove(final String uploader, final String name, final String artist) {
        return super.remove(uploader, name, artist);
    }

    public boolean containsKey(final String uploader, final String name, final String artist) {
        return super.containsKey(uploader, name, artist);
    }

}
