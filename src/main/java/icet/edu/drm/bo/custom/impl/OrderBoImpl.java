package icet.edu.drm.bo.custom.impl;

import icet.edu.drm.dao.Custom.impl.CustomerDaoImpl;
import icet.edu.drm.dao.Custom.impl.OrderDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.util.DaoType;

public class OrderBoImpl {


    OrderDaoImpl orderDaoImpl = DaoFactory.getInstance().getDao(DaoType.ORDER);
}
