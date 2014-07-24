package ru.ckesc.adbautoreconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class Controller {
    @FXML
    public Label statusLabel;
    public TextField ipTextField;
    public ToggleButton connectButton;

    public void toggleConnect(ActionEvent actionEvent) {
//        if (connectButton.isSelected()) {
//        }
        statusLabel.setText(String.valueOf(connectButton.isSelected()));
    }
}
