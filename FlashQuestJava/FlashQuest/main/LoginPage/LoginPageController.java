package LoginPage;

import Backend.Controller.FlashQuestController;
import SignUpPage.signUp;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ChooseClassPage.chooseClass;

public class LoginPageController {
    private final Stage stage;
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final FlashQuestController flashQuestController;

    public LoginPageController(Stage stage, TextField usernameField, PasswordField passwordField, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.flashQuestController = flashQuestController;
    }

    public void onLoginButtonClicked() {
        String username = usernameField.getText(); // email should be username
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            if (flashQuestController.logIn(username, password) != null) {
                // Reuse the existing stage to transition smoothly
                chooseClass classPage = new chooseClass(stage, flashQuestController);
                classPage.show();
            } else {
                System.out.println("User was not found");
            }
        } else {
            System.out.println("Fields cannot be empty.");
        }
    }

    public void onSignUpButtonClicked() {
        // Pass the Stage to the SignUpPage to display it
        signUp signUpPage = new signUp(stage, flashQuestController); // Correct capitalization (SignUp -> signUp)
        signUpPage.show();  // Show the SignUpPage
    }
}
