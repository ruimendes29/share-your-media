package database;

import java.util.ArrayList;
import java.util.List;

public final class DefaultCategories implements DataClass<String> {

    private String mediafileName;
    private String mediafileArtist;
    private String category1;
    private String category2;
    private String category3;

    public DefaultCategories(final String name, final String artist, final String category1, final String category2,
            final String category3) {
        this.mediafileName = name;
        this.mediafileArtist = artist;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public DefaultCategories(final List<String> l) {
        this.mediafileName = l.get(0);
        this.mediafileArtist = l.get(1);
        this.category1 = l.get(2);
        this.category2 = l.get(3);
        this.category3 = l.get(4);
    }

    public DefaultCategories() {
    }

    public String getMediafileName() {
        return this.mediafileName;
    }

    public String getMediafileArtist() {
        return this.mediafileArtist;
    }

    public List<String> getCategories() {
        List<String> r = new ArrayList<>();
        r.add(this.category1);
        r.add(this.category2);
        r.add(this.category3);
        return r;
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new DefaultCategories(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.mediafileName);
        r.add(this.mediafileArtist);
        r.add(this.category1);
        r.add(this.category2);
        r.add(this.category3);
        return r;
    }
}
