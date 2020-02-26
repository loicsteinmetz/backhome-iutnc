package utils;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Parser {

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
}
