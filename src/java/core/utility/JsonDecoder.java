/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mobileapps1
 */
public class JsonDecoder {

    public static String getJsonValue(JSONObject obj, String key) {
        String value = "";
        try {
            value = obj.get(key).toString();
        } catch (Exception e) {
        }
        return value;
    }
    
    public static JSONObject getJson(String json){
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(json);
        } catch (ParseException ex) {
            System.out.println("Json Parser Err "+ ex.toString());
        }
        return getJson("{}");
    }
}
