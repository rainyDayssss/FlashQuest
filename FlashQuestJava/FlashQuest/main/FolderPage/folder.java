package FolderPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Folder;
import CreateFolderPage.createFolderPage;
import CreateSmithCard.smithCard;
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

public class folder {
    private Stage stage;
    private FlashQuestController flashQuestController;
    private Folder folder;

    public folder(Stage stage, FlashQuestController flashQuestController, Folder folder) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
        this.folder = folder;
    }

    public folder(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }

    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("/CreateFolderPage/FlashCard.gif").toExternalForm());
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

        folderController controller = new folderController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        // TODO Close button setup
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: #CC0000; -fx-text-fill: white; -fx-font-size: 18px;");
        closeButton.setPrefSize(40, 40);
        closeButton.setOnAction(e -> closePage());

        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPrefWidth(500);
        topBar.setPrefHeight(40);
        topBar.setStyle("-fx-background-color: transparent;");

        // Title setup
//        Text part1 = new Text("Edit ");
//        Text part2 = new Text("Smithcards");
//        TextFlow title1 = new TextFlow(part1, part2);
//        title1.setTranslateY(30);
//        title1.setTranslateX(140);
//        part1.setStyle("-fx-fill: white;");
//        part2.setStyle("-fx-fill: #FFCF0E;");
//        title1.getStyleClass().add("Title");

        VBox questLayout = new VBox(15); // Added spacing between folder items
        questLayout.setAlignment(Pos.TOP_CENTER);
        questLayout.setFillWidth(true); // Ensure children stretch to the VBox's width

        // Dynamically populate the VBox with folders
        for (Folder folder : flashQuestController.getAllFolders()) {
            HBox questItem = questShow(folder);
            questLayout.getChildren().add(questItem);
        }

        // ScrollPane setup
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(questLayout);
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
        String css = this.getClass().getResource("folderPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image(getClass().getResource("/SignUpPage/final.png").toExternalForm());
        stage.getIcons().clear();
        stage.getIcons().add(icon);

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

    // Responsible for showing the quest
    private HBox questShow(Folder folder) {
        folderController controller = new folderController(stage, flashQuestController);
        Text text = new Text(folder.getFolderName());
        text.setWrappingWidth(200);
        text.getStyleClass().add("question");

        Button select = new Button(" Select ");
        select.setPrefWidth(200);
        select.setPrefHeight(40);
        select.getStyleClass().add("select-button");


        // Set action for the Select button
        select.setOnAction(e -> {
            // Logic to add flashcards to the selected folder
            controller.clickSelectButton(folder);
        });


        HBox layout = new HBox(25, text, select);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 15;"); // Optional spacing around the box
        layout.getStyleClass().add("quest-layout");
        return layout;
    }

    // Close the current page and navigate to another page (e.g., Menu page)
    private void closePage() {
        if (folder != null) {
            smithCard SmithCard = new smithCard(stage, flashQuestController, folder);
            SmithCard.show();
        }
        else {
            createFolderPage createFolderPage = new createFolderPage(stage, flashQuestController);
            createFolderPage.show();
        }
    }
}
