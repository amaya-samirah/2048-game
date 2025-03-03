package src.code;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class deals with all the users
 * @author Amaya Shabazz
 */
public class UserList {

    private static UserList instance;
    private ArrayList<User> users;

    private UserList() {

    }

    public static synchronized UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }

        return instance;
    }

    /**
     * Creates account for new user
     * @param userName User's chosen username
     * @param password User's chosen password
     * @return If account creation was successful
     */
    public boolean createAccount(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }

        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return false;
            }
        }

        User newUser = new User(UUID.randomUUID(), userName, password);
        return true;
    }
    
}
