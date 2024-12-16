package LoginPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Avatar;
import MenuPage.menu;
import SignUpPage.signUp;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ChooseClassPage.chooseClass;

public class LoginPageController {
    private final Stage stage;
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final FlashQuestController flashQuestController;
    private final Text errorMessage;

    public LoginPageController(Stage stage, TextField usernameField, PasswordField passwordField, FlashQuestController flashQuestController, Text errorMessage) {
        this.stage = stage;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.errorMessage = errorMessage;
        this.flashQuestController = flashQuestController;
    }

    public void onLoginButtonClicked() {
        String username = usernameField.getText(); // email should be username
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            if (flashQuestController.logIn(username, password) != null) {
                Avatar avatar;
                // This is for the already chosen their avatar in their account
                if ((avatar = flashQuestController.getUser().getAvatarObject()) != null) {
                    menu MenuPage = new menu(stage, flashQuestController);
                    MenuPage.show();
                }
                else {
                    // Reuse the existing stage to transition smoothly
                    chooseClass classPage = new chooseClass(stage, flashQuestController);
                    classPage.show();
                    errorMessage.setVisible(false);
                }
            } else {
                errorMessage.setText("User was not found.");
                errorMessage.setVisible(true);
            }
        } else {
            errorMessage.setText("Fields cannot be empty.");
            errorMessage.setVisible(true);
        }
    }

    public void onSignUpButtonClicked() {
        // Pass the Stage to the SignUpPage to display it
        signUp signUpPage = new signUp(stage, flashQuestController); // Correct capitalization (SignUp -> signUp)
        signUpPage.show();  // Show the SignUpPage
    }
}