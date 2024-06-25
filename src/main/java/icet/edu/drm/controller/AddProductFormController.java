package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import icet.edu.drm.bo.custom.impl.SupplierBoImpl;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.Product;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public ComboBox cmb;
    public ComboBox cmbSup;
    public JFXTextField txtprice;


    public AnchorPane Anchor2;

    public TableView Table1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn ColSize;
    public TableColumn ColQty;
    public TableColumn ColSupId;
    public TableColumn colPrice;


    ProductBoImpl productBoImpl = new ProductBoImpl();
    SupplierBoImpl supplierBoImpl = new SupplierBoImpl();
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDropMenu();
        loadSuplierIds();

        txtId.setText(productBoImpl.generateProId());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        Table1.setItems(productBoImpl.getAllPro());
    }

    private void loadSuplierIds() {

        ObservableList<Supplier> allSupplier = supplierBoImpl.getAllSupplier();
        ObservableList ids = FXCollections.observableArrayList();

        allSupplier.forEach(supplier -> {
            ids.add(supplier.getId());

        });
        cmbSup.setItems(ids);
    }

    private void loadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("XS");
        items.add("S");
        items.add("M");
        items.add("L");
        items.add("2XL");
        items.add("3XL");
        cmb.setItems(items);
    }

    public void AddAction(ActionEvent actionEvent) {

         String id = txtId.getText();
         String name = txtName.getText();
         String size =cmb.getValue().toString();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double price  =Double.valueOf( txtprice.getText());
        String cmb2 =cmbSup.getValue().toString();

        Product product =new Product(
                id,name,size,qty,price,cmb2
        );

        if (!txtName.getText().equals("") && !txtQty.getText().equals("")) {


            boolean isInsert = productBoImpl.insertPro(product);
            if (isInsert) {
                Table1.setItems(productBoImpl.getAllPro());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Added");
                alert.setContentText("Product Added Successfully..!");
                alert.showAndWait();
                txtprice.setText("");
                txtName.setText("");
                txtQty.setText("");
                cmb.setValue("");
                cmbSup.setValue("");
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
    }

    public void searchAction(ActionEvent actionEvent) {
        Product product = productBoImpl.getProById(txtId.getText());
        txtName.setText(product.getName());
        txtQty.setText(String.valueOf(product.getQty()));
        txtprice.setText(String.valueOf(product.getPrice()));
        cmb.setValue(product.getSize());
        cmbSup.setValue(product.getSupId());
    }

    public void DeleteActiion(ActionEvent actionEvent) {

        if (!txtId.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Product");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get()== ButtonType.OK){
                boolean isDeleted = productBoImpl.deleteProById(txtId.getText());
                if (isDeleted){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Product Deleted");
                    alert2.setContentText("Product deleted successfully");
                    alert2.showAndWait();
                    Table1.setItems(productBoImpl.getAllPro());
                    txtprice.setText("");
                    txtName.setText("");
                    txtQty.setText("");
                    cmb.setValue("");
                    cmbSup.setValue("");
                    txtId.setText(productBoImpl.generateProId());
                }
            }
        }
    }

    public void UpdateAction(ActionEvent actionEvent) {


        if (!txtName.getText().equals("") && !txtQty.getText().equals("")){

            String id = txtId.getText();
            String name = txtName.getText();
            String size =cmb.getValue().toString();
            Integer qty = Integer.parseInt(txtQty.getText());
            Double price  =Double.valueOf( txtprice.getText());
            String cmb2 =cmbSup.getValue().toString();

            Product product =new Product(
                    id,name,size,qty,price,cmb2
            );

            boolean isUpdated = productBoImpl.updatePro(product);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Update");
                alert.setContentText("Product Updated Successfully");
                alert.showAndWait();
                txtprice.setText("");
                txtName.setText("");
                txtQty.setText("");
                cmb.setValue("");
                cmbSup.setValue("");
                Table1.setItems(productBoImpl.getAllPro());
                txtId.setText(productBoImpl.generateProId());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }

    }


    public void ProductAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor2, "AddProductFormController.fxml");
    }

    public void CusAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor2, "AddCusFormController.fxml");
    }

    public void PlaceAction(ActionEvent actionEvent) {
    }

    public void SupplierAction(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(Anchor2, "AddSupFormController.fxml");
    }

    public void log(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure want to logout..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            sceneSwitch.switchScene(Anchor2,"welcomeForm.fxml");
        }
    }


}
