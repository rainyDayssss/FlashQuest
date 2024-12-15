package Backend.Repo;
import Backend.Model.User;

import java.util.ArrayList;

// TODO DATABASE LOGIC
public class FlashQuestRepo {
    private final UserStorage userStorage;

    public FlashQuestRepo() {
        userStorage = new UserStorage();
    }

    public User logIn(String email, String password) {
        return userStorage.login(email, password);
    }

    public void signUp(String email, String password, String username) {
        userStorage.addUser(email, password, username);
    }

    public ArrayList<User> getAllUsers() {
        return userStorage.getAllUsers();
    }
}
