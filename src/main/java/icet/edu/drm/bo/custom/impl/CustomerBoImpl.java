package icet.edu.drm.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.bo.custom.CustomerBo;
import icet.edu.drm.dao.Custom.impl.CustomerDaoImpl;
import icet.edu.drm.dao.Custom.impl.UserDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.entity.CustomerEntity;
import icet.edu.drm.entity.UserEntity;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.User;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerBoImpl implements CustomerBo {


    CustomerDaoImpl customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    public boolean insertUser(Customer customer) {

        CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);
        return customerDao.insert(customerEntity);

    }

    public boolean isValidEmail(String email) {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String generateEmployeeId() {

        String lastEmployeeId = customerDao.getLatestId();
        if (lastEmployeeId==null){
            return "C0001";
        }

        int number = Integer.parseInt(lastEmployeeId.split("C")[1]);
        number++;
        return String.format("C%04d", number);
    }

    public ObservableList<Customer> getAllCustomer() {
        ObservableList<CustomerEntity> customerEntities = customerDao.searchAll();

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        customerEntities.forEach(customerEntity -> {
            customerList.add(new ObjectMapper().convertValue(customerEntity, Customer.class));
        });
        return customerList;
    }

    public Customer getCusById(String id) {

        CustomerEntity customerEntity = customerDao.searchById(id);
        return new ObjectMapper().convertValue(customerEntity,Customer.class);

    }

    public boolean updateCus(Customer customer) {

        return customerDao.update(new ObjectMapper().convertValue(customer, CustomerEntity.class));
    }

    public boolean deleteCusById(String text) {

        return customerDao.delete(text);
    }
}
