package SignUpPage;

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

public class signUp {
    private Stage stage;
    private FlashQuestController flashQuestController;

    public signUp(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }
    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        // Load the background image
        Image image = new Image(getClass().getResource("signUp.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(680);

        Text Title = new Text("Sign up");
        Title.getStyleClass().add("Title");

        Text description = new Text("It won't take long and its free!");
        description.getStyleClass().add("description");

        Text userName = new Text("Username");
        userName.getStyleClass().add("email");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(470);
        usernameField.setPrefHeight(35);
        usernameField.getStyleClass().add("placeholder");

        Text email = new Text("Email");
        email.getStyleClass().add("email");

        TextField emailText = new TextField();
        emailText.setPromptText("Enter your email");
        emailText.setPrefWidth(470);
        emailText.setPrefHeight(35);
        emailText.getStyleClass().add("placeholder");

        Text password = new Text("Password");
        password.getStyleClass().add("email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(470);
        passwordField.setPrefHeight(35);
        passwordField.getStyleClass().add("placeholder");

        Button login = new Button(" Login ");
        login.getStyleClass().add("signUp");

        Button signUp = new Button("Sign up");
        signUp.getStyleClass().add("login");

        Text errorMessage = new Text();
        errorMessage.getStyleClass().add("error-message");
        errorMessage.setVisible(false);
        // Use a Pane for manual positioning
        Pane root = new Pane();
        root.getChildren().addAll(imageView, Title, description, userName, usernameField, email, emailText, password, passwordField, signUp, login, errorMessage); // Add image, title, and description

        Title.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        Title.layoutYProperty().bind(Bindings.multiply(0.27, root.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.31, root.heightProperty()));

        userName.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        userName.layoutYProperty().bind(Bindings.multiply(0.17, root.widthProperty()));
        usernameField.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        usernameField.layoutYProperty().bind(Bindings.multiply(0.18, root.widthProperty()));

        email.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        email.layoutYProperty().bind(Bindings.multiply(0.23, root.widthProperty()));
        emailText.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        emailText.layoutYProperty().bind(Bindings.multiply(0.24, root.widthProperty()));

        password.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        password.layoutYProperty().bind(Bindings.multiply(0.29, root.widthProperty()));
        passwordField.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        passwordField.layoutYProperty().bind(Bindings.multiply(0.30, root.widthProperty()));

        login.layoutXProperty().bind(Bindings.multiply(0.54, root.widthProperty()));
        login.layoutYProperty().bind(Bindings.multiply(0.35, root.widthProperty()));

        signUp.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        signUp.layoutYProperty().bind(Bindings.multiply(0.35, root.widthProperty()));

        errorMessage.layoutXProperty().bind(Bindings.multiply(0.34, root.widthProperty()));
        errorMessage.layoutYProperty().bind(Bindings.add(Bindings.multiply(0.30, root.heightProperty()), 255));

        SignUpController controller = new SignUpController(stage, emailText, usernameField, passwordField, flashQuestController, errorMessage);

        signUp.setOnAction(e -> controller.onSignUpButtonClicked());
        login.setOnAction(e -> controller.onLoginButtonClicked());


        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("/SignUpPage/signUpPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image(getClass().getResource("/SignUpPage/final.png").toExternalForm());
        stage.getIcons().clear();
        stage.getIcons().add(icon);

        // Set up the stage
        stage.setTitle("FlashQuest - Sign Up");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}