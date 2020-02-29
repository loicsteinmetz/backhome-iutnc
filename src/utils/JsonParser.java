package utils;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;

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
     * @throws IOException
     * @throws ParseException
     */
    public String[] parseStrings(String fichier, String cle) throws IOException, ParseException, URISyntaxException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(getClass().getResourceAsStream(fichier)));
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
    public JSONObject[] parseObjects(String fichier, String cle) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(getClass().getResourceAsStream(fichier)));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray arr = (JSONArray) jsonObject.get(cle);
        JSONObject[] res = new JSONObject[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++){
            res[i] = (JSONObject) arr.get(i);
        }
        return res;
    }
}
