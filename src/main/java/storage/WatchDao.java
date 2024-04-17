package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface WatchDao {
    public void addWatch(WatchBeen watch) throws SQLException;
    public void updateWatch(WatchBeen watch) throws SQLException;
    public WatchBeen getWatchById(Integer id) throws SQLException;
    public Collection<WatchBeen> getAllWatches() throws SQLException;
    public void deleteWatch(WatchBeen watch) throws SQLException;
    public void deleteAllWatches() throws SQLException;
}
