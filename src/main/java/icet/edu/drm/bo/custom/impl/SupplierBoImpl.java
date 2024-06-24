package icet.edu.drm.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.bo.custom.SupplierBo;
import icet.edu.drm.dao.Custom.impl.SupplierDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SupplierBoImpl implements SupplierBo {

    SupplierDaoImpl supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);


    public boolean isValidEmail(String email) {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean insertSupplier(Supplier supplier) {

        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return  supplierDao.insert(supplierEntity);

    }

    public ObservableList getAllSupplier() {

        ObservableList<SupplierEntity> supplierEntities = supplierDao.searchAll();
        ObservableList<Supplier> list = FXCollections.observableArrayList();

        supplierEntities.forEach(supplierEntity -> {
            list.add(new ObjectMapper().convertValue(supplierEntity, Supplier.class));
        });
        return list;
    }

    public String generateSupId() {

        String lastSupId = supplierDao.getLatestId();
        if (lastSupId==null){
            return "S0001";
        }

        int number = Integer.parseInt(lastSupId.split("S")[1]);
        number++;
        return String.format("S%04d", number);
    }

    public Supplier getSupById(String id) {

        SupplierEntity supplierEntity = supplierDao.searchById(id);
        return new ObjectMapper().convertValue(supplierEntity, Supplier.class);

    }

    public boolean deleteSupById(String text) {
        return supplierDao.delete(text);
    }

    public boolean updateSup(Supplier supplier) {

        return supplierDao.update(new ObjectMapper().convertValue(supplier, SupplierEntity.class));
    }
}
