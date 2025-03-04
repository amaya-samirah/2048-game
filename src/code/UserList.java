

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

    public boolean hasUser(String userName) {
        boolean hasUser = false;
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                hasUser = true;
            }
        }

        return hasUser;
    }

    public User getUser(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        
        return null;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    public boolean addUser(String userName, String password) {
        if (hasUser(userName)) {
            return false;
        }

        User newUser = new User(userName, password);
        userList.add(newUser);

        return true;
    }

    public void saveUsers() {
        DataManager.saveUsers();
    }
}
