import java.time.Duration;

public class Song {
    private String songName;
    private Duration songDuration;

    public Song(String songName, Duration songDuration) {
        this.songName = songName;
        this.songDuration = songDuration;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Duration getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Duration songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", songDuration=" + songDuration +
                '}';
    }
}
