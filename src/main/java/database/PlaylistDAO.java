package database;

import java.util.Arrays;

public final class PlaylistDAO extends DataAcessObject<String, PlaylistLine> {

    private static PlaylistDAO playlist = new PlaylistDAO();
    private PlaylistMediafilesDAO playlistMediafiles;

    public PlaylistDAO() {
        super(new PlaylistLine(), "PLAYLIST", Arrays.asList("name", "creator", "criteria"));
        this.playlistMediafiles = PlaylistMediafilesDAO.getInstance();
    }

    public static PlaylistDAO getInstance() {
        return PlaylistDAO.playlist;
    }

    public PlaylistLine get(final String name, final String creator) {
        return super.get(name, creator);
    }

    public PlaylistLine put(final PlaylistLine p) {
        return super.put(p, p.getName(), p.getCreator());
    }

}
