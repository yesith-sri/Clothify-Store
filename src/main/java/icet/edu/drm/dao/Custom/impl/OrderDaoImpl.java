package icet.edu.drm.dao.Custom.impl;

import icet.edu.drm.dao.Custom.OrderDao;
import icet.edu.drm.entity.OrderEntity;
import javafx.collections.ObservableList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public OrderEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<OrderEntity> searchAll() {
        return null;
    }

    @Override
    public boolean insert(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
