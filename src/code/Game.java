public class Game {
    
    private UserList users;
    private GameManager manager;
    private User currUser;

    public Game(UserList users) {
        this.users = users;
    }

    public void play() {
        this.manager  = new GameManager(currUser.getBestScore());
        manager.run();
    }

    public boolean logIn(String userName, String password) {
        if (!users.hasUser(userName, password)) {
            return false;
        }
        currUser = users.getUser(userName, password);
        return true;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void logOut() {
        users.saveUsers();
    }
}
