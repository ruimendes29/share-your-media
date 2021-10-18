package database;

import java.util.ArrayList;
import java.util.List;

public final class PlaylistMediafile implements DataClass<String> {

    private String playlistName;
    private String creatorEmail;
    private String mediafileName;
    private String mediafileArtist;

    public PlaylistMediafile(final String playlistName, final String creatorEmail, final String mediafileName,
            final String mediafileArtist) {
        this.playlistName = playlistName;
        this.creatorEmail = creatorEmail;
        this.mediafileName = mediafileName;
        this.mediafileArtist = mediafileArtist;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public PlaylistMediafile(final List<String> l) {
        this.playlistName = l.get(0);
        this.creatorEmail = l.get(1);
        this.mediafileName = l.get(2);
        this.mediafileArtist = l.get(3);
    }

    public PlaylistMediafile() {
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public String getCreatorEmail() {
        return this.creatorEmail;
    }

    public String getMediafileName() {
        return this.mediafileName;
    }

    public String getMediafileArtist() {
        return this.mediafileArtist;
    }

    public DataClass<String> fromRow(final List<String> l) {
        return new PlaylistMediafile(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.playlistName);
        r.add(this.creatorEmail);
        r.add(this.mediafileName);
        r.add(this.mediafileArtist);
        return r;
    }
}
