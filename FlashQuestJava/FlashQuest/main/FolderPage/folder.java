package FolderPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import CreateSmithCard.smithCardController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.scene.input.MouseEvent;

public class folder {
    private Stage stage;
    private FlashQuestController flashQuestController;

    public folder(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }

    public void show() {
        Font vcrFont = Font.loadFont(getClass().getResource("VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("FlashCard.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        // For the sidebar icon
        Image quest = new Image(getClass().getResource("Quest.png").toExternalForm());
        Image note = new Image(getClass().getResource("Note.png").toExternalForm());
        Image menu = new Image(getClass().getResource("Content.png").toExternalForm());
        Image user = new Image(getClass().getResource("User.png").toExternalForm());

        // Creator of Sidebar menu (VBox)
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(300);

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");
        // Button items in the sidebar
        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        menuButton.getStyleClass().add("menu-button");
        Button smithCardButton = sideBarIcons("Smithcard", note, 40, 40);
        smithCardButton.getStyleClass().add("menu-button");
        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        questButton.getStyleClass().add("menu-button");
        Button userButton = sideBarIcons("Zyche", user, 30, 40);
        userButton.getStyleClass().add("menu-button");

        folderController controller = new folderController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        // Add a spacer to push the userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        // Add the "X" button for closing the current page
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 18px;");
        closeButton.setPrefSize(40, 40);
        closeButton.setOnAction(e -> closePage());

        // Position the "X" button at the top-right corner of the screen
        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPrefWidth(500);
        topBar.setPrefHeight(40);
        topBar.setStyle("-fx-background-color: transparent;");

        // Setting alignment for sideBar
        BorderPane root = new BorderPane();
        root.setTop(topBar); // Add the top bar with the close button
        root.setLeft(sidebar); // Sidebar on the left
        root.getChildren().add(imageView); // Add the background image to the root

        Text part1 = new Text("Edit ");
        Text part2 = new Text("Smithcards");
        TextFlow title1 = new TextFlow(part1, part2);
        title1.setTranslateY(30); // Sets the text down a little bit
        title1.setTranslateX(140); // Sets the text to the center
        part1.setStyle("-fx-fill: white;");
        part2.setStyle("-fx-fill: #FFCF0E;");
        title1.getStyleClass().add("Title");

        VBox questLayout = new VBox(0, title1);
        questLayout.setAlignment(Pos.TOP_CENTER);
        questLayout.setPrefWidth(300);

        // Creates buttons based on how many the user has added folders
        int total = 15;
        for (int i = 0; i < total; i++) {
            HBox questItem = questShow("Technology Quest ");
            questLayout.getChildren().add(questItem);
            questItem.setFocusTraversable(false); // Prevent focus on questLayout
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(questLayout);
        scrollPane.setFitToWidth(true); // Ensure the VBox stretches to the ScrollPane's width
        scrollPane.setMaxSize(700, 550); // Constrain maximum size
        scrollPane.setFocusTraversable(false); // Prevent focus on questLayout
        scrollPane.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;"); // Optional styling
        scrollPane.getStyleClass().add("scroll-pane");

        StackPane boxLayout = new StackPane(scrollPane);
        boxLayout.getChildren().addAll(questLayout);

        root.setCenter(boxLayout);
        imageView.toBack();

        // Scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("folderPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        stage.setTitle("Quest Page");
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
    private HBox questShow(String name) {
        folderController controller = new folderController(stage, flashQuestController);
        Text text = new Text(name);
        text.getStyleClass().add("question");
        Button edit = new Button(" Select ");
        Button select = new Button(" Edit ");
        edit.setPrefWidth(200);
        edit.setPrefHeight(40);
        edit.getStyleClass().add("edit-button");
        select.setPrefWidth(200);
        select.setPrefHeight(40);
        select.getStyleClass().add("select-button");
        select.setOnAction(e -> controller.clickEditButton());
        edit.setOnAction(e -> controller.clickSelectButton());

        HBox layout = new HBox(25, text, select, edit);
        layout.setTranslateY(50);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 15;"); // Optional spacing around the box

        layout.getStyleClass().add("quest-layout");
        return layout;
    }

    // Close the current page and navigate to another page (e.g., Menu page)
    private void closePage() {
        // You can create a new MenuPage instance here and show it
        createFolderPage createFolderPage = new createFolderPage(stage, flashQuestController);
        createFolderPage.show();
    }
}
