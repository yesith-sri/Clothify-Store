package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.BoFactory;
import icet.edu.drm.bo.custom.impl.CustomerBoImpl;
import icet.edu.drm.bo.custom.impl.OrderBoImpl;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import icet.edu.drm.bo.custom.impl.SupplierBoImpl;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.Order;
import icet.edu.drm.model.Product;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.util.BoType;
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

public class OrderViewFormController implements Initializable {

    public TableView Table1;
    public TableColumn colId;
    public TableColumn colItemCode;
    public TableColumn colDiscount;
    public TableColumn colQty;

    public AnchorPane Anchor;


    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;

    public JFXTextField txtId1Sup;
    public JFXTextField txtName1Sup;
    public JFXTextField txtcompanySup;
    public JFXTextField txtEmail1Sup;

    String id;
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    ProductBoImpl productBoImpl = new ProductBoImpl();
    OrderBoImpl orderBoImpl = BoFactory.getInstance().getBo(BoType.ORDER);
    CustomerBoImpl customerBoImpl = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    SupplierBoImpl supplierBoImpl = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("discount"));

        Table1.setItems(productBoImpl.getAllOrder());

    }

    boolean isRowSelect, isMouseClick, isPriceValid;

    public void RowData(MouseEvent mouseEvent) {
        int index = Table1.getSelectionModel().getSelectedIndex();

        try {
            isRowSelect = true;
            String orderId = colId.getCellData(index).toString();
            String itemId = colItemCode.getCellData(index).toString();
            Product product = productBoImpl.getProById(itemId);

            id = orderId;

            Order order = orderBoImpl.getOrderById(orderId);
            Customer customer = customerBoImpl. getCusById(order.getCusId());
            Supplier supplier = supplierBoImpl.getSupById(product.getSupId());

            txtAddress.setText(customer.getAddress());
            txtId.setText(customer.getId());
            txtEmail.setText(customer.getEmail());
            txtName.setText(customer.getName());

            txtId1Sup.setText(supplier.getId());
            txtName1Sup.setText(supplier.getName());
            txtcompanySup.setText(supplier.getCompany());
            txtEmail1Sup.setText(supplier.getEmail());

        } catch(Exception e)
    {
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


    public void AnchorClick(MouseEvent mouseEvent) {

        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");

        txtId1Sup.setText("");
        txtName1Sup.setText("");
        txtcompanySup.setText("");
        txtEmail1Sup.setText("");

    }
}
