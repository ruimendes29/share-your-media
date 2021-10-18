package database;

import java.util.Arrays;

public final class DefaultCategoriesDAO extends DataAcessObject<String, DefaultCategories> {

    private static DefaultCategoriesDAO singleton = new DefaultCategoriesDAO();

    private DefaultCategoriesDAO() {
        super(new DefaultCategories(), "DEFAULT_CATEGORIES",
                Arrays.asList("MEDIAFILE_name", "MEDIAFILE_artist", "category1", "category2", "category3"));
    }

    public static DefaultCategoriesDAO getInstance() {
        return DefaultCategoriesDAO.singleton;
    }

    public DefaultCategories get(final String mediafileName, final String mediafileArtist) {
        return super.get(mediafileName, mediafileArtist);
    }

    public DefaultCategories put(final DefaultCategories dc) {
        return super.put(dc, dc.getMediafileName(), dc.getMediafileArtist());
    }

    public DefaultCategories remove(final String mediafileName, final String mediafileArtist) {
        return super.remove(mediafileName, mediafileArtist);
    }

}
