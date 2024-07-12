package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.UserBoImpl;
import icet.edu.drm.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class UserRegFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;

    public TableView Table1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public ComboBox Cmb;
    public Button Addbuttton;
    public AnchorPane Anchor;

    UserBoImpl userBoImpl = new UserBoImpl();
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        loadDropMenu();


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        txtId.setText(userBoImpl.generateEmployeeId());
        Table1.setItems(userBoImpl.getAllUsers());
    }

    private void loadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("Admin Panel");
        items.add("Employee");
        Cmb.setItems(items);
    }

    public void AddAction(ActionEvent actionEvent) {


        Random random = new Random();
        int p = random.nextInt(99999999) + 10000000;

        String encrypt = Integer.toString(p);
        String password = userBoImpl.passwordEncrypt(encrypt);

            User user = new User(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                    password,
                Cmb.getValue().toString(),
                txtAddress.getText()
        );
        if (!txtName.getText().equals("") && userBoImpl.isValidEmail(txtEmail.getText()) && !txtAddress.getText().equals("")) {


            boolean isInsert = userBoImpl.insertUser(user);
            if (isInsert) {
                Table1.setItems(userBoImpl.getAllUsers());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Added");
                alert.setContentText("Employee Added Successfully..!");
                alert.showAndWait();
                txtId.setText(userBoImpl.generateEmployeeId());
                txtAddress.setText("");
                txtName.setText("");
                txtEmail.setText("");
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }

        //clear();
    }

    public void searchAction(ActionEvent actionEvent) {

        User user = userBoImpl.getUserById(txtId.getText());
                txtName.setText(user.getName());
                txtEmail.setText(user.getEmail());
                Cmb.setValue(user.getUsertype());
                txtAddress.setText(user.getAddress());

    }

    public void clear(){

        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        Cmb.setValue("");
        txtAddress.setText("");
    }



    public void UpdateAction(ActionEvent actionEvent) {

        if (!txtEmail.getText().equals("") && !txtAddress.getText().equals("") && !txtName.getText().equals("")){
            User user = new User(txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    null ,
                    null,
                    txtAddress.getText());

            boolean isUpdated = userBoImpl.updateUser(user);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Update");
                alert.setContentText("Employee Updated Successfully");
                alert.showAndWait();
                txtEmail.setText("");
                txtAddress.setText("");
                txtName.setText("");
                Table1.setItems(userBoImpl.getAllUsers());
                clear();
                txtId.setText(userBoImpl.generateEmployeeId());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }

    }


    public void DeleteActiion(ActionEvent actionEvent) {

        if (!txtId.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Employee");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get()== ButtonType.OK){
                boolean isDeleted = userBoImpl.deleteUserById(txtId.getText());
                if (isDeleted){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Employee Deleted");
                    alert2.setContentText("Employee deleted successfully");
                    alert2.showAndWait();
                    Table1.setItems(userBoImpl.getAllUsers());
                    txtEmail.setText("");
                    txtAddress.setText("");
                    txtName.setText("");
                    clear();
                    txtId.setText(userBoImpl.generateEmployeeId());
                }
            }
        }

    }

    public void ReleaseEmailkey(KeyEvent keyEvent) {
        boolean isValidEmail = userBoImpl.isValidEmail(txtEmail.getText());
        if (!isValidEmail) {
            Addbuttton.setVisible(true);
        } else {
            Addbuttton.setDisable(false);
        }
    }

    public void SupplierAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "Supplier.fxml");
    }

    public void ManageAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "UserRegisterForm.fxml");
    }

    public void OrderAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "OrderViewFormController.fxml");
    }

    public void ItemAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "item.fxml");
    }


    public void logOutAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure want to logout..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            sceneSwitch.switchScene(Anchor,"welcomeForm.fxml");
        }
    }
}
