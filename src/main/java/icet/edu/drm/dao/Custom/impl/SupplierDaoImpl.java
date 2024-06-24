package icet.edu.drm.dao.Custom.impl;

import icet.edu.drm.dao.Custom.SupplierDao;
import icet.edu.drm.entity.CustomerEntity;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao{
    @Override
    public SupplierEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<SupplierEntity> searchAll() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        List<SupplierEntity> list = session.createQuery("FROM supplier").list();
        session.close();
        ObservableList<SupplierEntity> supplierEntityList = FXCollections.observableArrayList();

        list.forEach(supplierEntity -> {
            supplierEntityList.add(supplierEntity);
        });
        return supplierEntityList;
    }

    @Override
    public boolean insert(SupplierEntity supplierEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE supplier SET name =:name,company =:company,email =:email WHERE id =:id");
        query.setParameter("name",supplierEntity.getName());
        query.setParameter("company",supplierEntity.getCompany());
        query.setParameter("email",supplierEntity.getEmail());
        query.setParameter("id",supplierEntity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    @Override
    public boolean delete(String s) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("DELETE FROM supplier WHERE id=:id");
        query.setParameter("id",s);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    public String getLatestId() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;

    }

    public SupplierEntity searchById(String id) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM supplier WHERE id=:id");
        query.setParameter("id",id);
        SupplierEntity supplierEntity = (SupplierEntity) query.uniqueResult();
        session.close();

        return supplierEntity;
    }
}
