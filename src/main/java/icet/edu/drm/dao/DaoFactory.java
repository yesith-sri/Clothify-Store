package icet.edu.drm.dao;

import icet.edu.drm.dao.Custom.impl.*;
import icet.edu.drm.util.DaoType;

import static icet.edu.drm.util.BoType.USER;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USER:return (T)new UserDaoImpl();
            case CUSTOMER:return (T)new CustomerDaoImpl();
            case SUPPLIER:return (T)new SupplierDaoImpl();
            case PRODUCT:return (T)new ProductDaoImpl();
            case ORDER:return (T)new OrderDaoImpl();


        }
        return null;
    }
}
