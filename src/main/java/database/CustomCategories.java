package database;

import java.util.ArrayList;
import java.util.List;

public final class CustomCategories implements DataClass<String> {

    private String username;
    private String mediafileName;
    private String mediafileArtist;
    private String category1;
    private String category2;
    private String category3;

    public CustomCategories(final String username, final String mediafileName, final String mediafileArtist,
            final String category1, final String category2, final String category3) {
        this.username = username;
        this.mediafileName = mediafileName;
        this.mediafileArtist = mediafileArtist;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public CustomCategories(final List<String> l) {
        this.username = l.get(0);
        this.mediafileName = l.get(1);
        this.mediafileArtist = l.get(2);
        this.category1 = l.get(3);
        this.category2 = l.get(4);
        this.category3 = l.get(5);
    }

    public CustomCategories() {
    }

    public String getUsername() {
        return username;
    }

    public String getMediafileName() {
        return mediafileName;
    }

    public String getMediafileArtist() {
        return mediafileArtist;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }

    public List<String> getCategories() {
        List<String> r = new ArrayList<>();
        r.add(this.category1);
        r.add(this.category2);
        r.add(this.category3);
        return r;
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new CustomCategories(l);
    }

    public List<String> toRow() {
        List<String> row = new ArrayList<>();
        row.add(this.username);
        row.add(this.mediafileName);
        row.add(this.mediafileArtist);
        row.add(this.category1);
        row.add(this.category2);
        row.add(this.category3);
        return row;
    }
}
