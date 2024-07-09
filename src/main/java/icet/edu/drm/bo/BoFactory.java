package icet.edu.drm.bo;

import icet.edu.drm.bo.custom.impl.*;
import icet.edu.drm.util.BoType;

public class BoFactory {

    private static BoFactory instance;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return instance!=null?instance:(instance=new BoFactory());
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case USER:return (T)new UserBoImpl();
            case CUSTOMER:return (T)new CustomerBoImpl();
            case PRODUCT:return (T)new ProductBoImpl();
            case SUPPLIER:return (T) new SupplierBoImpl();
            case ORDER:return (T) new OrderBoImpl();
        }
        return null;
    }
}
