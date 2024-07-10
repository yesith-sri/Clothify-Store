package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.UserBoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class ResetFormController implements Initializable {
    public TextField txtEmail;
    public JFXTextField txtNewPass;
    public JFXTextField txtRePassWord;
    public TextField txtOTPCode;
    public AnchorPane txtRePass;
    public AnchorPane Anchor;
    private int otp;
    UserBoImpl userBoImpl=new UserBoImpl();

    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public void SendOTPAction(ActionEvent actionEvent) {

        Random random = new Random();
        otp = random.nextInt(999999)+100000;
        System.out.println(otp);

        try {
            userBoImpl.sendEmail(txtEmail.getText(),Integer.toString(otp));
            new Alert(Alert.AlertType.INFORMATION,"Send Email Successfully").show();
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR,"Access Denided..your Email is invalid").show();
        }
    }



    public void ResetAction(ActionEvent actionEvent) {

        try {
            if (txtNewPass.getText().equals(txtRePassWord.getText())){
                if (otp==Integer.parseInt(txtOTPCode.getText())){
                    boolean isUpdatePassword = userBoImpl.isUpdatePassword(txtEmail.getText(),txtNewPass.getText());
                    if (isUpdatePassword){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Reset Password");
                        alert.setContentText("Password reset Successfully");
                        alert.showAndWait();
                        txtNewPass.setText("");
                        txtRePassWord.setText("");
                        txtOTPCode.setText("");
                        sceneSwitch.switchScene(txtRePass, "welcomeForm.fxml");

                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"Incorrect OTP, Please Check your OTP").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"Password & Confirmation Password does not match..!!").show();
            }
        }catch (Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"Invalid OTP").show();
        }
   }


    public void Logout(MouseEvent mouseEvent) throws IOException {
            sceneSwitch.switchScene(Anchor,"welcomeForm.fxml");
    }
}
