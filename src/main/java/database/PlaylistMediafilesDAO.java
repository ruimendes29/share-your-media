package database;

import java.util.Arrays;
import java.util.List;

public final class PlaylistMediafilesDAO extends DataAcessObject<String, PlaylistMediafile> {

    private static PlaylistMediafilesDAO singleton = new PlaylistMediafilesDAO();

    public PlaylistMediafilesDAO() {
        super(new PlaylistMediafile(), "PLAYLIST_has_MEDIAFILE",
                Arrays.asList("PLAYLIST_name", "PLAYLIST_creator", "MEDIAFILE_name", "MEDIAFILE_artist"));
    }

    public static PlaylistMediafilesDAO getInstance() {
        return PlaylistMediafilesDAO.singleton;
    }

    public List<PlaylistMediafile> get(final String playlistName, final String creatorEmail) {
        return super.find(playlistName, creatorEmail);
    }

    public PlaylistMediafile put(final PlaylistMediafile p) {
        return super.put(p, p.getPlaylistName(), p.getCreatorEmail(), p.getMediafileName(), p.getMediafileArtist());
    }

}
