package icet.edu.drm.controller;

import com.jfoenix.controls.JFXTextField;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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



    ProductBoImpl productBoImpl = new ProductBoImpl();
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDropMenu();

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
    }

    public void searchAction(ActionEvent actionEvent) {
    }

    public void DeleteActiion(ActionEvent actionEvent) {
    }

    public void UpdateAction(ActionEvent actionEvent) {
    }





    public void ProductAction(ActionEvent actionEvent) {
    }

    public void CusAction(ActionEvent actionEvent) {
    }

    public void PlaceAction(ActionEvent actionEvent) {
    }

    public void SupplierAction(ActionEvent actionEvent) {
    }

    public void log(ActionEvent actionEvent) {
    }


}
