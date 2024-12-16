package FightPage;

import QuestPage.questController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Battle extends Application {
    private Stage stage;
    public void start(Stage stage) {

        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        // Load the background image
        Image image = new Image(getClass().getResource("fightScene.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(620);
        imageView.setTranslateY(-300);


        Text part1 = new Text("Choose a ");
        Text part2 = new Text("Class");
        TextFlow Title = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: white;");
        part2.setStyle("-fx-fill: #FFCF0E;");
        Title.getStyleClass().add("Title");

        Text part3 = new Text("You can ");
        Text part4 = new Text("only ");
        Text part5 = new Text("choose ");
        Text part6 = new Text("once");
        TextFlow description = new TextFlow(part3, part4, part5, part6);
        part3.setStyle("-fx-fill: white");
        part5.setStyle("-fx-fill: white");
        part4.setStyle("-fx-fill: #FFCF0E;");
        part6.setStyle("-fx-fill: #FF0000;");
        Title.getStyleClass().add("Title");
        description.getStyleClass().add("description");

        Button retreat = new Button(" Retreat ");
        Button submit = new Button(" Submit ");
        submit.setPrefWidth(200);
        submit.setPrefHeight(40);
        retreat.setPrefWidth(200);
        retreat.setPrefHeight(40);
        retreat.getStyleClass().add("retreat-button");
        submit.getStyleClass().add("submit-button");

        TextField answers = new TextField();
        answers.setTranslateX(270);
        answers.setTranslateY(400);
        answers.setPrefHeight(40);
        answers.setPrefWidth(400);
        answers.setPromptText("Enter your question ");
        answers.getStyleClass().add("placeholder");

        Text questions = new Text("When did the WW2 Happen?");
        questions.getStyleClass().add("question");
        HBox layoutAnswers = new HBox();
        layoutAnswers.setLayoutX(0); // Align to the left
        layoutAnswers.setLayoutY(300); // Position near the bottom
        layoutAnswers.setPrefWidth(1280); // Full width of the scene
        layoutAnswers.setPrefHeight(600); // Fixed height for the background
        layoutAnswers.setStyle("-fx-background-color: #364444;"); // Solid background color

// Add it to the root Pane
        layoutAnswers.toBack(); // Ensure it appears behind other elements if needed
        VBox layoutQuestions = new VBox(5, questions);
        layoutQuestions.setLayoutX(210); // Position the VBox horizontally
        layoutQuestions.setLayoutY(330); // Position it vertically
        layoutQuestions.setMaxWidth(880); // Set the max width for the VBox
        layoutQuestions.setPrefHeight(140); // Set preferred height for VBox
        layoutQuestions.setAlignment(Pos.CENTER);
        questions.setTextAlignment(TextAlignment.CENTER); // Center the text inside the Text node
        layoutQuestions.setStyle("-fx-background-color: #363234;"); // Solid background color


// Allow the Text to wrap within the VBox
        questions.setWrappingWidth(860); // Set the wrapping width (slightly less than the VBox width)
        // Use a Pane for manual positioning
        layoutAnswers.toBack(); // Ensure it appears behind other elements if needed

        Pane root = new Pane();
        root.getChildren().addAll(layoutAnswers, imageView, Title, description, layoutQuestions, answers);

        Title.layoutXProperty().bind(Bindings.multiply(0.3, root.widthProperty()));
        Title.layoutYProperty().bind(Bindings.multiply(0.0, root.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.4, root.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.15, root.heightProperty()));


        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("fightScene.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        stage.setTitle("FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}