package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.CustomerBoImpl;
import icet.edu.drm.bo.custom.impl.UserBoImpl;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCusFormController implements Initializable {


    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;


    public TableView Table1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public AnchorPane Anchor;

    CustomerBoImpl customerBoImpl = new CustomerBoImpl();
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtId.setText(customerBoImpl.generateEmployeeId());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        Table1.setItems(customerBoImpl.getAllCustomer());

    }
    public void AddAction(ActionEvent actionEvent) {

        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtAddress.getText()

        );

        if (!txtName.getText().equals("") && customerBoImpl.isValidEmail(txtEmail.getText()) && !txtAddress.getText().equals("")) {


            boolean isInsert = customerBoImpl.insertUser(customer);
            if (isInsert) {
                Table1.setItems(customerBoImpl.getAllCustomer());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added");
                alert.setContentText("Customer Added Successfully..!");
                alert.showAndWait();
                txtAddress.setText("");
                txtName.setText("");
                txtEmail.setText("");
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }

    }

    public void searchAction(ActionEvent actionEvent) {

        Customer customer = customerBoImpl.getCusById(txtId.getText());
        txtName.setText(customer.getName());
        txtEmail.setText(customer.getEmail());
        txtAddress.setText(customer.getAddress());
    }

    public void DeleteActiion(ActionEvent actionEvent) {


        if (!txtId.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Customer");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get()== ButtonType.OK){
                boolean isDeleted = customerBoImpl.deleteCusById(txtId.getText());
                if (isDeleted){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Customer Deleted");
                    alert2.setContentText("Customer deleted successfully");
                    alert2.showAndWait();
                    Table1.setItems(customerBoImpl.getAllCustomer());
                    txtEmail.setText("");
                    txtAddress.setText("");
                    txtName.setText("");
                    clear();
                    txtId.setText(customerBoImpl.generateEmployeeId());
                }
            }
        }
    }

    public void UpdateAction(ActionEvent actionEvent) {

        if (!txtEmail.getText().equals("") && !txtAddress.getText().equals("") && !txtName.getText().equals("")){
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText());

            boolean isUpdated = customerBoImpl.updateCus(customer);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Update");
                alert.setContentText("Customer Updated Successfully");
                alert.showAndWait();
                txtEmail.setText("");
                txtAddress.setText("");
                txtName.setText("");
                Table1.setItems(customerBoImpl.getAllCustomer());
                clear();
                txtId.setText(customerBoImpl.generateEmployeeId());

            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }


    public void clear(){

        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }





    public void SupplierAction(ActionEvent actionEvent) throws IOException {

        sceneSwitch.switchScene(Anchor, "AddSupFormController.fxml");

    }


    public void PlaceAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "placeOrder.fxml");
    }

    public void CusAction(ActionEvent actionEvent) throws IOException {

        sceneSwitch.switchScene(Anchor, "AddCusFormController.fxml");
    }

    public void ProductAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor, "AddProductFormController.fxml");


    }

    public void log(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure want to logout..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            sceneSwitch.switchScene(Anchor,"welcomeForm.fxml");
        }
    }


}
