package SignUpPage;

import Backend.Controller.FlashQuestController;
import LoginPage.loginPage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController {
    private final Stage stage;
    private final TextField usernameField;
    private final TextField emailField;
    private final PasswordField passwordField;
    private final FlashQuestController flashQuestController;
    private final Text errorMessage;

    // Constructor to pass the Stage and UI components
    public SignUpController(Stage stage, TextField emailField, TextField usernameField, PasswordField passwordField, FlashQuestController flashQuestController, Text errorMessage) {
        this.stage = stage;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.errorMessage = errorMessage;
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
            errorMessage.setText("Fields cannot be empty."); // Set error text
            errorMessage.setVisible(true); // Show error message
        }
    }
}