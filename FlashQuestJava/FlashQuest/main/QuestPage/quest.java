package QuestPage;

import Backend.Controller.FlashQuestController;
import StartingPage.StartingPageController;
import javafx.application.Application;
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



public class quest {
    private Stage stage;
    private FlashQuestController flashQuestController;

    public quest(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }
    public void show() {
        questController controller = new questController(stage, flashQuestController);
        Font vcrFont = Font.loadFont(getClass().getResource("VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("QuestPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        //For the sidebar icon
        Image quest = new Image(getClass().getResource("Quest.png").toExternalForm());
        Image note = new Image(getClass().getResource("Note.png").toExternalForm());
        Image menu = new Image(getClass().getResource("Content.png").toExternalForm());
        Image user = new Image(getClass().getResource("User.png").toExternalForm());

        // Creator of Sidebar menu (VBox)
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");
        // Button items in the sidebar
        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        menuButton.getStyleClass().add("menu-button");
        Button smithCardButton = sideBarIcons("SmithFolder", note, 40, 40);
        smithCardButton.getStyleClass().add("menu-button");
        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        questButton.getStyleClass().add("menu-button");
        Button userButton = sideBarIcons("Zyche", user, 30, 40);
        userButton.getStyleClass().add("menu-button");

        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        // Add a spacer to push the userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        //Setting alignment for sideBar
        BorderPane root = new BorderPane();
        root.setLeft(sidebar); // Sidebar on the left
        root.getChildren().add(imageView); // Add the background image to the root

        VBox questLayout = new VBox(5);
        questLayout.setAlignment(Pos.TOP_CENTER);

        Text part1 = new Text("Choose a ");
        Text part2 = new Text("Quest");
        TextFlow title1 = new TextFlow(part1, part2);
        title1.setTranslateY(30); // Sets the text down a little bit
        title1.setTranslateX(140); // Sets the text to the center
        part1.setStyle("-fx-fill: white;");
        part2.setStyle("-fx-fill: #FFCF0E;");
        title1.getStyleClass().add("Title");
        questLayout.getChildren().add(title1);

        //Creates buttons based on how many the user has added folders
        int total = 15;
        for (int i=0; i < total; i++) {
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


        StackPane boxlayout = new StackPane(scrollPane);
        boxlayout.getChildren().addAll(questLayout);


        imageView.toBack();
        root.setCenter(boxlayout);


        // Scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("QuestPage.css").toExternalForm();
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
    //Responsible for showing the quest
    private HBox questShow(String name) {
        questController controller = new questController(stage, flashQuestController);
        Text text = new Text (name);
        text.getStyleClass().add("question");
        Button view = new Button(" View ");
        Button start = new Button(" Start ");
        start.setPrefWidth(200);
        start.setPrefHeight(40);
        view.setPrefWidth(200);
        view.setPrefHeight(40);
        start.getStyleClass().add("start-button");
        view.getStyleClass().add("view-button");

        start.setOnAction(e -> controller.clickStartButton());
        view.setOnAction(e -> controller.clickViewButton());

        HBox layout = new HBox(25, text, view, start);
        layout.setTranslateY(50);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 10;"); // Optional spacing around the box

        layout.getStyleClass().add("quest-layout");
        return layout;
    }

}
