package ru.ckesc.adbautoreconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class Controller implements StatusUpdater{
    @FXML
    public javafx.scene.control.TextArea statusLabel;
    public TextField ipTextField;
    public ToggleButton connectButton;

    private ReConnecter reConnecter;

    @SuppressWarnings("UnusedDeclaration")
    public void initialize() {
        statusLabel.setText("");
        reConnecter = new ReConnecter(this);
    }

    @SuppressWarnings("UnusedParameters")
    public void toggleConnect(ActionEvent actionEvent) {
        toggle();
    }

    private void toggle() {
        if (!connectButton.isPressed()) {
            String ip = ipTextField.getText();
            reConnecter.start(ip);
        } else {
            reConnecter.stop();
        }
    }

    @Override
    public void updateStatus(String status) {
        String nowTime = DateFormat.getTimeInstance().format(new Date());
        String logLine = String.format("%s: %s", nowTime, status);
        statusLabel.setText(logLine);
    }

    public void ipAction(ActionEvent actionEvent) {
        connectButton.fire();
    }
}
