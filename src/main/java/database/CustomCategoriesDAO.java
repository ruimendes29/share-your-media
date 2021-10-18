package database;

import java.util.Arrays;

public final class CustomCategoriesDAO extends DataAcessObject<String, CustomCategories> {

    private static CustomCategoriesDAO singleton = new CustomCategoriesDAO();

    private CustomCategoriesDAO() {
        super(new CustomCategories(), "CUSTOM_CATEGORY", Arrays.asList("REGULAR_USER_email", "MEDIAFILE_name",
                "MEDIAFILE_artist", "category1", "category2", "category3"));
    }

    public static CustomCategoriesDAO getInstance() {
        return CustomCategoriesDAO.singleton;
    }

    public CustomCategories get(final String username, final String mediafileName, final String mediafileArtist) {
        return super.get(username, mediafileName, mediafileArtist);
    }

    public CustomCategories put(final CustomCategories ct) {
        return super.put(ct, ct.getUsername(), ct.getMediafileName(), ct.getMediafileArtist());
    }

    public CustomCategories remove(final String username, final String mediafileName, final String mediafileArtist) {
        return super.remove(username, mediafileName, mediafileArtist);
    }

}
