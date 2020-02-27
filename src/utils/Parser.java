package utils;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe utilitaire de gestion du parsing
 */
public class Parser {

    /**
     * Parse un tableau de chaînes à partir d'un fichier json
     * @param fichier chemin vers le fichier json
     * @param cle la clé du tableau json
     * @return le tableau java
     * @throws IOException
     * @throws ParseException
     */
    public static String[] parseStrings(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fichier));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray arr = (JSONArray) jsonObject.get(cle);
        String[] res = new String[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++){
            res[i] = (String) arr.get(i);
        }
        return res;
    }

    /**
     * Parse un tableau d'objets json à partir d'un fichier json
     * @param fichier chemin vers le fichier json
     * @param cle la clé de l'objet json
     * @return l'objet json
     * @throws IOException
     * @throws ParseException
     */
    public static JSONObject[] parseObjects(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fichier));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray arr = (JSONArray) jsonObject.get(cle);
        JSONObject[] res = new JSONObject[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++){
            res[i] = (JSONObject) arr.get(i);
        }
        return res;
    }

    /**
     * Parse un chemin au format linux pour format universel
     * @param chemin chemin au format linux
     * @return format universel
     */
    public static String parseChemin(String chemin){
        String[] split = chemin.split("");
        for (String s : split){
            if (s == "/") s = File.separator;
        }
        return String.join("", split);
    }
}
