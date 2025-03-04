import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class controls the data from the JSON file
 * @author Amaya Shabazz
 */
public class DataManager {
    
    protected static final String FILENAME = "data/User.json";
    protected static final String ID = "id";
    protected static final String SCORE = "score";
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";  // later change to be more secure

    /**
     * All the users from the JSON file
     * @return The full list of users
     */
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
                int bestScore = ((Long)userjson.get(SCORE)).intValue();

                users.add(new User(id, userName, password, bestScore));
            }

            return users;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates ALL users' information in JSON file
     */
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

    /**
     * The object from the JSON file
     * @param user The user (and information along with it)
     * @return The new info from the JSON file
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userInfo = new JSONObject();
        userInfo.put(ID, user.getId().toString());
        userInfo.put(USERNAME, user.getUserName());
        userInfo.put(PASSWORD, user.getPassword());
        userInfo.put(SCORE, user.getBestScore());

        return userInfo;
    }

}
