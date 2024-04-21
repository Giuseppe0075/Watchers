package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface BrandDao{
    public void addBrand(BrandBeen brand) throws SQLException;
    public void updateBrand(BrandBeen brand) throws SQLException;
    public BrandBeen getBrandById(Integer id) throws SQLException;
    public Collection<BrandBeen> getAllBrands() throws SQLException;
    public void deleteBrand(BrandBeen brand) throws SQLException;
}
