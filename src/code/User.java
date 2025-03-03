package src.code;

import java.util.UUID;

/**
 * This class deals with the individual data of the user
 * @author Amaya Shabazz
 */
public class User {
    
    private final UUID id;
    private String userName;
    private String password;

    /**
     * Contructor for a new user
     * @param id User's id
     * @param userName User's chosen username
     * @param password User's chose password
     */
    public User(UUID id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
