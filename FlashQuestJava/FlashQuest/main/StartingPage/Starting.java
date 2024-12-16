package StartingPage;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;  // Use Pane for free positioning
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

// TODO populate folder and flashcard data
// TODO add folder
// TODO add flashcard
// TODO view and edit btm
public class Starting extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Initializing constructor for controller
        StartingPageController controller = new StartingPageController(stage);
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);

        // Load the background image
        Image image = new Image(getClass().getResource("StartingPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(620);

        Text Title = new Text("FlashQuest");
        Title.getStyleClass().add("Title");

        Text part1 = new Text("FlashQuest ");
        Text part2 = new Text("is a unique flashcard app that transforms learning into an epic RPG adventure!");
        TextFlow description = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: #FFCF0E");
        part2.setStyle("-fx-fill: white");
        description.getStyleClass().add("description");

        Button signUp = new Button("Sign up");
        signUp.getStyleClass().add("signUp");

        Button login = new Button(" Login ");
        login.getStyleClass().add("login");
        // Use a Pane for manual positioning
        Pane root = new Pane();
        root.getChildren().addAll(imageView, Title, description, signUp, login); // Add image, title, and description

        Title.layoutXProperty().bind(Bindings.multiply(0.26, root.widthProperty()));
        Title.layoutYProperty().bind(Bindings.multiply(0.53, root.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.16, root.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.56, root.heightProperty()));

        signUp.layoutXProperty().bind(Bindings.multiply(0.33, root.widthProperty()));
        signUp.layoutYProperty().bind(Bindings.multiply(0.3, root.widthProperty()));

        login.layoutXProperty().bind(Bindings.multiply(0.53, root.widthProperty()));
        login.layoutYProperty().bind(Bindings.multiply(0.3, root.widthProperty()));

        //Button Clicked
        signUp.setOnAction(e -> controller.onSignUpButtonClicked());
        login.setOnAction(e -> controller.onLoginButtonClicked());


        // Apply the CSS to the scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("/StartingPage/startingPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        Image icon = new Image(getClass().getResource("/SignUpPage/final.png").toExternalForm());
        stage.getIcons().clear();
        stage.getIcons().add(icon);

        stage.setTitle("FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}