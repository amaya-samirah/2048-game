

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
}
