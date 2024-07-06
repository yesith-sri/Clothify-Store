package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.BoFactory;
import icet.edu.drm.bo.custom.impl.UserBoImpl;
import icet.edu.drm.entity.UserEntity;
import icet.edu.drm.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class welcomeFormController implements Initializable {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane welcomeAnchor;
    private boolean isShow;

    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();
    UserBoImpl userBoImpl=new UserBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isShow=true;
    }
    public void SignAction(ActionEvent actionEvent) throws IOException {

        userBoImpl=BoFactory.getInstance().getBo(BoType.USER);

        UserEntity userEntity = userBoImpl.getUserByEmail(txtUserName.getText());


        if (userEntity==null){
            new Alert(Alert.AlertType.ERROR,"Invalid Email Address or Password").show();
            return;
        }
        String password = userBoImpl.passwordDecrypt(userEntity.getPassword());

        if (userEntity.getUsertype().equals("Admin Panel") && password.equals(txtPassword.getText())){
            System.out.println("Logged");
            try {
                SceneSwitchController.getInstance().switchScene(welcomeAnchor, "UserRegisterForm.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (userEntity.getUsertype().equals("Employee") && password.equals(txtPassword.getText())){
            SceneSwitchController.getInstance().switchScene(welcomeAnchor, "AddCusFormController.fxml");
        } else if (userEntity.getId()==null) {
            System.out.println("Null");
        } else{
            new Alert(Alert.AlertType.ERROR,"Invalid Password").show();
        }

    }

    public void forgetAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(welcomeAnchor, "ResetForm.fxml");
    }

    public void NeedAction(ActionEvent actionEvent) {

    }


    public void emailKeyReleasedAction(KeyEvent keyEvent) {
        if (isShow){
            new Alert(Alert.AlertType.INFORMATION,"If your first time to sign in to this, Please reset your password clicked forgot password button").show();
            isShow=false;
        }
    }


}
