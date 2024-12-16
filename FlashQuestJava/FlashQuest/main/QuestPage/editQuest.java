package QuestPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;  // Assuming Flashcard is the correct class for a flashcard
import Backend.Model.Folder;
import CreateFolderPage.createFolderPage;
import CreateSmithCard.smithCard;
import QuestPage.questController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class editQuest {
    private Stage stage;
    private FlashQuestController flashQuestController;
    private Folder folder;

    public editQuest(Stage stage, FlashQuestController flashQuestController, Folder folder) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
        this.folder = folder;
    }

    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("/QuestPage/QuestPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        // Sidebar setup
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(300);

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");

        Image quest = new Image(getClass().getResource("Quest.png").toExternalForm());
        Image note = new Image(getClass().getResource("Note.png").toExternalForm());
        Image menu = new Image(getClass().getResource("Content.png").toExternalForm());
        Image user = new Image(getClass().getResource("User.png").toExternalForm());

        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        Button smithCardButton = sideBarIcons("SmithFolder", note, 40, 40);
        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        Button userButton = sideBarIcons(flashQuestController.getUser().getUsername(), user, 30, 40);

        menuButton.getStyleClass().add("menu-button");
        smithCardButton.getStyleClass().add("menu-button");
        questButton.getStyleClass().add("menu-button");
        userButton.getStyleClass().add("menu-button");

        editQuestController controller = new editQuestController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        // Close button setup
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 18px;");
        closeButton.setPrefSize(40, 40);
        closeButton.setOnAction(e -> closePage());

        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPrefWidth(500);
        topBar.setPrefHeight(40);
        topBar.setStyle("-fx-background-color: transparent;");

        // Title setup
//        Text part1 = new Text("Manage ");
//        Text part2 = new Text("Flashcards");
//        TextFlow title1 = new TextFlow(part1, part2);
//        title1.setTranslateY(30);
//        title1.setTranslateX(140);
//        part1.setStyle("-fx-fill: white;");
//        part2.setStyle("-fx-fill: #FFCF0E;");
//        title1.getStyleClass().add("Title");

        VBox flashcardLayout = new VBox(15); // Added spacing between flashcards
        flashcardLayout.setAlignment(Pos.TOP_CENTER);
        flashcardLayout.setFillWidth(true); // Ensure children stretch to the VBox's width

        // Dynamically populate the VBox with flashcards
        for (Flashcard flashcard : flashQuestController.getAllFlashcardsByFolderId(folder.getId())) {
            HBox flashcardItem = flashcardShow(flashcard);
            flashcardLayout.getChildren().add(flashcardItem);
        }

        // ScrollPane setup
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flashcardLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(700, 550);
        scrollPane.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;");
        scrollPane.getStyleClass().add("scroll-pane");

        // Layout setup
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(scrollPane);
        root.getChildren().add(imageView);
        imageView.toBack();

        // Scene setup
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("QuestPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Helper method to create a sidebar button with an image
    private Button sideBarIcons(String text, Image image, double height, double width) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Button button = new Button(text);
        button.setGraphic(imageView);
        button.setPrefWidth(260);
        button.setPrefHeight(40);
        return button;
    }

    // Responsible for showing the flashcards
    private HBox flashcardShow(Flashcard flashcard) {
        editQuestController controller = new editQuestController(stage, flashQuestController);

        Text questionText = new Text("Q: " + flashcard.getQuestion());  // Assuming Flashcard has a getQuestion() method
        questionText.setWrappingWidth(300);
        questionText.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-weight: bold;");

        Text answerText = new Text("A: " + flashcard.getAnswer());  // Assuming Flashcard has a getAnswer() method
        answerText.setWrappingWidth(300);
        answerText.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-weight: bold;");

        VBox textBox = new VBox(10, questionText, answerText); // Display question and answer vertically
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button delete = new Button("Delete");
        delete.setPrefWidth(100);
        delete.setPrefHeight(40);
        delete.setStyle("-fx-text-fill: #FFFFFF; /* Light gray text */\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-padding: 10px 15px;\n" +
                "    -fx-border-radius: 5;\n" +
                "    -fx-background-radius: 5;\n" +
                "    -fx-border-color: transparent;\n" +
                "    -fx-background-color: #363E29;\n" +
                "    -fx-cursor: hand;");
        //delete.getStyleClass().add("select-button");

        Button edit = new Button("Edit");
        edit.setPrefWidth(100);
        edit.setPrefHeight(40);
        edit.setStyle(" -fx-text-fill: #FFFFFF; /* Light gray text */\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-padding: 10px 15px;\n" +
                "    -fx-border-radius: 5;\n" +
                "    -fx-background-radius: 5;\n" +
                "    -fx-border-color: transparent;\n" +
                "    -fx-background-color: #9FA18C;\n" +
                "    -fx-cursor: hand;");
        //edit.getStyleClass().add("edit-button");

        // Set action for the Delete button
        delete.setOnAction(e -> controller.clickDeleteButton(flashcard, folder));

        // Set action for the Edit button
        edit.setOnAction(e -> controller.clickEditButton(flashcard, folder));

        HBox layout = new HBox(25, textBox, edit, delete);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 15;"); // Optional spacing around the box
        layout.getStyleClass().add("quest-layout");
        return layout;
    }

        // Close the current page and navigate to another page (e.g., Menu page)
    private void closePage() {
        quest QuestPage = new quest(stage, flashQuestController);
        QuestPage.show();
    }
}
