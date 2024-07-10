package icet.edu.drm.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.bo.custom.UserBo;
import icet.edu.drm.controller.ResetFormController;
import icet.edu.drm.dao.Custom.impl.UserDaoImpl;
import icet.edu.drm.dao.DaoFactory;
import icet.edu.drm.entity.UserEntity;
import icet.edu.drm.model.User;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBoImpl implements UserBo {

    UserDaoImpl userDaoImpl = DaoFactory.getInstance().getDao(DaoType.USER);


    public boolean insertUser(User user) {

        UserEntity userEntity = new ObjectMapper().convertValue(user, UserEntity.class);
        return userDaoImpl.insert(userEntity);

    }


    public ObservableList getAllUsers() {

        ObservableList<UserEntity> list = userDaoImpl.searchAll();
        ObservableList<User> userList = FXCollections.observableArrayList();

        list.forEach(userEntity -> {
            userList.add(new ObjectMapper().convertValue(userEntity,User.class));
        });
        return userList;
    }

    public User getUserById(String id) {

        UserEntity userEntity = userDaoImpl.searchById(id);
        return new ObjectMapper().convertValue(userEntity,User.class);

    }

    public boolean updateUser(User user) {

        UserEntity userEntity = new ObjectMapper().convertValue(user, UserEntity.class);

        return userDaoImpl.update(userEntity);

    }

    public boolean deleteUserById(String text) {

        return userDaoImpl.delete(text);

    }

    public String passwordEncrypt(String password) {

        return new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String generateEmployeeId() {

        String lastEmployeeId = userDaoImpl.getLatestId();
        if (lastEmployeeId==null){
            return "E0001";
        }

        int number = Integer.parseInt(lastEmployeeId.split("E")[1]);
        number++;
        return String.format("E%04d", number);
    }

    public void sendEmail(String receiveEmail, String text) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myEmail = "hansana.etec1216@gmail.com";
        String password = "kdluuvcabqlujuuw";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,password);
            }
        });
        String text1 ="Dear User,\n\n" +
                "To reset your password, please use the following One-Time Password (OTP):\n\n" +
                text + "\n\n" +
                "This code is valid for the next 10 minutes. Please do not share this OTP with anyone.\n\n" +
                "Thank You!\n" +
                "(Clothify Store)";

        Message message = prepareMessage(session,myEmail,receiveEmail,text1);
        Transport.send(message);
    }

    private Message prepareMessage(Session session, String myEmail, String receiveEmail, String text) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                    new InternetAddress(receiveEmail)
            });
            message.setSubject("One Time Password");
            message.setText(text);

            return message;
        }catch (Exception e){
            Logger.getLogger(ResetFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }


    public boolean isUpdatePassword(String email, String password) {


        String encryptPassword=passwordEncrypt(password);
        return userDaoImpl.update(email,encryptPassword);
    }

    public UserEntity getUserByEmail(String email) {
        return userDaoImpl.search(email);

    }

    public String passwordDecrypt(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
