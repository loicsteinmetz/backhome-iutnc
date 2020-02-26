package utils;

import javafx.scene.text.Font;
import models.BackHome;

public class Fonts {

    public static void load(){
        Font.loadFont(BackHome .class.getResource("/assets/fonts/ZCOOLQingKeHuangYou-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(BackHome.class.getResource("/assets/fonts/RobotoSlab-VariableFont:wght.ttf").toExternalForm(), 10);
    }
}
