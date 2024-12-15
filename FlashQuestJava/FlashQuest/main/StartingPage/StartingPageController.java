package StartingPage;

import Backend.Config.AppConfig;
import Backend.Controller.FlashQuestController;
import LoginPage.loginPage;
import SignUpPage.signUp;
import javafx.stage.Stage;

public class StartingPageController {
    private Stage stage;
    private final FlashQuestController flashQuestController;

    // Constructor to pass the Stage
    public StartingPageController(Stage stage) {
        this.stage = stage;
        flashQuestController = AppConfig.createController();
    }

    // Method to handle SignUp button click
    public void onSignUpButtonClicked() {
        // Pass the Stage to the SignUpPage to display it
        signUp signUpPage = new signUp(stage, flashQuestController); // Correct capitalization (SignUp -> signUp)
        signUpPage.show();  // Show the SignUpPage
    }

    // Method to handle Login button click
    public void onLoginButtonClicked() {
        // Add your logic for login
        loginPage loginPage1 = new loginPage(stage, flashQuestController); // Correct capitalization (SignUp -> signUp)
        loginPage1.show();  // Show the SignUpPage
    }

}
