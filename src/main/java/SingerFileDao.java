import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SingerFileDao implements SingerDao {

    @Override
    public List<Singer> findSingers() throws IOException {
        List<String> singersFile = Files.readAllLines(Paths.get("src/main/java/singers.txt"), StandardCharsets.UTF_8);
        List<Singer> singers = new ArrayList<>();
        Singer singer = null;
        Album album = null;
        for (String str : singersFile) {
            String[] typeAndInfo = str.split(": ", 2);
            switch (typeAndInfo[0].trim()) {
                case "Singer" -> {
                    singers.add(singer = new Singer(typeAndInfo[1]));
                }
                case "Album" -> {
                    String[] albumNameAndGenre = str.split(": ")[1].split(", ");
                    Objects.requireNonNull(singer).getSingerAlbums().add(album = new Album(albumNameAndGenre[0].trim(), albumNameAndGenre[1].trim()));
                }
                case "Song" -> {
                    String[] songNameAndDuration = str.split(": ")[1].split(", ");
                    Objects.requireNonNull(album).getAlbumSongs().add(new Song(songNameAndDuration[0].trim(), Duration.ofSeconds(Long.parseLong(songNameAndDuration[1].trim()))));
                }
            }
        }
        return singers;
    }

    @Deprecated
    public List<Singer> findSingersOld() throws IOException {
        String singersFile = String.join(System.lineSeparator(),
                Files.readAllLines(Paths.get("src/main/java/singers.txt"), StandardCharsets.UTF_8));
        List<Singer> singers = new ArrayList<>();
        List<String> singersInfo = Arrays.stream(singersFile.split("Singer: "))
                .filter(s -> s.length() != 0).collect(Collectors.toList());
        for (String singer : singersInfo) {
            String singerName = singer.trim().split("\n")[0].trim();
            singer = singer.substring(singer.indexOf("\n")).trim();
            List<String> albumsInfo = new ArrayList<>(Arrays.asList(singer.split("\n\\s*Album: ")));
            List<Album> albums = new ArrayList<>();
            for (String album : albumsInfo) {
                String albumNameAndGenre = album.trim().split("\n")[0];
                String albumName = albumNameAndGenre.trim().split(",")[0].trim();
                String albumGenre = albumNameAndGenre.trim().split(",")[1].trim();
                album = album.substring(album.indexOf("\n")).trim();
                List<String> songsInfo = new ArrayList<>(Arrays.asList(album.split("\n\\s*Song: ")));
                List<Song> songs = new ArrayList<>();
                for (String song : songsInfo) {
                    songs.add(new Song(song.split(",")[0].trim(), Duration.ofSeconds(Long.parseLong(song.split(",")[1].trim()))));
                }
                albums.add(new Album(albumName, albumGenre, songs));
            }
            singers.add(new Singer(singerName, albums));
        }
        return singers;
    }

    @Override
    public void saveSingers(List<Singer> singers) throws IOException {
        Path path = Paths.get("src/main/java/singers1.txt");
        BufferedWriter writer = Files.newBufferedWriter(path);
        for (Singer singer : singers) {
            writer.write("Singer: " + singer.getSingerName() + "\n");
            for (Album album : singer.getSingerAlbums()) {
                writer.write("    Album: " + album.getAlbumName() + ", " + album.getAlbumGenre() + "\n");
                for (Song song : album.getAlbumSongs()) {
                    writer.write("        Song: " + song.getSongName() + ", " + song.getSongDuration().getSeconds() + "\n");
                }
            }
        }
        writer.close();
    }

    @Deprecated
    public void saveSingers2(List<Singer> singers) throws IOException {
        Path path = Paths.get("src/main/java/singers1.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
        writer.write(singers.stream()
                .map(singer -> {
                    String singerName = "Singer: " + singer.getSingerName() + "\n";
                    List<Album> albums = singer.getSingerAlbums();
                    return singerName +
                            albums.stream()
                                    .map(album -> {
                                        String albumName = "    Album: " + album.getAlbumName() + ", " + album.getAlbumGenre() + "\n";
                                        List<Song> songs = album.getAlbumSongs();
                                        return albumName + songs.stream()
                                                .map(song -> "        Song: " + song.getSongName() + ", " + song.getSongDuration().getSeconds())
                                                .collect(Collectors.joining("\n"));
                                    })
                                    .collect(Collectors.joining("\n"));
                })
                .collect(Collectors.joining("\n"))
        );
        writer.close();
    }

}
