package utils;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe utilitaire de gestion du parsing json
 * @see lib.org.json.simple.parser.JSONParser
 */
public class JsonParser {

    /**
     * Parse un tableau de chaînes à partir d'un fichier json
     * @param fichier chemin vers le fichier json
     * @param cle la clé du tableau json
     * @return le tableau java
     * @throws IOException récupération du fichier json
     * @throws ParseException parsing du fichier json
     */
    public static String[] parseStrings(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(JsonParser.class.getResourceAsStream(fichier)));
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
     * @return un tableau d'objet json
     * @throws IOException récupération du fichier json
     * @throws ParseException parsing du fichier json
     */
    public static JSONObject[] parseObjects(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(JsonParser.class.getResourceAsStream(fichier)));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray arr = (JSONArray) jsonObject.get(cle);
        JSONObject[] res = new JSONObject[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++){
            res[i] = (JSONObject) arr.get(i);
        }
        return res;
    }

    /**
     * Parse un objet json à partir d'un fichier json
     * @param fichier chemin vers le fichier json
     * @param cle la clé de l'objet json
     * @return l'objet json
     * @throws IOException récupération du fichier json
     * @throws ParseException parsing du fichier json
     */
    public static JSONObject parseObject(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(JsonParser.class.getResourceAsStream(fichier)));
        JSONObject jsonObject = (JSONObject) obj;
        return (JSONObject) jsonObject.get(cle);
    }

}
