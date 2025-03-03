package src.code;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataManager {
    
    protected static final String FILENAME = "data/User.json";
    protected static final String ID = "id";
    protected static final String SCORE = "score";
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";  // later change to be more secure

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(FILENAME);
            JSONParser parser = JSONParser();
            JSONArray userjson = (JSONArray)new JSONParser().parse(reader);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
