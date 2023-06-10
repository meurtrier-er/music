import java.util.List;
import java.util.stream.Collectors;

public class SingerService {
    public static List<Singer> filterByGenre(List<Singer> singers, String genre){
        return singers.stream()
                .filter(singer -> singer.getSingerAlbums().stream()
                            .anyMatch(s -> s.getAlbumGenre().equals(genre)))
                .collect(Collectors.toList());
    }

    public static List<Singer> filterByMinAlbumsCount(List<Singer> singers, int minAlbums){
        return singers.stream()
                .filter(singer -> singer.getSingerAlbums().size() >= minAlbums)
                .collect(Collectors.toList());
    }

}
