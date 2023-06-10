import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Singer {
    private String singerName;
    private List<Album> singerAlbums = new ArrayList<>();

    public Singer(String singerName, List<Album> singerAlbums) {
        this.singerName = singerName;
        this.singerAlbums = singerAlbums;
    }

    public Singer(String singerName) {
        this.singerName = singerName;
    }

    public Singer withAlbums(Album... albums) {
        this.singerAlbums.addAll(Arrays.asList(albums));
        return this;
    }
    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public List<Album> getSingerAlbums() {
        return singerAlbums;
    }

    public void setSingerAlbums(List<Album> singerAlbums) {
        this.singerAlbums = singerAlbums;
    }

    public void addAlbum(Album singerAlbum) {
        singerAlbums.add(singerAlbum);
    }

    public Album getAlbum(String albumName) {
        for (Album album : singerAlbums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Singer: " + singerName +
                ", number of singer albums = " + singerAlbums.size() +
                "{ " + singerAlbums.toString() + "}";
    }
}
