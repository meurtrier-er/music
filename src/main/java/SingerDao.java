import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SingerDao {
    public List<Singer> findSingers() throws InterruptedException, IOException, SQLException;

    public void saveSingers(List<Singer> singers) throws InterruptedException, IOException, SQLException;
}
