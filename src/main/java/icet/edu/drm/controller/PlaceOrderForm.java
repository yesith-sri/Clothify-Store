package icet.edu.drm.controller;

import icet.edu.drm.bo.custom.impl.CustomerBoImpl;
import icet.edu.drm.bo.custom.impl.OrderBoImpl;
import icet.edu.drm.bo.custom.impl.ProductBoImpl;
import icet.edu.drm.model.Customer;
import icet.edu.drm.model.Product;
import icet.edu.drm.model.Supplier;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderForm implements Initializable {


    public AnchorPane Anchor;
    public ComboBox cmbCustomerIDs;
    public Label lblName;
    public Label lblAddress;
    public Label lblEmail;



    public ComboBox cmbItemCode;
    public Label lblDesc;
    public Label lblUnitPrice;
    public Label lblSize;




    public Label lblTime;
    public Label lblDate;
    public Label lblOrderId;


    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colDesc;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label lblNetTotal;
    public TextField txtQtyFroCustomer;

    OrderBoImpl orderBoImpl = new OrderBoImpl();
    ProductBoImpl productBoImpl = new ProductBoImpl();
    CustomerBoImpl customerBoImpl = new CustomerBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDateAndTime();
        loadCustomerIDs();
        loadItemCodes();

        cmbCustomerIDs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerDataFroLbl((String) newValue);
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemDataFroLbl((String) newValue);
        });
    }

    private void setItemDataFroLbl(String ItemCode) {

        Product product = productBoImpl.getProById(ItemCode);
        lblDesc.setText(product.getName());
        lblUnitPrice.setText(String.valueOf(product.getPrice()));
        lblSize.setText(product.getSize());

    }

    private void setCustomerDataFroLbl(String newValue) {

        Customer customer = customerBoImpl.getCusById(newValue);
        lblName.setText(customer.getName());
        lblEmail.setText(customer.getEmail());
        lblAddress.setText(customer.getAddress());
    }



    private void loadItemCodes() {

        ObservableList<Product> allSupplier = productBoImpl.getAllPro();
        ObservableList ids = FXCollections.observableArrayList();

        allSupplier.forEach(supplier -> {
            ids.add(supplier.getId());

        });
        cmbItemCode.setItems(ids);
    }

    private void loadCustomerIDs() {

        ObservableList<Customer> allSupplier = customerBoImpl.getAllCustomer();
        ObservableList ids = FXCollections.observableArrayList();

        allSupplier.forEach(supplier -> {
            ids.add(supplier.getId());

        });
        cmbCustomerIDs.setItems(ids);

    }

    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));


        //Time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime time = LocalTime.now();
            lblTime.setText(
                    time.getHour() + " : " + time.getMinute() + " : " + time.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void txtAddtoCartOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
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
