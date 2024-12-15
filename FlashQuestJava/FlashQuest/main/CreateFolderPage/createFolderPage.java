package CreateFolderPage;

import Backend.Controller.FlashQuestController;
import MenuPage.menuController;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;  // Use Pane for free positioning
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class createFolderPage {
    private Stage stage;
    private FlashQuestController flashQuestController;

    public createFolderPage(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }

    public void show() {
        // Load the background image
        Font vcrFont = Font.loadFont(getClass().getResource("VCR-OSD-MONO.ttf").toExternalForm(), 130);

        Image image = new Image(getClass().getResource("FlashCard.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        // Sidebar menu (VBox)
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(300);

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");

        // Sidebar buttons with icons
        Button menuButton = sideBarIcons("Menu", new Image(getClass().getResource("Content.png").toExternalForm()), 40, 40);
        menuButton.getStyleClass().add("menu-button");
        Button smithCardButton = sideBarIcons("Smithcard", new Image(getClass().getResource("Note.png").toExternalForm()), 40, 40);
        smithCardButton.getStyleClass().add("menu-button");
        Button questButton = sideBarIcons("Quest", new Image(getClass().getResource("Quest.png").toExternalForm()), 40, 40);
        questButton.getStyleClass().add("menu-button");
        Button userButton = sideBarIcons("Zyche", new Image(getClass().getResource("User.png").toExternalForm()), 30, 40);
        userButton.getStyleClass().add("menu-button");

        // Attach controller actions
        createFolderController controller = new createFolderController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        // Add spacer to push userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        // Root layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.getChildren().add(imageView);
        imageView.toBack();

        // Main content elements
        Text part1 = new Text("Create a ");
        Text part2 = new Text("Folder");
        TextFlow title2 = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: white");
        part2.setStyle("-fx-fill: #FFAE00");
        title2.getStyleClass().add("Title");

        Text description = new Text("Create folder to store Smithcards");
        description.getStyleClass().add("description");

        Text question = new Text("Folder Name");
        question.getStyleClass().add("question");

        TextField questionField = new TextField();
        questionField.setPromptText("Enter your folder name ");
        questionField.setPrefWidth(390);
        questionField.setPrefHeight(35);
        questionField.getStyleClass().add("placeholder");

        Button createButton = new Button(" Create ");
        createButton.getStyleClass().add("create-button");
        createButton.setPrefWidth(200);
        createButton.setPrefHeight(50);

        Button folderButton = new Button(" Choose folder ");
        folderButton.getStyleClass().add("folder-button");
        folderButton.setPrefWidth(200);
        folderButton.setPrefHeight(50);

        Tooltip folderButtonTooltip = new Tooltip("Click to choose a folder \nand start creating Smithcards!");
        folderButtonTooltip.setShowDelay(Duration.seconds(0.1));
        Tooltip.install(folderButton, folderButtonTooltip);


        // Notification text
        Text notificationText = new Text();
        notificationText.setVisible(false);

        // Button actions
        createButton.setOnAction(e -> {
            if (questionField.getText().isEmpty()) {
                notificationText.setText("Cannot create with empty folder name!");
                notificationText.setStyle("-fx-fill: #FF0000; -fx-font-size: 16px; -fx-font-weight: bold;"); // Green text

            }
            else {
                notificationText.setText("Folder has been created successfully!");
                notificationText.setStyle("-fx-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;"); // Green text
                controller.clickCreateFolderButton(questionField.getText());
                questionField.setText("");
            }
            notificationText.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> notificationText.setVisible(false));
            pause.play();
        });
        folderButton.setOnAction(e -> controller.clickFolderButton());

        // Pane for central content
        Pane root1 = new Pane();
        root1.getChildren().addAll(title2, description, question, questionField, folderButton, createButton, notificationText);
        root.setCenter(root1);

        // Positioning
        title2.layoutXProperty().bind(Bindings.multiply(0.27, root1.widthProperty()));
        title2.layoutYProperty().bind(Bindings.multiply(0.28, root1.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.35, root1.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.4, root1.heightProperty()));

        question.layoutXProperty().bind(Bindings.multiply(0.30, root1.widthProperty()));
        question.layoutYProperty().bind(Bindings.multiply(0.47, root1.heightProperty()));

        questionField.layoutXProperty().bind(Bindings.multiply(0.30, root1.widthProperty()));
        questionField.layoutYProperty().bind(Bindings.multiply(0.50, root1.heightProperty()));

        createButton.layoutXProperty().bind(Bindings.multiply(0.26, root1.widthProperty()));
        createButton.layoutYProperty().bind(Bindings.multiply(0.62, root1.heightProperty()));

        folderButton.layoutXProperty().bind(Bindings.multiply(0.53, root1.widthProperty()));
        folderButton.layoutYProperty().bind(Bindings.multiply(0.62, root1.heightProperty()));

        notificationText.layoutXProperty().bind(Bindings.multiply(0.38, root1.widthProperty()));
        notificationText.layoutYProperty().bind(Bindings.multiply(0.80, root1.heightProperty())); // Positioned below the buttons

        // Scene setup
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("createFolder.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Stage setup
        stage.setTitle("Create a SmithCard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Helper method to create sidebar buttons with images
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
