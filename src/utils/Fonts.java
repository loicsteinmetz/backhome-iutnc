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
        Font.loadFont(BackHome .class.getResource("/assets/fonts/ZCOOLQingKeHuang.ttf")
            .toExternalForm(), 10);
        Font.loadFont(BackHome.class.getResource("/assets/fonts/RobotoSlab.ttf")
            .toExternalForm(), 10);
    }
}
