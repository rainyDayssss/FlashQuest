package LoginPage;

import Backend.Controller.FlashQuestController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;  // Use Pane for free positioning
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class loginPage {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public loginPage(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }
    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        // Load the background image
        Image image = new Image(getClass().getResource("/LoginPage/LoginPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(680);

        Text Title = new Text("FlashQuest");
        Title.getStyleClass().add("Title");

        Text email = new Text("Email");
        email.getStyleClass().add("email");
        email.setFont(vcrFont);

        TextField emailText = new TextField();
        emailText.setPromptText("Enter your username"); //Placeholder
        emailText.setPrefWidth(470);
        emailText.setPrefHeight(35);
        emailText.getStyleClass().add("placeholder");


        Text password = new Text("Password");
        password.getStyleClass().add("email");
        password.setFont(vcrFont);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password"); //Placeholder
        passwordField.setPrefWidth(470);
        passwordField.setPrefHeight(35);
        passwordField.getStyleClass().add("placeholder");

        Text errorMessage = new Text();
        errorMessage.getStyleClass().add("error-message");

        Button signUp = new Button("Sign up");
        signUp.getStyleClass().add("signUp");
        signUp.setFont(vcrFont);

        Button login = new Button(" Login ");
        login.getStyleClass().add("login");
        login.setFont(vcrFont);

        // Use a Pane for manual positioning
        Pane root = new Pane();
        root.getChildren().addAll(imageView, Title, email, emailText, password, passwordField, signUp, login, errorMessage); //murag panel.add sa swing

        Title.layoutXProperty().bind(Bindings.multiply(0.3, root.widthProperty()));
        Title.layoutYProperty().bind(Bindings.multiply(0.39, root.heightProperty()));

        email.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        email.layoutYProperty().bind(Bindings.multiply(0.23, root.widthProperty()));

        password.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        password.layoutYProperty().bind(Bindings.multiply(0.29, root.widthProperty()));

        emailText.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        emailText.layoutYProperty().bind(Bindings.multiply(0.24, root.widthProperty()));

        passwordField.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        passwordField.layoutYProperty().bind(Bindings.multiply(0.30, root.widthProperty()));

        login.layoutXProperty().bind(Bindings.multiply(0.54, root.widthProperty()));
        login.layoutYProperty().bind(Bindings.multiply(0.35, root.widthProperty()));

        signUp.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        signUp.layoutYProperty().bind(Bindings.multiply(0.35, root.widthProperty()));

        errorMessage.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        errorMessage.layoutYProperty().bind(Bindings.add(Bindings.multiply(0.30, root.heightProperty()), 85));

        LoginPageController controller = new LoginPageController(stage, emailText, passwordField, flashQuestController, errorMessage);
        signUp.setOnAction(e -> controller.onSignUpButtonClicked());
        login.setOnAction(e -> controller.onLoginButtonClicked());


        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("/LoginPage/loginPage.css").toExternalForm();
        scene.getStylesheets().add(css);


        // TODO logoh
        Image icon = new Image(getClass().getResource("/SignUpPage/final.png").toExternalForm());
        stage.getIcons().clear();
        stage.getIcons().add(icon);


        // Set up the stage
        stage.setTitle("FlashQuest - Log In");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}