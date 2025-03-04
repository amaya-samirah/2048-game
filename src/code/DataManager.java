
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
            JSONArray usersjson = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < usersjson.size(); i++) {
                JSONObject userjson = (JSONObject)usersjson.get(i);
                UUID id = UUID.fromString((String)userjson.get(ID));
                String userName =(String)userjson.get(USERNAME);
                String password = (String)userjson.get(PASSWORD);

                users.add(new User(id, userName, password));
            }

            return users;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
