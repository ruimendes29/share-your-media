package database;

import java.util.ArrayList;
import java.util.List;

public final class PlaylistLine implements DataClass<String> {

    private String name;
    private String creator;
    private String criteria;

    public PlaylistLine(final String name, final String creator, final String criteria) {
        this.name = name;
        this.creator = creator;
        this.criteria = criteria;
    }

    public PlaylistLine(final List<String> l) {
        this.name = l.get(0);
        this.creator = l.get(1);
        this.criteria = l.get(2);
    }

    public PlaylistLine() {
    }

    public String getName() {
        return this.name;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getCriteria() {
        return this.criteria;
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new PlaylistLine(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.name);
        r.add(this.creator);
        r.add(this.criteria);
        return r;
    }
}
