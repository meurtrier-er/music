import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
//        Song song1 = new Song("Song_1", Duration.ofMinutes(17));
//        Song song2 = new Song("Song_2", Duration.ofMinutes(27));
//        Song song3 = new Song("Song_3", Duration.ofMinutes(37));
//        Song song4 = new Song("Song_4", Duration.ofMinutes(47));
//        Song song5 = new Song("Song_5", Duration.ofMinutes(57));
//
//        Album album1 = new Album("Album_1", "rap", Arrays.asList(song1, song2, song3));
//        Album album2 = new Album("Album_2", "hip-hop", Arrays.asList(song3, song4, song5));
//        Album album3 = new Album("Album_3", "rap", Arrays.asList(song1, song2, song5));
//        Album album4 = new Album("Album_4", "rap", Arrays.asList(song2, song3, song5));
//
//        Singer singer1 = new Singer("Singer_1", Arrays.asList(album1, album2));
//        Singer singer2 = new Singer("Singer_2", Collections.singletonList(album2));
//        Singer singer3 = new Singer("Singer_3", Arrays.asList(album1, album3, album4));
//        Singer singer4 = new Singer("Singer_4", Arrays.asList(album2, album4));
//        Singer singer5 = new Singer("Singer_5", Arrays.asList(album1, album4));

//        System.out.println("Rap singers.txt");
//        SingerService.filterByGenre(Arrays.asList(singer1, singer2, singer3, singer4, singer5), "rap").forEach(s -> System.out.println(s.toString()));
//        System.out.println("Hip-hop singers.txt");
//        SingerService.filterByGenre(Arrays.asList(singer1, singer2, singer3, singer4, singer5), "hip-hop").forEach(s -> System.out.println(s.toString()));
//        System.out.println("Min 3 albums");
//        SingerService.filterByMinAlbumsCount(Arrays.asList(singer1, singer2, singer3, singer4, singer5), 3).forEach(s -> System.out.println(s.toString()));
//        System.out.println("Min 2 albums");
//        SingerService.filterByMinAlbumsCount(Arrays.asList(singer1, singer2, singer3, singer4, singer5), 2).forEach(s -> System.out.println(s.toString()));

//        SingerDao singerDao1 = SingerFileDaoFactory.INSTANCE.createSingerDao();
//        SingerDao singerDao2 = SingerJDBCDaoFactory.INSTANCE.createSingerDao();

//        singerDao1.findSingers().forEach(System.out::println);
//        SingerFileDaoFactory.INSTANCE.createSingerDao();
//        SingerFileDao.saveSingers(SingerFileDaoFactory.INSTANCE.createSingerDao().findSingers());
//        SingerJDBCDaoFactory.INSTANCE.createSingerDao().findSingers().forEach(System.out::println);
        SingerJDBCDaoFactory.INSTANCE.createSingerDao().saveSingers(SingerJDBCDaoFactory.INSTANCE.createSingerDao().findSingers());
//        SingerJDBCDaoFactory.INSTANCE.createSingerDao().findSingers().forEach(System.out::println);
    }
}