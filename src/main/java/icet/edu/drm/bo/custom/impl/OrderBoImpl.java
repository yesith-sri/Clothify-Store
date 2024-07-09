package icet.edu.drm.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.bo.custom.OrderBo;
import icet.edu.drm.dao.Custom.impl.OrderDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.entity.OrderEntity;
import icet.edu.drm.entity.OrderHasItemEntity;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.model.Order;
import icet.edu.drm.model.OrderHasItem;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.util.DaoType;
import javafx.collections.ObservableList;


public class OrderBoImpl implements OrderBo {


    OrderDaoImpl orderDaoImpl = DaoFactory.getInstance().getDao(DaoType.ORDER);

    public String generateOrderId() {


        String lastEmployeeId =orderDaoImpl.getLatestId();
        if (lastEmployeeId==null){
            return "O0001";
        }

        int number = Integer.parseInt(lastEmployeeId.split("O")[1]);
        number++;
        return String.format("O%04d", number);
    }

    public boolean insertOrder(Order order) {

        OrderEntity orderEntity = new ObjectMapper().convertValue(order, OrderEntity.class);
        return orderDaoImpl.insert(orderEntity);
    }

    public boolean saveOrderDetails(ObservableList<OrderHasItem> orderHasItemObservableList) {
        orderHasItemObservableList.forEach(orderHasItem -> {
            OrderHasItemEntity orderHasItemEntity = new ObjectMapper().convertValue(orderHasItem, OrderHasItemEntity.class);
            orderDaoImpl.saveAll(orderHasItemEntity);

        });
        return true;
    }


    public Order getOrderById(String orderId) {
         OrderEntity orderEntity = orderDaoImpl.searchById(orderId);
        return new ObjectMapper().convertValue(orderEntity, Order.class);

    }
}
