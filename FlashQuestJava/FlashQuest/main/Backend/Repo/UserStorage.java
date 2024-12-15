package Backend.Repo;

import Backend.Model.User;

import java.util.ArrayList;

public class UserStorage {
    private final ArrayList<User> userList;
    private static final UserStorage instance = new UserStorage(); // to only have a single storage object

    public UserStorage() {
        userList = new ArrayList<>();
        addUser("admin", "admin", "admin");
    }

    public void addUser(String email, String password, String username) {
        userList.add(new User(email, password, username));
    }

    public boolean doUsernameAndPasswordExist(String email, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public User login(String username, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public static UserStorage getInstance() {
        return instance;
    }
}
