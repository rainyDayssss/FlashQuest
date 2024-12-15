package Backend.Service;

import Backend.Model.User;
import Backend.Repo.FlashQuestRepo;

import java.util.ArrayList;

public class UserService {
    private final FlashQuestRepo flashQuestRepo;
    private User user;

    public UserService(FlashQuestRepo flashQuestRepo) {
        this.flashQuestRepo = flashQuestRepo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User logIn(String email, String password) {
        return flashQuestRepo.logIn(email, password);
    }

    public void signUp(String email, String password, String username) {
        flashQuestRepo.signUp(email, password, username);
    }

    public ArrayList<User> getAllUsers() {
        return flashQuestRepo.getAllUsers();
    }

    public User getUser() {
        return user;
    }
}
