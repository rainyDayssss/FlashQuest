package CreditsPage;

import Backend.Controller.FlashQuestController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class CreditsPage
{
    private Stage stage;
    private FlashQuestController flashQuestController;

    public CreditsPage(Stage stage, FlashQuestController flashQuestController)
    {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }

    public void show()
    {
        Font vcrFont = Font.loadFont(getClass().getResource("/CreditsPage/VCR-OSD-MONO.ttf").toExternalForm(), 28);

        // Image paths
        Image backgroundPic = new Image(Objects.requireNonNull(getClass().getResource("/CreditsPage/Background.gif")).toExternalForm());
        Image menu = new Image(Objects.requireNonNull(getClass().getResource("/CreditsPage/Content.png")).toExternalForm());
        Image note = new Image(Objects.requireNonNull(getClass().getResource("/CreditsPage/Note.png")).toExternalForm());
        Image quest = new Image(Objects.requireNonNull(getClass().getResource("/CreditsPage/Quest.png")).toExternalForm());
        Image user = new Image(Objects.requireNonNull(getClass().getResource("/CreditsPage/User.png")).toExternalForm());

        // Background Image
        ImageView background = new ImageView(backgroundPic);
        background.setFitWidth(1280);       // Set width to match the scene
        background.setFitHeight(620);      // Set height to match the scene
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        background.setEffect(colorAdjust);
        background.setFitWidth(1280);
        background.setFitHeight(750);

        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");

        // Menu items
        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        menuButton.getStyleClass().add("menu-button");

        Button smithCardButton = sideBarIcons("SmithFolder", note, 40, 40);
        smithCardButton.getStyleClass().add("menu-button");

        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        questButton.getStyleClass().add("menu-button");

        Button userButton = sideBarIcons(flashQuestController.getUser().getUsername(), user, 30, 40);
        userButton.getStyleClass().add("menu-button");

        CreditsPageController controller = new CreditsPageController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        // Add a spacer to push the userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Add elements to the sidebar
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);
        sidebar.setPrefWidth(333);
        sidebar.setMaxWidth(10);

        ////////////////Layout, Alignments and Scene
        BorderPane rootLayout = new BorderPane();
        rootLayout.setLeft(sidebar);

        /// ////////////Credits
        Text part1 = new Text("The Team behind");
        part1.setFont(vcrFont);
        part1.setFill(Color.WHITE);

        Text part2 = new Text("FlashQuest");
        part2.setFont(vcrFont);
        part2.setFill(Color.YELLOW);

        Text part3 = new Text("Group Leader");
        part3.setFont(vcrFont);
        part3.setFill(Color.WHITE);
        part3.setTranslateX(-315);
        part3.setTranslateY(17);

        Text talisic = new Text("Jhon Rosell Talisic");
        talisic.setFont(vcrFont);
        talisic.setFill(Color.WHITE);
        talisic.setTranslateX(418);
        talisic.setTranslateY(130);

        Text part4 = new Text("Developers");
        part4.setFont(vcrFont);
        part4.setFill(Color.WHITE);
        part4.setTranslateX(-330);
        part4.setTranslateY(25);

        Text ducay = new Text("Christian Kyle Ducay");
        ducay.setFont(vcrFont);
        ducay.setFill(Color.WHITE);
        ducay.setTranslateX(427);
        ducay.setTranslateY(-118);

        Text part5 = new Text("Haggai P. Estavilla");
        part5.setFont(vcrFont);
        part5.setFill(Color.WHITE);
        part5.setTranslateX(260);
        part5.setTranslateY(25);

        Text part6 = new Text("Jhon Rosell Talisic");
        part6.setFont(vcrFont);
        part6.setFill(Color.WHITE);
        part6.setTranslateX(260);
        part6.setTranslateY(25);

        Text part7 = new Text("Aaron Añabieza Bigno");
        part7.setFont(vcrFont);
        part7.setFill(Color.WHITE);
        part7.setTranslateX(270);
        part7.setTranslateY(25);

        Text part8 = new Text("And thank you to Sir Jewel Cedrick Gesim \nfor being our OOP teacher this semester");
        part8.setFont(vcrFont);
        part8.setFill(Color.WHITE);
        part8.setTranslateY(30);

        // Use a VBox to layout the text nodes
        VBox creditsTextLayout = new VBox(20); // Spacing between elements
        creditsTextLayout.setAlignment(Pos.TOP_CENTER); // Center align
        creditsTextLayout.setTranslateX(160);
        creditsTextLayout.setTranslateY(20);
        creditsTextLayout.getChildren().addAll(part1, part2, part3, part4, part5, part6, part7, part8);

        Text goBack = new Text("Back");
        goBack.setFont(vcrFont); // Set font and size
        goBack.setFill(Color.WHITE); // Set text color to white

        // Black background for Credit Title
        Rectangle backRectangle = new Rectangle();
        backRectangle.setWidth(185);  // Width of the title background
        backRectangle.setHeight(40);  // Height of the title background
        backRectangle.setFill(Color.BLACK);  // Set background color to black

        StackPane backContent = new StackPane();
        backContent.getChildren().addAll(backRectangle, goBack);

        // Create the button and set the StackPane as its graphic
        Button backButton = new Button();
        backButton.setGraphic(backContent); // Use StackPane as the button's graphic
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;"); // Transparent button background
        backButton.setTranslateX(150);
        backButton.setTranslateY(-40);
        backButton.setOnAction(e -> controller.clickBackButton());

        // Main Layout
        StackPane creditsLayout = new StackPane(background, sidebar, creditsTextLayout, ducay, talisic, backButton);
        StackPane.setAlignment(sidebar, Pos.TOP_LEFT);
        StackPane.setAlignment(creditsTextLayout, Pos.TOP_CENTER);
        StackPane.setAlignment(ducay, Pos.CENTER);
        StackPane.setAlignment(backButton, Pos. BOTTOM_CENTER);
        StackPane.setAlignment(talisic, Pos. TOP_CENTER);

        // Scene
        Scene scene = new Scene(creditsLayout, 1280, 620);
        String css = this.getClass().getResource("/CreditsPage/menuPage.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("FlashQuest");
        stage.setResizable(false);
        stage.show();
    }

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
}