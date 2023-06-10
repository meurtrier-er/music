import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingerJDBCDao implements SingerDao {

    @Override
    public List<Singer> findSingers() throws SQLException {
        List<Singer> singerList;

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/music", "postgres", "5432");
             Statement statement = connection.createStatement()) {

            String sql = "SELECT singers.id AS singerId, singers.singername, albums.albumname," +
                    " genres.genreName, songs.songname, songs.songduration " +
                    "FROM singers " +
                    "JOIN albums ON singers.id = albums.singerId " +
                    "JOIN genres ON albums.albumGenreId = genres.id " +
                    "JOIN songs ON albums.id = songs.albumId";
            ResultSet resultSet = statement.executeQuery(sql);

            Map<Integer, Singer> singerMap = new HashMap<>();
            while (resultSet.next()) {
                int singerId = resultSet.getInt("singerId");
                String singerName = resultSet.getString("singername");
                String albumName = resultSet.getString("albumname");
                String genreName = resultSet.getString("genreName");
                String songName = resultSet.getString("songname");
                int songDuration = resultSet.getInt("songduration");

                Singer singer = singerMap.get(singerId);
                if (singer == null) {
                    singer = new Singer(singerName);
                    singerMap.put(singerId, singer);
                }

                Album album = singer.getAlbum(albumName);
                if (album == null) {
                    List<Song> songList = new ArrayList<>();
                    album = new Album(albumName, genreName, songList);
                    singer.addAlbum(album);
                }

                Song song = new Song(songName, Duration.ofSeconds(songDuration));
                album.addSong(song);
            }

            singerList = new ArrayList<>(singerMap.values());
        }

        return singerList;
    }

    @Deprecated
    public List<Singer> findSingers2() throws SQLException {
        List<Singer> singerList;

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/music", "postgres", "5432");
             Statement statement = connection.createStatement()) {

            String sql = "SELECT singers.singername, albums.albumname, genres.genreName, songs.songname, songs.songduration " +
                    "FROM singers " +
                    "JOIN albums ON singers.id = albums.singerId " +
                    "JOIN genres ON albums.albumGenreId = genres.id " +
                    "JOIN songs ON albums.id = songs.albumId";
            ResultSet resultSet = statement.executeQuery(sql);

            Map<String, Singer> singerMap = new HashMap<>();
            while (resultSet.next()) {
                String singerName = resultSet.getString("singername");
                String albumName = resultSet.getString("albumname");
                String genreName = resultSet.getString("genreName");
                String songName = resultSet.getString("songname");
                int songDuration = resultSet.getInt("songduration");

                Singer singer = singerMap.computeIfAbsent(singerName, Singer::new);

                Album album = singer.getAlbum(albumName);
                if (album == null) {
                    List<Song> songList = new ArrayList<>();
                    album = new Album(albumName, genreName, songList);
                    singer.addAlbum(album);
                }

                Song song = new Song(songName, Duration.ofSeconds(songDuration));
                album.addSong(song);
            }

            singerList = new ArrayList<>(singerMap.values());
        }

        return singerList;
    }

    @Deprecated
    public List<Singer> findSingers3() throws SQLException {
        List<Singer> singerList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/music", "postgres", "5432");
            Statement statementSingers = connection.createStatement(); Statement statementAlbums = connection.createStatement();
            Statement statementGenre = connection.createStatement(); Statement statementSongs = connection.createStatement()){
            ResultSet singers = statementSingers.executeQuery("SELECT * FROM singers");
            while (singers.next()){
                ResultSet albums = statementAlbums.executeQuery("SELECT * FROM albums WHERE singerid = " + singers.getInt("id"));
                List<Album> albumList = new ArrayList<>();
                while (albums.next()){
                    ResultSet genreSet = statementGenre.executeQuery("SELECT genrename FROM genres where id = " + albums.getInt("albumgenreid"));
                    genreSet.next();
                    String genre = genreSet.getString("genrename");
                    ResultSet songs = statementSongs.executeQuery("SELECT * FROM songs WHERE albumid = " + albums.getInt("id"));
                    List<Song> songList = new ArrayList<>();
                    while (songs.next()){
                        songList.add(new Song(songs.getString("songname"), Duration.ofSeconds(songs.getInt("songduration"))));
                    }
                    albumList.add(new Album(albums.getString("albumname"), genre, songList));
                }
                singerList.add(new Singer(singers.getString("singername"), albumList));
            }
        }
        return singerList;
    }

    @Override
    public void saveSingers(List<Singer> singers) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/music", "postgres", "5432")) {
            Statement truncateStatement = connection.createStatement();
            truncateStatement.executeUpdate("TRUNCATE TABLE singers_filtered, genres_filtered, albums_filtered, songs_filtered");

            Map<String, Integer> genres = new HashMap<>();
            for (Singer singer : singers) {
                int idSinger = insertSinger(connection, singer);

                List<Album> albums = singer.getSingerAlbums();
                for (Album album : albums) {
                    String albumGenre = album.getAlbumGenre();
                    int genreId = genres.computeIfAbsent(albumGenre, g -> {
                        try {
                            return insertGenre(connection, albumGenre);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    int idAlbum = insertAlbum(connection, album, genreId, idSinger);

                    for (Song song : album.getAlbumSongs()) {
                        insertSong(connection, song, idAlbum);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int insertSinger(Connection connection, Singer singer) throws SQLException {
        String sql = "INSERT INTO singers_filtered (singername) VALUES (?)";
        int singerId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, singer.getSingerName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    singerId = generatedKeys.getInt(1);
                }
            }
        }
        return singerId;
    }

    private int insertGenre(Connection connection, String genre) throws SQLException {
        String sql = "INSERT INTO genres_filtered (genreName) VALUES (?)";
        int genreId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, genre);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    genreId = generatedKeys.getInt(1);
                }
            }
        }
        return genreId;
    }

    private int insertAlbum(Connection connection, Album album, int genreId, int singerId) throws SQLException {
        String sql = "INSERT INTO albums_filtered (albumname, genreId, singerid) VALUES (?, ?, ?)";
        int albumId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, album.getAlbumName());
            statement.setInt(2, genreId);
            statement.setInt(3, singerId);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    albumId = generatedKeys.getInt(1);
                }
            }
        }
        return albumId;
    }

    private void insertSong(Connection connection, Song song, int albumId) throws SQLException {
        String sql = "INSERT INTO songs_filtered (songname, songduration, albumid) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, song.getSongName());
            statement.setLong(2, song.getSongDuration().getSeconds());
            statement.setInt(3, albumId);
            statement.executeUpdate();
        }
    }
    @Deprecated
    public void saveSingers2(List<Singer> singers) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/music", "postgres", "5432")) {
            Statement truncateStatement = connection.createStatement();
            truncateStatement.executeUpdate("TRUNCATE TABLE singers_filtered");
            truncateStatement.executeUpdate("TRUNCATE TABLE genres_filtered");
            truncateStatement.executeUpdate("TRUNCATE TABLE albums_filtered");
            truncateStatement.executeUpdate("TRUNCATE TABLE songs_filtered");
                Map<String, Integer> genres = new HashMap<>();
            for (Singer singer : singers) {
                String sql_singers = "INSERT INTO singers_filtered (singername) VALUES (?)";
                try (PreparedStatement statementSingersPut = connection.prepareStatement(sql_singers, Statement.RETURN_GENERATED_KEYS)) {
                    statementSingersPut.setString(1, singer.getSingerName());
                    int affectedRows = statementSingersPut.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Insert failed, no rows affected.");
                    }
                    try (ResultSet generatedKeys = statementSingersPut.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int idSinger = generatedKeys.getInt(1);
                            List<Album> albums = singer.getSingerAlbums();
                            for (Album album : albums) {
                                String albumGenre = album.getAlbumGenre();
                                if (!genres.containsKey(albumGenre)) {
                                    String sql_genres = "INSERT INTO genres_filtered (genreName) VALUES (?)";
                                    PreparedStatement statementGenrePut = connection.prepareStatement(sql_genres, Statement.RETURN_GENERATED_KEYS);
                                    statementGenrePut.setString(1, albumGenre);
                                    affectedRows = statementGenrePut.executeUpdate();
                                    if (affectedRows == 0) {
                                        throw new SQLException("Insert failed, no rows affected.");
                                    }
                                    try (ResultSet generatedKeysGenre = statementGenrePut.getGeneratedKeys()) {
                                        if (generatedKeysGenre.next()) {
                                            genres.put(albumGenre, generatedKeysGenre.getInt(1));
                                        }
                                    }
                                }
                                String sql_albums = "INSERT INTO albums_filtered (albumname, genreId, singerid) VALUES (?, ?, ?)";
                                PreparedStatement statementAlbumsPut = connection.prepareStatement(sql_albums, Statement.RETURN_GENERATED_KEYS);
                                statementAlbumsPut.setString(1, album.getAlbumName());
                                statementAlbumsPut.setInt(2, genres.get(albumGenre));
                                statementAlbumsPut.setInt(3, idSinger);
                                affectedRows = statementAlbumsPut.executeUpdate();
                                if (affectedRows == 0) {
                                    throw new SQLException("Insert failed, no rows affected.");
                                }
                                try (ResultSet generatedKeysAlbums = statementAlbumsPut.getGeneratedKeys()) {
                                    if (generatedKeysAlbums.next()) {
                                        int idAlbum = generatedKeysAlbums.getInt(1);
                                        List<Song> songs = album.getAlbumSongs();
                                        for (Song song : songs) {
                                            String sql_songs = "INSERT INTO songs_filtered (songname, songduration, albumid) VALUES (?, ?, ?)";
                                            PreparedStatement statementSongsPut = connection.prepareStatement(sql_songs, Statement.RETURN_GENERATED_KEYS);
                                            statementSongsPut.setString(1, song.getSongName());
                                            statementSongsPut.setLong(2, song.getSongDuration().getSeconds());
                                            statementSongsPut.setInt(3, idAlbum);
                                            affectedRows = statementSongsPut.executeUpdate();
                                            if (affectedRows == 0) {
                                                throw new SQLException("Insert failed, no rows affected.");
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new SQLException("Insert failed, no ID obtained.");
                        }
                    }
                }
            }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }