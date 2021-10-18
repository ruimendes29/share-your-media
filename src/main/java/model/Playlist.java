package model;

import database.PlaylistDAO;
import database.PlaylistMediafile;
import database.PlaylistMediafilesDAO;

public final class Playlist {

    private String name;
    private String creator;
    private String criteria;
    private PlaylistDAO playlists;
    private PlaylistMediafilesDAO mediafiles;

    /**
     * Constructor
     *
     * @param name Playlist's name.
     * @param creator Playlist's creator email.
     * @param criteria Playlist's criteria.
     */
    public Playlist(final String name, final String creator, final String criteria) {
        this.name = name;
        this.creator = creator;
        this.criteria = criteria;
        this.mediafiles = PlaylistMediafilesDAO.getInstance();
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

    public void addMediaFile(final MediaFile m) {
        PlaylistMediafile p = new PlaylistMediafile(this.name, this.creator, m.getName(), m.getArtist());
        this.mediafiles.put(p, p.getPlaylistName(), p.getCreatorEmail(), p.getMediafileName(), p.getMediafileArtist());
    }
}
