package utils;

import javafx.scene.text.Font;
import models.BackHome;

/**
 * Classe utilitaire de gestion des polices d'écriture
 */
public class Fonts {

    /**
     * Charge les polices
     */
    public static void load(){
        Font.loadFont(BackHome .class.getResource("/assets/fonts/ZCOOLQingKeHuangYou-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(BackHome.class.getResource("/assets/fonts/RobotoSlab-VariableFont:wght.ttf").toExternalForm(), 10);
    }
}
