package database;

import model.MediaFile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Collection;

public final class MediaFileDAO extends DataAcessObject<String, MediaFile> {

    private static MediaFileDAO singleton = new MediaFileDAO();

    private CustomCategoriesDAO customCategories;
    private DefaultCategoriesDAO defaultCategories;

    private MediaFileDAO() {
        super(new MediaFile(), "MEDIAFILE", Arrays.asList("name", "artist", "ALBUM_name", "SERIES_name"));
        customCategories = CustomCategoriesDAO.getInstance();
        defaultCategories = DefaultCategoriesDAO.getInstance();
    }

    public static MediaFileDAO getInstance() {
        return MediaFileDAO.singleton;
    }

    public boolean containsKey(final String name, final String artist) {
        return super.containsKey(name, artist);
    }

    public List<MediaFile> findByNameAndArtist(final String name, final String artist) {
        return super.find(name, artist);
    }

    public List<MediaFile> findByName(final String name) {
        return super.find(name);
    }

    public Set<MediaFile> searchByNameOrArtist(final String value) {
        return super.search(value, 0, 1);
    }

    public Set<MediaFile> searchByName(final String value) {
        return super.search(value, 0);
    }

    public Set<MediaFile> searchByArtist(final String value) {
        return super.search(value, 1);
    }

    public MediaFile get(final String name, final String artist) {
        return super.get(name, artist);
    }

    public MediaFile put(final MediaFile value) {
        return super.put(value, value.getName(), value.getArtist());
    }

    public MediaFile remove(final String name, final String artist) {
        return super.remove(name, artist);
    }

    // TODO: Temos de definir este metodo. Temos de o ter para percorrer os Media Files
    public Collection<MediaFile> values() {
        return super.values();
    }
}
