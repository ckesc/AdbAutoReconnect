package ru.ckesc.adbautoreconnect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = getClass().getResource("main.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Adb auto reconnect");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png"))); //icon from http://www.flaticon.com/
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
