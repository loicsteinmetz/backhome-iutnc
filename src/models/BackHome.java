package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BackHome extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/BackHome.fxml"));
        Scene scene = new Scene(root, 800, 600);
        Font.loadFont(BackHome.class.getResource("/assets/fonts/ZCOOLQingKeHuangYou-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(BackHome.class.getResource("/assets/fonts/RobotoSlab-VariableFont:wght.ttf").toExternalForm(), 10);
        scene.getStylesheets().add("assets/css/BackHome.css");
        primaryStage.setTitle("Back Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
