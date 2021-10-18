package database;

import model.MediaFile;

import java.util.ArrayList;
import java.util.List;

public final class UserCollection implements DataClass<String> {

    private String username;
    private String mediafileName;
    private String mediafileArtist;

    public UserCollection(final String email, final String name, final String artist) {
        this.username = username;
        this.mediafileName = name;
        this.mediafileArtist = artist;
    }

    public UserCollection(final List<String> l) {
        this.username = l.get(0);
        this.mediafileName = l.get(1);
        this.mediafileArtist = l.get(2);
    }

    public UserCollection() {
    }

    public MediaFile getMediaFile(final MediaFileDAO mf) {
        return mf.get(this.mediafileName, this.mediafileArtist);
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new UserCollection(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.username);
        r.add(this.mediafileName);
        r.add(this.mediafileArtist);
        return r;
    }
}
