package SignUpPage;

import Backend.Controller.FlashQuestController;
import LoginPage.loginPage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
    private final Stage stage;
    private final TextField usernameField;
    private final TextField emailField;
    private final PasswordField passwordField;
    private final FlashQuestController flashQuestController;

    // Constructor to pass the Stage and UI components
    public SignUpController(Stage stage, TextField emailField, TextField usernameField, PasswordField passwordField, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.flashQuestController = flashQuestController;
    }

    // Method to handle Login button click
    public void onLoginButtonClicked() {
        loginPage loginPage1 = new loginPage(stage, flashQuestController);  // Correct capitalization (loginPage)
        loginPage1.show();  // Show the LoginPage
    }

    // Method to handle Sign up button click
    public void onSignUpButtonClicked() {
        String username = usernameField.getText();
        String email = emailField.getText(); // should be username
        String password = passwordField.getText();

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);

            // Sign Up Logic
            flashQuestController.signUP(email, password, username);

            loginPage loginPage1 = new loginPage(stage, flashQuestController);  // Appears if done signing up
            loginPage1.show();  // Show the LoginPage
        } else {
            System.out.println("There was an error, try again");
        }
    }
}
