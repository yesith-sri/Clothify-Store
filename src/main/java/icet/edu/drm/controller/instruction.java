package icet.edu.drm.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class instruction implements Initializable {

    public AnchorPane welcomeAnchor;
    public TextArea emptext;
    public TextArea Adminemp;
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

String emp="Clothify POS System Help for Admin:\n" +
        "\n" +
        "Manage Employee:\n" +
        "\n" +
        "Add: Enter employee details and click 'Add'.\n" +
        "View: Enter employee ID and click 'View' for details.\n" +
        "Update: Enter updated details and click 'Update'.\n" +
        "Delete: Enter employee ID and click 'Delete'.\n" +
        "View Orders:\n" +
        "\n" +
        "Click 'View Orders' to see a list of all orders.\n" +
        "View Products:\n" +
        "\n" +
        "Click 'View Products' to see a list of all products.\n" +
        "View Customers:\n" +
        "\n" +
        "Click 'View Customers' to see a list of all customers.\n" +
        "View Suppliers:\n" +
        "\n" +
        "Click 'View Suppliers' to see a list of all suppliers.\n" +
        "Logout:\n" +
        "\n" +
        "Click 'Logout' to securely exit the system.\n" +
        "Clothify POS System Help for Employees:\n" +
        "\n" +
        "Manage Order:\n" +
        "\n" +
        "Add: Enter order details and click 'Add'.\n" +
        "View: Enter order ID and click 'View' for details.\n" +
        "Update: Enter updated details and click 'Update'.\n" +
        "Delete: Enter order ID and click 'Delete'.\n" +
        "Manage Products:\n" +
        "\n" +
        "Click 'Manage Products' to see a list of all products.\n" +
        "Manage Customers:\n" +
        "\n" +
        "Click 'Manage Customers' to see a list of all customers.\n" +
        "Manage Suppliers:\n" +
        "\n" +
        "Click 'Manage Suppliers' to see a list of all suppliers.\n" +
        "Report Generate:\n" +
        "\n" +
        "Click 'Report Generate' to generate and view reports.\n" +
        "Logout:\n" +
        "\n" +
        "Click 'Logout' to securely exit the system.";



String admin = "Clothify POS System FAQs\n" +
        "\n" +
        "For Admins:\n" +
        "\n" +
        "Q: How do I manage employees?\n" +
        "\n" +
        "Add: Enter employee details and click 'Add'.\n" +
        "View: Enter employee ID and click 'View' to see details.\n" +
        "Update: Enter updated details and click 'Update'.\n" +
        "Delete: Enter employee ID and click 'Delete'.\n" +
        "Q: How can I view orders?\n" +
        "\n" +
        "Click 'View Orders' to see a list of all orders.\n" +
        "Q: How do I access product information?\n" +
        "\n" +
        "Click 'View Products' to see a list of all products.\n" +
        "Q: How can I view customer details?\n" +
        "\n" +
        "Click 'View Customers' to see a list of all customers.\n" +
        "Q: How do I see information about suppliers?\n" +
        "\n" +
        "Click 'View Suppliers' to see a list of all suppliers.\n" +
        "Q: How do I log out securely?\n" +
        "\n" +
        "Click 'Logout' to exit the system securely.\n" +
        "For Employees:\n" +
        "\n" +
        "Q: How do I manage orders?\n" +
        "\n" +
        "Add: Enter order details and click 'Add'.\n" +
        "View: Enter order ID and click 'View' to see details.\n" +
        "Update: Enter updated details and click 'Update'.\n" +
        "Delete: Enter order ID and click 'Delete'.\n" +
        "Q: How can I manage product information?\n" +
        "\n" +
        "Click 'Manage Products' to see a list of all products.\n" +
        "Q: How do I manage customer details?\n" +
        "\n" +
        "Click 'Manage Customers' to see a list of all customers.\n" +
        "Q: How can I access supplier information?\n" +
        "\n" +
        "Click 'Manage Suppliers' to see a list of all suppliers.\n" +
        "Q: How do I generate reports?\n" +
        "\n" +
        "Click 'Report Generate' to generate and view reports.\n" +
        "Q: How do I log out securely?\n" +
        "\n" +
        "Click 'Logout' to exit the system securely.";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emptext.setText(emp);
        Adminemp.setText(admin);
    }


    public void Logout(MouseEvent mouseEvent) throws IOException {

        sceneSwitch.switchScene(welcomeAnchor, "welcomeForm.fxml");
    }


}
