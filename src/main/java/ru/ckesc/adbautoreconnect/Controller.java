package ru.ckesc.adbautoreconnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.text.DateFormat;
import java.util.Date;

public class Controller implements StatusUpdater{
    @FXML
    public Label statusLabel;
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
        if (connectButton.isSelected()) {
            reConnecter.start();
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
}
