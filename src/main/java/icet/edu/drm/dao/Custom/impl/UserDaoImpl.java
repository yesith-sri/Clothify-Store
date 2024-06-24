package icet.edu.drm.dao.Custom.impl;

import icet.edu.drm.dao.Custom.UserDao;
import icet.edu.drm.entity.UserEntity;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {



    @Override
    public boolean insert(UserEntity userEntity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        session.persist(userEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE user SET name =:name,address =:address,email =:email WHERE id =:id");
        query.setParameter("name",userEntity.getName());
        query.setParameter("address",userEntity.getAddress());
        query.setParameter("email",userEntity.getEmail());
        query.setParameter("id",userEntity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM user WHERE id=:id");
        query.setParameter("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public UserEntity search(String s) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM user WHERE email=:email");
        query.setParameter("email",s);
        UserEntity userEntity = (UserEntity) query.uniqueResult();
        session.close();
        return userEntity;
    }

    public ObservableList<UserEntity> searchAll() {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();

        List<UserEntity> userList = session.createQuery("FROM user").list();
        ObservableList<UserEntity> list= FXCollections.observableArrayList();
        session.close();
        userList.forEach(userEntity -> {
            list.add(userEntity);
        });
        return list;
    }

    public UserEntity searchById(String id) {

        Session session = HibernateUtil.getSession();
        session.getTransaction();

        Query query = session.createQuery("FROM user WHERE id=:id");
        query.setParameter("id",id);
        UserEntity userEntity = (UserEntity) query.uniqueResult();
        session.close();
        return userEntity;

    }

    public String getLatestId() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public boolean update(String email, String encryptPassword) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE user SET password =:p WHERE email =:e");
        query.setParameter("p",encryptPassword);
        query.setParameter("e",email);
        int i = query.executeUpdate();
        System.out.println(i);

        session.getTransaction().commit();
        session.close();
        return i>0;
    }
}
