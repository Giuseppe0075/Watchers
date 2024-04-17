package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface AdminDao{
    public void addAdmin(AdminBeen admin) throws SQLException;
    public void updateAdmin(AdminBeen admin) throws SQLException;
    public AdminBeen getAdminById(Integer id) throws SQLException;
    public Collection<AdminBeen> getAllAdmins() throws SQLException;
    public void deleteAdmin(AdminBeen admin) throws SQLException;
    public void deleteAllAdmins() throws SQLException;

}
