
import java.io.FileReader;
import java.io.FileWriter;
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
            //JSONParser parser = JSONParser();
            JSONArray usersjson = (JSONArray)new JSONParser().parse(reader);

            for (int i = 0; i < usersjson.size(); i++) {
                JSONObject userjson = (JSONObject)usersjson.get(i);
                UUID id = UUID.fromString((String)userjson.get(ID));
                String userName =(String)userjson.get(USERNAME);
                String password = (String)userjson.get(PASSWORD);
                int bestScore = (int)userjson.get(SCORE);

                users.add(new User(id, userName, password, bestScore));
            }

            return users;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();

        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        // write to file
        try (FileWriter json = new FileWriter(FILENAME)) {
            json.write(jsonUsers.toJSONString());
            json.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userInfo = new JSONObject();
        userInfo.put(ID, user.getId().toString());
        userInfo.put(USERNAME, user.getUserName());
        userInfo.put(PASSWORD, user.getPassword());

        return userInfo;
    }

}
