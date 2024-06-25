package icet.edu.drm.bo.custom.impl;

import icet.edu.drm.bo.custom.ProductBo;
import icet.edu.drm.dao.Custom.ProductDao;
import icet.edu.drm.dao.Custom.impl.ProductDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.util.DaoType;

public class ProductBoImpl implements ProductBo {

    ProductDaoImpl productDao= DaoFactory.getInstance().getDao(DaoType.PRODUCT);






}
