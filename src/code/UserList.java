import java.util.ArrayList;

/**
 * This class deals with all the users
 * @author Amaya Shabazz
 */
public class UserList {

    private static UserList users;
    private ArrayList<User> userList;

    private UserList() {
        userList = DataManager.getUsers();
    }

    public static UserList getInstance() {
        if (users == null) {
            users = new UserList();
        }

        return users;
    }

    /**
     * Determines if the user exists
     * @param userName User's username
     * @return If the user exists
     */
    public boolean hasUser(String userName) {
        boolean hasUser = false;
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                hasUser = true;
            }
        }

        return hasUser;
    }

    /**
     * The user that's logged in
     * @param userName User's username
     * @param password User's password
     * @return The user corresponding to username and password
     */
    public User getUser(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        
        return null;
    }

    /**
     * All the users
     * @return The list of users
     */
    public ArrayList<User> getUsers() {
        return userList;
    }

    /**
     * Adds a new user
     * @param userName User's chosen username
     * @param password User's chose password
     * @return If adding the new user was successful
     */
    public boolean addUser(String userName, String password) {
        if (hasUser(userName)) {
            return false;
        }

        User newUser = new User(userName, password);
        userList.add(newUser);

        return true;
    }

    /**
     * Saving users to JSON file
     */
    public void saveUsers() {
        DataManager.saveUsers();
    }
}
