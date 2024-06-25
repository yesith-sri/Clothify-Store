package icet.edu.drm.bo.custom.impl;

import icet.edu.drm.bo.custom.OrderBo;
import icet.edu.drm.dao.Custom.impl.OrderDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.util.DaoType;

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
}
