package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import icet.edu.drm.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemControllerForm implements Initializable {
    public AnchorPane Anchor;
    public TableView Table1;
    public TableColumn colId;
    public TableColumn ColSize;
    public TableColumn colName;
    public TableColumn ColQty;
    public TableColumn ColSupId;
    public TableColumn colPrice;
    
    
    
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public ComboBox cmb;
    public ComboBox cmbSup;
    public JFXTextField txtprice;

    ProductBoImpl productBoImpl = new ProductBoImpl();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        Table1.setItems(productBoImpl.getAllPro());

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
    public void Rowdata(MouseEvent mouseEvent) {
        int index = Table1.getSelectionModel().getSelectedIndex();


        if(index < 0){
            return;
        }
        String id = colId.getCellData(index).toString();

        if (isAction){
            isPriceValid = true;
            Product product = productBoImpl.getProById(id);
            txtId.setText(product.getId());
            txtName.setText(product.getName());
            txtQty.setText(String.valueOf(product.getQty()));
            txtprice.setText(String.valueOf(product.getPrice()));
            cmb.getSelectionModel().select(product.getSize());
            cmbSup.getSelectionModel().select(product.getSupId());

            if (!product.getId().equals("")){
                isMouseClick = true;
            }
        }
    }
}
