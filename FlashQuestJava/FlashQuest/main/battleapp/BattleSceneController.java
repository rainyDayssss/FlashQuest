package battleapp;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class BattleSceneController {

    @FXML
    private Canvas healthBarCanvas; // Canvas for drawing health bars

    @FXML
    private Canvas attackMeterCanvas;

    @FXML
    private TextField answerField;

    @FXML
    private Button submitButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Button retreatLabel;

    @FXML
    private Label streakCounter;

    @FXML
    private ImageView background;

    @FXML
    private ImageView characterSprite;

    @FXML
    private ImageView enemySprite;

    @FXML

    private int playerHealth = 100;
    private int enemyHealth = 100;
    private final int attackDamage = 10;
    private final int enemyDamage = 15;
    private final int correctAnswers = 0;
    FlashCardFolder card;

    private int consecutiveCorrectAnswers = 0;
    private int consecutiveIncorrectAnswers = 0;


    public void setFlashCardFolder(FlashCardFolder flashCardFolder) {
        this.card = flashCardFolder;
    }

    @FXML
    public void initialize() {
        // Initial drawing of health bars
        questionLabel.setText("Ready?");
        submitButton.setText("Yes");
        resultLabel.setText("");

        //Scene scene = submitButton.getScene(); // Assuming submitButton is part of the scene
        //scene.getStylesheets().add(getClass().getResource("battleTheme.css").toExternalForm());

        //background.setImage(new Image(getClass().getResource("/images/battle_background.png").toExternalForm()));

        // Load the GIFs for character and enemy
        characterSprite.setImage(new Image(getClass().getResource("/images/warrior.gif").toExternalForm()));
        enemySprite.setImage(new Image(getClass().getResource("/images/enemy.gif").toExternalForm()));
        drawHealthBars();
        handleAttack();
    }

    @FXML
    private void handleSubmit() {

        if (submitButton.getText().equals("Yes")) {
            card.nextFlashcard();
            questionLabel.setText(card.getCurrentQuestion());
            submitButton.setText("Submit");
            resultLabel.setText("");
            return;
        }

        String userAnswer = answerField.getText().trim();

        if(submitButton.getText().equals("Submit")){
            if (userAnswer.equalsIgnoreCase(card.getCurrentAnswer())) {
                // Correct answer
                enemyHealth -= attackDamage;
                resultLabel.setText("Correct!");
                resultLabel.setStyle("-fx-text-fill: #00ff00;");
                consecutiveCorrectAnswers++;
                consecutiveIncorrectAnswers = 0;

                if(submitButton.getText().equals("Submit"))
                    animateTackle(characterSprite, 50);
                else if (submitButton.getText().equals("Next"))
                    animateTackle(characterSprite, 0);

                if (consecutiveCorrectAnswers == 3) {
                    triggerUniqueAbility(); //unique ability to be implemented later
                    consecutiveCorrectAnswers = 0; // Reset after triggering
                }

            } else {
                // Incorrect answer
                playerHealth -= enemyDamage;
                resultLabel.setText("Incorrect!" + "\n" + "correct answer: " +card.getCurrentAnswer());
                resultLabel.setWrapText(true);
                resultLabel.setStyle("-fx-text-fill: #ff0000;");
                if(resultLabel.getText().equals("Incorrect!" + "\n" + "correct answer: " +card.getCurrentAnswer()))
                    animateTackle(enemySprite, -50);
                else
                    animateTackle(enemySprite, 0);

                consecutiveIncorrectAnswers++;
                consecutiveCorrectAnswers = 0;

                handleConsecutiveIncorrectAnswers();
            }

            submitButton.setText("Next");
            drawHealthBars();
        }
        else if (submitButton.getText().equals("Next")) {
            answerField.clear();
            card.nextFlashcard();
            questionLabel.setText(card.getCurrentQuestion());
            submitButton.setText("Submit");
        }
        //animateTackle(characterSprite, 50);

        streakCounter.setText("Streak: " + consecutiveCorrectAnswers + "");

        // Check for game over
        if (playerHealth <= 0) {
            resultLabel.setText("You lost!");
            disableInputs();
        } else if (enemyHealth <= 0) {
            retreatLabel.setText("Return");
            resultLabel.setText("You win!");
            disableInputs();
        }

    }

    @FXML
    public void handleRetreat(){
        retreatLabel.setOnAction(event -> Platform.exit());
    }

    private void drawHealthBars() {
        GraphicsContext gc = healthBarCanvas.getGraphicsContext2D();

        // Clear the canvas
        gc.clearRect(0, 0, healthBarCanvas.getWidth(), healthBarCanvas.getHeight());

        // Player Health Bar
        gc.setFill(Color.BLACK);
        gc.fillRect(100, 20, 210, 30); // Border
        gc.setFill(Color.LIMEGREEN);
        gc.fillRect(105, 25, playerHealth * 2, 20);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText("Player Health", 105, 18);

        // Enemy Health Bar
        gc.setFill(Color.BLACK);
        gc.fillRect(900, 20, 210, 30); // Border
        gc.setFill(Color.CRIMSON);
        gc.fillRect(905, 25, enemyHealth * 2, 20);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText("Enemy Health", 905, 18);
    }

    @FXML
    private void handleAttack(){

        GraphicsContext gc = attackMeterCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, attackMeterCanvas.getWidth(), attackMeterCanvas.getHeight());

        //Player attack Bar
        gc.setFill(Color.BLACK);
        gc.fillRect(100, 10, 210, 30); // Border
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(105, 15, playerHealth * 2, 20);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText("Attack " + attackDamage + "", playerHealth * 2 + 115, 30);

        // Enemy attack Bar
        gc.setFill(Color.BLACK);
        gc.fillRect(900, 10, 210, 30); // Border
        gc.setFill(Color.DEEPSKYBLUE);
        gc.fillRect(905, 15, enemyHealth * 2, 20);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText("Attack " + enemyDamage + "", playerHealth * 9 - 65, 30);


    }

    private void disableInputs() {
        answerField.setDisable(true);
        submitButton.setDisable(true);
    }

    private void triggerUniqueAbility() {
        resultLabel.setText("Correct!\nUnique Ability Activated!");
        // Add your unique ability implementation here.
        // Example: Massive attack on enemy
        enemyHealth -= 20;
        drawHealthBars();
    }

    private void handleConsecutiveIncorrectAnswers() {
        // Placeholder for future logic for wrong answer streak
        // Example: Temporary penalty, heal enemy, etc.
    }


    private void animateTackle(ImageView sprite, double moveBy) {
        TranslateTransition moveForward = new TranslateTransition(Duration.seconds(0.2), sprite);
        moveForward.setByX(moveBy);

        TranslateTransition moveBackward = new TranslateTransition(Duration.seconds(0.2), sprite);
        moveBackward.setByX(-moveBy);

        moveForward.setOnFinished(e -> moveBackward.play()); // Chain the animations
        moveForward.play();
    }
}
