package FightPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import QuestPage.quest;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BattleSceneController {
    private Text questionText;  // Displays the current question
    private Label resultLabel;  // Displays the result after the user submits an answer
    private ProgressBar playerHealthBar;  // Shows the player's health
    private ProgressBar enemyHealthBar;  // Shows the enemy's health
    public Pane pane;  // The layout pane that holds all elements
    public ImageView characterSprite;  // The sprite for the player's character
    public ImageView enemySprite;  // The sprite for the enemy

    private int playerHealth = 100;  // Player's initial health
    private int enemyHealth = 100;  // Enemy's initial health
    private int attackDamage = 10;  // The damage dealt by the player with each correct answer
    private final int enemyDamage = 10;  // The damage dealt by the enemy for each incorrect answer
    public Button submitButton;  // The button for submitting the answer
    public Button retreatButton;  // The button for retreating or exiting the battle
    public TextField answerField;  // TextField for entering the answer
    public Text errorMessage;  // Displays error messages, like incorrect answers
    private int flashcardIndex = 0;
    private int correctFlashcardCount = 0;

    // TODO QUESTIONS
//    private String currentQuestion = "When did WW2 happen?"; // Example question
//    private String currentAnswer = "1939"; // Example answer

    // Initializes the battle scene with provided elements and the flashcard question
    public void initialize(Text questionText, Label resultLabel, ProgressBar playerHealthBar, ProgressBar enemyHealthBar, Pane pane,
                           ImageView characterSprite, ImageView enemySprite, Button submitButton, Button retreatButton,
                           TextField answerField, Text errorMessage, Flashcard flashcard) {
        this.questionText = questionText;
        this.resultLabel = resultLabel;
        this.playerHealthBar = playerHealthBar;
        this.enemyHealthBar = enemyHealthBar;
        this.characterSprite = characterSprite;
        this.enemySprite = enemySprite;
        this.pane = pane;
        this.submitButton = submitButton;
        this.retreatButton = retreatButton;
        this.answerField = answerField;
        this.errorMessage = errorMessage;

        // Set the initial question from the flashcard
        questionText.setText(flashcard.getQuestion());
    }

    // Handles the submit action when the player answers the question
    public void handleSubmit(String userAnswer, Button submitButton, Button retreatButton, TextField answerField, Text errorMessage, Folder folder) {
        // Load the attack and hit effect images for the animations
        Image attack1 = new Image(getClass().getResource("AttackEffect1.gif").toExternalForm());
        ImageView fighterAttack = new ImageView(attack1);

        // Load the hit effects for both the player and enemy
        Image hitEffect1 = new Image(getClass().getResource("HitEffect.gif").toExternalForm());
        ImageView hitEffect = new ImageView(hitEffect1);

        Image hitEffect2 = new Image(getClass().getResource("HitEffect.gif").toExternalForm());
        ImageView hitEffectFighter = new ImageView(hitEffect2);

        fighterAttack.setFitWidth(150);
        fighterAttack.setFitHeight(150);
        fighterAttack.setTranslateX(160); // Position the attack effect at the character's location
        fighterAttack.setTranslateY(150);

        // Set the size and position of the hit effect on the enemy
        hitEffect.setFitWidth(200);
        hitEffect.setFitHeight(200);
        hitEffect.setTranslateX(enemySprite.getTranslateX());
        hitEffect.setTranslateY(enemySprite.getTranslateY());
        hitEffectFighter.setFitWidth(200);
        hitEffectFighter.setFitHeight(200);
        hitEffectFighter.setTranslateX(enemySprite.getTranslateX());
        hitEffectFighter.setTranslateY(enemySprite.getTranslateY());

        // Ensure flashcardIndex is within bounds before accessing it
        if (flashcardIndex < folder.getAllFlashcards().size()) {
            Flashcard flashcard = folder.getFlashcardByFlashcardId(flashcardIndex);

            // 1. If the "Next" button is clicked, show the next question
            if (submitButton.getText().equals("Next")) {
                errorMessage.setText("");  // Clear the error message
                // Prepare for the next question
                answerField.clear(); // Clear the answer field
                submitButton.setText("Submit");  // Reset the button text to "Submit"
                flashcardIndex++; // Increment the flashcardIndex to the next card

                // Check if we're still within bounds after incrementing
                if (flashcardIndex < folder.getAllFlashcards().size()) {
                    flashcard = folder.getAllFlashcards().get(flashcardIndex);
                    questionText.setText(flashcard.getQuestion());  // Update the question
                    resultLabel.setText("");
                } else {
                    resultLabel.setText("\tYou have Completed all the questions!");  // Show win message if all questions are completed
                    disableActions(submitButton);  // Disable further actions
                    retreatButton.setText("Exit Battle");  // Change retreat button text to "Exit Battle"
                }
                return; // Exit after handling "Next"
            }

            // 2. If the "Submit" button is clicked, handle the user's answer
            if (userAnswer.trim().equalsIgnoreCase(flashcard.getAnswer().trim())) {
                correctFlashcardCount++;
                folder.addToCorrectFlashcards(flashcard);
                errorMessage.setText("");  // Clear any previous error messages
                // Correct answer: Add the attack and hit effect to the pane
                pane.getChildren().addAll(fighterAttack, hitEffect, hitEffectFighter);

                // TODO unique Ability
                if (correctFlashcardCount == 3) {
                    attackDamage += 100;
                }

                // Animate the character's attack and hit effect
                animateTackle(characterSprite, fighterAttack, 50, hitEffect, hitEffectFighter);
                enemyHealth -= attackDamage;  // Decrease enemy health
                updateHealthBars();  // Update the health bars
                resultLabel.setText("Correct! You dealt damage to the enemy.");  // Show correct answer message
                resultLabel.setStyle("-fx-text-fill: green;");  // Set the text color to green for correct answer
                submitButton.setText("Next");  // Change the button text to "Next"
                if (enemyHealth <= 0) {
                    resultLabel.setText("\tYou won the battle!");  // Show win message if enemy health reaches 0
                    disableActions(submitButton);  // Disable further actions
                    pane.getChildren().remove(enemySprite);  // Remove the enemy sprite from the screen
                    retreatButton.setText("Exit Battle");  // Change retreat button text to "Exit Battle"
                }
            } else {
                // If the answer is incorrect, handle the incorrect answer logic
                if (!userAnswer.isEmpty()) {
                    folder.addToWrongFlashcards(flashcard);
                    errorMessage.setText("");  // Clear the error message
                    submitButton.setText("Next");  // Change the button text to "Next"
                    animateTackle(enemySprite, null, -50, hitEffect, hitEffectFighter);  // Animate the enemy's attack
                    playerHealth -= enemyDamage;  // Decrease player health
                    updateHealthBars();  // Update health bars

                    resultLabel.setText("Incorrect! The correct answer was: " + flashcard.getAnswer());  // Show incorrect message
                    resultLabel.setStyle("-fx-text-fill: red;");  // Set the text color to red for incorrect answer

                    if (playerHealth <= 0) {
                        resultLabel.setText("       You lost the battle.");  // Show lost battle message if player health reaches 0
                        disableActions(submitButton);  // Disable further actions
                        pane.getChildren().remove(characterSprite);  // Remove the player sprite from the screen
                        retreatButton.setText("Exit Battle");  // Change retreat button text to "Exit Battle"
                    }
                } else {
                    errorMessage.setText("Enter an answer");  // Display error message if no answer was entered
                }
            }
        } else {
            resultLabel.setText("\tYou have Completed all the questions!");  // Show win message if all questions are answered
            disableActions(submitButton);  // Disable further actions
            retreatButton.setText("Exit Battle");  // Change retreat button text to "Exit Battle"
        }
    }


    // Handles the retreat action (either exiting the battle or going back to quest page)
    public void handleRetreat(Stage stage, FlashQuestController flashQuestController) {
        flashQuestController.getLevelObject().correctFlashcardsToExperience(correctFlashcardCount);
        quest Quest = new quest(stage, flashQuestController);  // Create a new quest page
        Quest.show();  // Show the quest page
    }

    // Updates the health bars for both the player and the enemy
    private void updateHealthBars() {
        playerHealthBar.setProgress(playerHealth / 100.0);  // Update the player health bar
        enemyHealthBar.setProgress(enemyHealth / 100.0);  // Update the enemy health bar
    }

    // Disables the submit button and other actions after the battle ends
    private void disableActions(Button submitButton) {
        // Disable question, result label, and submit button to prevent further actions
        questionText.setDisable(true);
        resultLabel.setDisable(true);
        this.submitButton.setDisable(true);
    }

    // Animates the character's movement and attack
    private void animateTackle(ImageView sprite, ImageView attackEffect, double moveBy, ImageView hitEffect, ImageView hitEffectFighter) {
        // Move the sprite forward with a short animation
        TranslateTransition moveForward = new TranslateTransition(Duration.seconds(0.1), sprite);
        moveForward.setByX(moveBy);

        // Move the sprite backward after the attack
        TranslateTransition moveBackward = new TranslateTransition(Duration.seconds(0.1), sprite);
        moveBackward.setByX(-moveBy);

        // If an attack effect exists, animate it as well
        if (attackEffect != null) {
            TranslateTransition effectMove = new TranslateTransition(Duration.seconds(0.18), attackEffect);
            effectMove.setByX(moveBy);

            // Animate the hit effect for the fighter after the attack
            TranslateTransition hitEffectMove = new TranslateTransition(Duration.seconds(0.18), hitEffectFighter);
            hitEffectMove.setByX(moveBy);

            TranslateTransition hitEffect1 = new TranslateTransition(Duration.seconds(0.3), hitEffect);
            hitEffectMove.setByX(moveBy);

            // Remove the initial hitEffect immediately
            pane.getChildren().remove(hitEffect);

            // Play the effect animation
            moveForward.setOnFinished(e -> {
                effectMove.play(); // Play the attack effect move
                moveBackward.play(); // Play the sprite's backward animation after the effect

                // Ensure hitEffectFighter is visible after the attack
                hitEffectFighter.setVisible(true);

                // Remove the attack effect after it finishes
                effectMove.setOnFinished(f -> {
                    pane.getChildren().remove(attackEffect); // Remove the attack effect after animation completes
                    hitEffectMove.play(); // Play the hit effect movement
                });
            });

            // Remove the hit effect (fighter) after it finishes moving
            hitEffectMove.setOnFinished(f -> {
                pane.getChildren().remove(hitEffectFighter); // Remove the hit effect fighter after animation completes
            });

        } else {
            // Play only sprite animation if no attack effect exists
            moveForward.setOnFinished(e -> moveBackward.play());
        }

        moveForward.play(); // Start moving forward
    }
}
