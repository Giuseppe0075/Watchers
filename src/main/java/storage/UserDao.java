package storage;

import java.sql.SQLException;
import java.util.Collection;


public interface UserDao {
    public void addUser(UserBeen user) throws SQLException;
    public void updateUser(UserBeen user) throws SQLException;
    public UserBeen getUserById(Integer id) throws SQLException;
    public Collection<UserBeen> getAllUsers() throws SQLException;
    public void deleteUser(UserBeen user) throws SQLException;
    public void deleteAllUsers() throws SQLException;
}
