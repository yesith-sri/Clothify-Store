package icet.edu.drm.dao.Custom.impl;

import icet.edu.drm.dao.Custom.ProductDao;
import icet.edu.drm.entity.ProductEntity;
import javafx.collections.ObservableList;

public class ProductDaoImpl implements ProductDao {
    @Override
    public ProductEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<ProductEntity> searchAll() {
        return null;
    }

    @Override
    public boolean insert(ProductEntity productEntity) {
        return false;
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
