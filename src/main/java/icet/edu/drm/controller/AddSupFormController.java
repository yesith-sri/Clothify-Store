package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.SupplierBoImpl;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.Supplier;
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

public class AddSupFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtcompany;
    public JFXTextField txtEmail;


    public TableView Table1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public AnchorPane Anchor;


    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    SupplierBoImpl supplierBoImpl = new SupplierBoImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtId.setText(supplierBoImpl.generateSupId());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        Table1.setItems(supplierBoImpl.getAllSupplier());
    }

    public void AddAction(ActionEvent actionEvent) {

        Supplier supplier = new Supplier(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtcompany.getText()

        );

        if (!txtName.getText().equals("") && supplierBoImpl.isValidEmail(txtEmail.getText()) && !txtcompany.getText().equals("")) {


            boolean isInsert =  supplierBoImpl.insertSupplier(supplier);
            if (isInsert) {
                Table1.setItems( supplierBoImpl.getAllSupplier());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Added");
                alert.setContentText("Supplier Added Successfully..!");
                alert.showAndWait();
                txtcompany.setText("");
                txtName.setText("");
                txtEmail.setText("");
                txtId.setText(supplierBoImpl.generateSupId());
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }

    }

    public void searchAction(ActionEvent actionEvent) {

        Supplier supplier = supplierBoImpl.getSupById(txtId.getText());
        txtName.setText(supplier.getName());
        txtEmail.setText(supplier.getEmail());
        txtcompany.setText(supplier.getCompany());
    }

    public void DeleteActiion(ActionEvent actionEvent) {

        if (!txtId.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Supplier");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get()== ButtonType.OK){
                boolean isDeleted = supplierBoImpl.deleteSupById(txtId.getText());
                if (isDeleted){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Supplier Deleted");
                    alert2.setContentText("Supplier deleted successfully");
                    alert2.showAndWait();
                    Table1.setItems(supplierBoImpl.getAllSupplier());
                    txtEmail.setText("");
                    txtcompany.setText("");
                    txtName.setText("");
                    txtId.setText(supplierBoImpl.generateSupId());
                }
            }
        }
    }

    public void UpdateAction(ActionEvent actionEvent) {

        if (!txtEmail.getText().equals("") && !txtcompany.getText().equals("") && !txtName.getText().equals("")){
            Supplier supplier = new Supplier(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtcompany.getText());

            boolean isUpdated = supplierBoImpl.updateSup(supplier);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Update");
                alert.setContentText("Customer Updated Successfully");
                alert.showAndWait();
                txtEmail.setText("");
                txtcompany.setText("");
                txtName.setText("");
                Table1.setItems(supplierBoImpl.getAllSupplier());
                txtId.setText(supplierBoImpl.generateSupId());

            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }


    public void SupplierAction(ActionEvent actionEvent) throws IOException {

        sceneSwitch.switchScene(Anchor, "AddSupFormController.fxml");

    }


    public void ProductAction(ActionEvent actionEvent) {

    }

    public void CusAction(ActionEvent actionEvent) throws IOException {

        sceneSwitch.switchScene(Anchor, "AddCusFormController.fxml");
    }

    public void PlaceAction(ActionEvent actionEvent) {


    }

    public void logAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure want to logout..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            sceneSwitch.switchScene(Anchor,"welcomeForm.fxml");
        }
    }
}
