package QuestPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class editingQuest {
    private Stage stage;
    private FlashQuestController flashQuestController;
    private Folder folder;
    private Flashcard flashcard;

    public editingQuest(Stage stage, FlashQuestController flashQuestController, Folder folder, Flashcard flashcard) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
        this.folder = folder;
        this.flashcard = flashcard;
    }

    // TODO PROBLEM WITH HERE
    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("/QuestPage/QuestPage.gif").toExternalForm());

        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        Image quest = new Image(getClass().getResource("Quest.png").toExternalForm());
        Image note = new Image(getClass().getResource("Note.png").toExternalForm());
        Image menu = new Image(getClass().getResource("Content.png").toExternalForm());
        Image user = new Image(getClass().getResource("User.png").toExternalForm());

        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");

        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        menuButton.getStyleClass().add("menu-button");
        Button smithCardButton = sideBarIcons("SmithFolder", note, 40, 40);
        smithCardButton.getStyleClass().add("menu-button");
        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        questButton.getStyleClass().add("menu-button");
        Button userButton = sideBarIcons(flashQuestController.getUser().getUsername(), user, 30, 40);
        userButton.getStyleClass().add("menu-button");

        editingQuestController controller = new editingQuestController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 18px;");
        closeButton.setPrefSize(40, 40);
        closeButton.setOnAction(e -> closePage());

        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPrefWidth(500);
        topBar.setPrefHeight(40);
        topBar.setStyle("-fx-background-color: transparent;");


        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setTop(topBar);
        root.getChildren().add(imageView);
        imageView.toBack();

        Text part1 = new Text("Editing ");
        Text part2 = new Text("Smithcard");
        TextFlow title2 = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: white;");
        part2.setStyle("-fx-fill: #FFAE00;");
        title2.getStyleClass().add("Title");

        Text description = new Text("  This Smithcard will be saved to " + folder.getFolderName());
        description.getStyleClass().add("description2");

        Text question = new Text("Question");
        question.getStyleClass().add("question");

        Text answer = new Text("Answer");
        answer.getStyleClass().add("question");

        TextField questionField = new TextField(flashcard.getQuestion());
        questionField.setPrefWidth(390);
        questionField.setPrefHeight(35);
        questionField.getStyleClass().add("placeholder");

        TextField answerField = new TextField(flashcard.getAnswer());
        answerField.setPrefWidth(390);
        answerField.setPrefHeight(35);
        answerField.getStyleClass().add("placeholder");

        Button saveBtn = new Button(" Save ");
        saveBtn.getStyleClass().add("start-button");
        saveBtn.setPrefWidth(200);
        saveBtn.setPrefHeight(50);

        Text notificationText = new Text();
        notificationText.setVisible(false);

        saveBtn.setOnAction(e -> {
            if (!questionField.getText().isEmpty() && !answerField.getText().isEmpty()) {
                controller.clickSaveBtn(questionField.getText(), answerField.getText(), folder, flashcard);
                questionField.setText("");
                answerField.setText("");
            } else {
                notificationText.setText("Cannot save with empty question or answer!");
                notificationText.setStyle("-fx-fill: #FF0000; -fx-font-size: 16px; -fx-font-weight: bold;");
            }
            notificationText.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> notificationText.setVisible(false));
            pause.play();
        });

        Pane root1 = new Pane();
        root1.getChildren().addAll(title2, description, question, answer, questionField, answerField, saveBtn, notificationText);
        root.setCenter(root1);

        title2.layoutXProperty().bind(Bindings.multiply(0.24, root1.widthProperty()));
        title2.layoutYProperty().bind(Bindings.multiply(0.22, root1.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.30, root1.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.35, root1.heightProperty()));

        question.layoutXProperty().bind(Bindings.multiply(0.32, root1.widthProperty()));
        question.layoutYProperty().bind(Bindings.multiply(0.41, root1.heightProperty()));

        answer.layoutXProperty().bind(Bindings.multiply(0.32, root1.widthProperty()));
        answer.layoutYProperty().bind(Bindings.multiply(0.55, root1.heightProperty()));

        questionField.layoutXProperty().bind(Bindings.multiply(0.32, root1.widthProperty()));
        questionField.layoutYProperty().bind(Bindings.multiply(0.43, root1.heightProperty()));

        answerField.layoutXProperty().bind(Bindings.multiply(0.32, root1.widthProperty()));
        answerField.layoutYProperty().bind(Bindings.multiply(0.57, root1.heightProperty()));

        saveBtn.layoutXProperty().bind(Bindings.multiply(0.41, root1.widthProperty()));
        saveBtn.layoutYProperty().bind(Bindings.multiply(0.67, root1.heightProperty()));

        notificationText.layoutXProperty().bind(Bindings.multiply(0.35, root1.widthProperty()));
        notificationText.layoutYProperty().bind(Bindings.multiply(0.80, root1.heightProperty()));

        // Scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("QuestPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        stage.setTitle("FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Helper method to create a sidebar button with an image
    public Button sideBarIcons(String text, Image image, double height, double width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Button button = new Button(text);
        button.setGraphic(imageView);
        button.setPrefWidth(260);
        button.setPrefHeight(40);

        return button;
    }
    // Close the current page and navigate to another page (e.g., Menu page)
    private void closePage() {
        editQuest EditQuestPage = new editQuest(stage, flashQuestController, folder);
        EditQuestPage.show();
    }
}
