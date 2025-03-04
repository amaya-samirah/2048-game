import java.util.UUID;

/**
 * This class deals with the individual data of the user
 * @author Amaya Shabazz
 */
public class User {
    
    private UUID id;
    private String userName;
    private String password;
    private int bestScore;

    /**
     * For constructing a new account
     * @param userName User's chosen username
     * @param password User's chose password
     */
    public User(String userName, String password) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.password = password;
        bestScore = 0;
    }

    /**
     * For loading an existing account
     * @param id User's specific id
     * @param userName User's username
     * @param password User's passwrod
     */
    public User(UUID id, String userName, String password, int bestScore) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.bestScore = bestScore;
    }

    public UUID getId() {
        return id;
    }

    public int getBestScore() {
        return bestScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBestScore(int score) {
        this.bestScore = score;
    }
}
