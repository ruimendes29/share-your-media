package model;

public final class MediaTableRow implements Comparable<MediaTableRow> {
    private String name;
    private String artist;
    private String album;
    private String series;
    private String category1;
    private String category2;
    private String category3;

    public MediaTableRow(final String name, final String artist, final String album, final String series,
            final String category1, final String category2, final String category3) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.series = series;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(final String album) {
        this.album = album;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(final String series) {
        this.series = series;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(final String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(final String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(final String category3) {
        this.category3 = category3;
    }

    @Override
    public int compareTo(final MediaTableRow o) {
        return (this.getArtist() + this.getName()).compareTo(o.getArtist() + o.getName());
    }
}
