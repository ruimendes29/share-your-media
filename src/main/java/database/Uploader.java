package database;

import java.util.ArrayList;
import java.util.List;

public final class Uploader implements DataClass<String> {

    private String uploader;
    private String mediafileName;
    private String mediafileArtist;

    public Uploader(final String uploader, final String mediafileName, final String mediafileArtist) {
        this.uploader = uploader;
        this.mediafileName = mediafileName;
        this.mediafileArtist = mediafileArtist;
    }

    public Uploader(final List<String> l) {
        this.uploader = l.get(0);
        this.mediafileName = l.get(1);
        this.mediafileArtist = l.get(2);
    }

    public Uploader() {
    }

    public String getUploader() {
        return this.uploader;
    }

    public String getName() {
        return this.mediafileName;
    }

    public String getArtist() {
        return this.mediafileArtist;
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new Uploader(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.uploader);
        r.add(this.mediafileName);
        r.add(this.mediafileArtist);
        return r;
    }
}
