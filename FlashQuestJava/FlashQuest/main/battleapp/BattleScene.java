package battleapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BattleScene extends Application {

    // Battle Scene UI Components
    private Canvas healthBarCanvas;
    private Canvas attackMeterCanvas;
    private TextField answerField;
    private Button submitButton;
    private Button retreatButton;
    private Label resultLabel;
    private Label questionLabel;
    private Label streakCounter;
    private ImageView background;
    private ImageView characterSprite;
    private ImageView enemySprite;

    // Question UI Components
    private Label questionLabelQ;
    private Label answerLabelQ;
    private Button editQuestionButton;

    @Override
    public void start(Stage primaryStage) {
        // Create the root AnchorPane for the entire scene
        AnchorPane root = new AnchorPane();

        // --- Battle Scene UI ---
        // Canvas for Health Bars
        healthBarCanvas = new Canvas(1256, 51);
        healthBarCanvas.setLayoutX(14);
        healthBarCanvas.setLayoutY(14);

        // Question Label
        questionLabel = new Label("Your question here");
        questionLabel.setLayoutX(298);
        questionLabel.setLayoutY(361);
        questionLabel.setPrefWidth(611);
        questionLabel.setPrefHeight(78);
        questionLabel.setFont(Font.font(24));
        questionLabel.setTextFill(Color.rgb(27, 26, 26));

        // Answer Field
        answerField = new TextField();
        answerField.setLayoutX(223);
        answerField.setLayoutY(457);
        answerField.setPrefWidth(265);
        answerField.setPrefHeight(43);
        answerField.setFont(Font.font(14));
        answerField.setEffect(new DropShadow(10, Color.rgb(28, 242, 35)));

        // Submit Button
        submitButton = new Button("Submit");
        submitButton.setLayoutX(371);
        submitButton.setLayoutY(531);
        submitButton.setPrefWidth(117);
        submitButton.setPrefHeight(43);
        submitButton.setOnAction(event -> handleSubmit());

        // Result Label
        resultLabel = new Label();
        resultLabel.setLayoutX(536);
        resultLabel.setLayoutY(457);
        resultLabel.setPrefWidth(665);
        resultLabel.setPrefHeight(120);
        resultLabel.setFont(Font.font(25));
        resultLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Retreat Button
        retreatButton = new Button("Retreat");
        retreatButton.setLayoutX(223);
        retreatButton.setLayoutY(531);
        retreatButton.setPrefWidth(117);
        retreatButton.setPrefHeight(43);
        retreatButton.setOnAction(event -> handleRetreat());

        // Canvas for Attack Meter
        attackMeterCanvas = new Canvas(1250, 43);
        attackMeterCanvas.setLayoutX(16);
        attackMeterCanvas.setLayoutY(65);

        // Streak Counter
        streakCounter = new Label();
        streakCounter.setLayoutX(230);
        streakCounter.setLayoutY(108);
        streakCounter.setPrefWidth(103);
        streakCounter.setPrefHeight(26);
        streakCounter.setFont(Font.font(14));

        // Background Image
        background = new ImageView();
        background.setFitWidth(1280);
        background.setFitHeight(600);
        background.setLayoutX(0);
        background.setLayoutY(0);

        // Character Sprite
        characterSprite = new ImageView();
        characterSprite.setFitWidth(150);
        characterSprite.setFitHeight(150);
        characterSprite.setLayoutX(183);
        characterSprite.setLayoutY(160);

        // Enemy Sprite
        enemySprite = new ImageView();
        enemySprite.setFitWidth(150);
        enemySprite.setFitHeight(150);
        enemySprite.setLayoutX(928);
        enemySprite.setLayoutY(160);

        // --- Question Scene UI ---
        // Label for "Your Smithcards"
        Label smithcardsLabel = new Label("Your Smithcards");
        smithcardsLabel.setLayoutX(26);
        smithcardsLabel.setLayoutY(23);
        smithcardsLabel.setPrefWidth(252);
        smithcardsLabel.setPrefHeight(47);
        smithcardsLabel.setFont(Font.font("System Bold Italic", 29));

        // Question Label
        questionLabelQ = new Label("Capital of the Philippines?");
        questionLabelQ.setLayoutX(258);
        questionLabelQ.setLayoutY(126);
        questionLabelQ.setPrefWidth(403);
        questionLabelQ.setPrefHeight(73);
        questionLabelQ.setFont(Font.font(16));

        // Answer Label
        answerLabelQ = new Label("Manila");
        answerLabelQ.setLayoutX(258);
        answerLabelQ.setLayoutY(240);
        answerLabelQ.setPrefWidth(403);
        answerLabelQ.setPrefHeight(73);
        answerLabelQ.setFont(Font.font(16));

        // Edit Question Button
        editQuestionButton = new Button("Edit Questions");
        editQuestionButton.setLayoutX(611);
        editQuestionButton.setLayoutY(385);
        editQuestionButton.setPrefWidth(78);
        editQuestionButton.setPrefHeight(38);
        editQuestionButton.setFont(Font.font(10));

        // --- Add all Components to the root Pane ---
        root.getChildren().addAll(
                // Battle Scene Components
                healthBarCanvas, questionLabel, answerField, submitButton, resultLabel,
                retreatButton, attackMeterCanvas, streakCounter, background, characterSprite, enemySprite,
                // Question Scene Components
                smithcardsLabel, questionLabelQ, answerLabelQ, editQuestionButton
        );

        // Create and set the scene

        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("/ChooseClassPage/chooseClass.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Battle and Question Scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method for handling submit button action
    private void handleSubmit() {
        resultLabel.setText("Answer Submitted");
    }

    // Method for handling retreat button action
    private void handleRetreat() {
        resultLabel.setText("You Retreated");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
