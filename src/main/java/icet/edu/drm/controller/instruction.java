package icet.edu.drm.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class instruction {

    public AnchorPane welcomeAnchor;
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    public void Logout(MouseEvent mouseEvent) throws IOException {

        sceneSwitch.switchScene(welcomeAnchor, "welcomeForm.fxml");
    }
}
