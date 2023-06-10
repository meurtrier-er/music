import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Album {
    private String albumName;
    private String albumGenre;
    private List<Song> albumSongs = new ArrayList<>();

    public Album(String albumName, String albumGenre, List<Song> albumSongs) {
        this.albumName = albumName;
        this.albumGenre = albumGenre;
        this.albumSongs = albumSongs;
    }

    public Album(String albumName, String albumGenre) {
        this.albumName = albumName;
        this.albumGenre = albumGenre;
    }

    public Album withSongs(Song... songs) {
        this.albumSongs.addAll(Arrays.asList(songs));
        return this;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }

    public List<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(List<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }

    public void addSong(Song albumSong) {
        albumSongs.add(albumSong);
    }

    public String getName() {
        return albumName;
    }
    @Override
    public String toString() {
        return "Album{" +
                "albumName='" + albumName + '\'' +
                ", albumGenre='" + albumGenre + '\'' +
                ", albumSongs=" + albumSongs +
                '}';
    }


}
