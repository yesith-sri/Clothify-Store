package icet.edu.drm.dao.Custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.dao.Custom.OrderDao;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.entity.OrderEntity;
import icet.edu.drm.entity.OrderHasItemEntity;
import icet.edu.drm.model.OrderHasItem;
import icet.edu.drm.util.DaoType;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDaoImpl implements OrderDao {

   // OrderDaoImpl orderDao = DaoFactory.getInstance().getDao(DaoType. ORDER);
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

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(orderEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM order_table ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public boolean saveAll(ObservableList<OrderHasItem> orderHasItemObservableList) {


        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        orderHasItemObservableList.forEach(orderHasItem -> {
            OrderHasItemEntity orderHasItemEntity = new ObjectMapper().convertValue(orderHasItem, OrderHasItemEntity.class);

           // orderDao.updateQty(orderHasItemEntity.getItemCode(),orderHasItemEntity.getQty());
            session.persist(orderHasItemEntity);
        });
        session.getTransaction().commit();
        session.close();
        return true;
    }

    private void updateQty(String itemCode, Integer qty) {

    }
}