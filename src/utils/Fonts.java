package utils;

import javafx.scene.text.Font;
import models.BackHome;

import java.io.File;

/**
 * Classe utilitaire de gestion des polices d'écriture
 */
public class Fonts {

    /**
     * Charge les polices
     */
    public static void load(){
        Font.loadFont(BackHome .class.getResource(
          File.separator + "assets" +
                File.separator + "fonts" +
                File.separator + "ZCOOLQingKeHuangYou-Regular.ttf")
            .toExternalForm(), 10);
        Font.loadFont(BackHome.class.getResource(
          File.separator + "assets" +
                File.separator + "fonts" +
                File.separator + "RobotoSlab-VariableFont:wght.ttf")
            .toExternalForm(), 10);
    }
}
