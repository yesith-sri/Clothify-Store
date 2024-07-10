package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import icet.edu.drm.bo.custom.impl.SupplierBoImpl;
import icet.edu.drm.model.Product;
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

public class SupplierFormController implements Initializable {

    public AnchorPane Anchor;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtcompany;
    public JFXTextField txtEmail;
    
    
    
    
    public TableView Table1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAddress;

    SupplierBoImpl supplierBoImpl = new SupplierBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        Table1.setItems(supplierBoImpl.getAllSupplier());

    }

    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

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

    boolean isAction = true,isMouseClick,isPriceValid ;
    public void rowdata(MouseEvent mouseEvent) {

        int index = Table1.getSelectionModel().getSelectedIndex();


        if(index < 0){
            return;
        }
        String id = colId.getCellData(index).toString();

        if (isAction){
            isPriceValid = true;
            Supplier supplier = supplierBoImpl.getSupById(id);
            txtId.setText(supplier.getId());
            txtName.setText(supplier.getName());
            txtcompany.setText(supplier.getCompany());
            txtEmail.setText(supplier.getEmail());

            if (!supplier.getId().equals("")){
                isMouseClick = true;
            }
        }
    }

    public void AnchorCkick(MouseEvent mouseEvent) {

        txtId.setText("");
        txtName.setText("");
        txtcompany.setText("");
        txtEmail.setText("");
    }
}
