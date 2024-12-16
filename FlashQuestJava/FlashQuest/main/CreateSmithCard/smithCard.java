package CreateSmithCard;

import Backend.Controller.FlashQuestController;
import Backend.Model.Folder;
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
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.awt.*;


public class smithCard{
    private Stage stage;
    private FlashQuestController flashQuestController;
    private Folder folder;

    public smithCard (Stage stage, FlashQuestController flashQuestController, Folder folder) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
        this.folder = folder;
    }
    public void show() {
        // Load the background image
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 130);
        Image image = new Image(getClass().getResource("/CreateFolderPage/FlashCard.gif").toExternalForm());

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

        // Sidebar menu (VBox)
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(300);

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

        smithCardController controller = new smithCardController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());

        // Add a spacer to push the userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);

        // Root layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar); // Sidebar on the left
        root.getChildren().add(imageView); // Add the background image to the root

        // Ensure the background image stays behind the sidebar
        imageView.toBack();

        Text part1 = new Text("Create a ");
        Text part2 = new Text("Smithcard");
        TextFlow title2 = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: white");
        part2.setStyle("-fx-fill: #FFAE00");
        title2.getStyleClass().add("Title");

//        Text description1 = new Text("Created Smithcard will be added to ");
//        description1.setStyle("-fx-fill: white");
//        Text description2 = new Text(folder.getFolderName());
//        description2.setStyle("-fx-fill: #FF0000");
//        TextFlow description = new TextFlow(description1, description2);
        // Text description = new Text("Created Smithcard will be added to" + description2);

        Text description = new Text("  This Smithcard will be added to " + folder.getFolderName());
        description.getStyleClass().add("description");

        Text question = new Text("Question");
        question.getStyleClass().add("question");

        Text answer = new Text("Answer");
        answer.getStyleClass().add("question");

        TextField questionField = new TextField();
        questionField.setPromptText("Enter your question ");
        questionField.setPrefWidth(390);
        questionField.setPrefHeight(35);
        questionField.getStyleClass().add("placeholder");

        TextField answerField = new TextField();
        answerField.setPromptText("Enter your answer ");
        answerField.setPrefWidth(390);
        answerField.setPrefHeight(35);
        answerField.getStyleClass().add("placeholder");

        Button createButton = new Button (" Create ");
        createButton.getStyleClass().add("create-button");
        createButton.setPrefWidth(200);
        createButton.setPrefHeight(50);

        Text notificationText = new Text();
        notificationText.setVisible(false);

        // TODO
        createButton.setOnAction(e -> {
            if (!questionField.getText().isEmpty() && !answerField.getText().isEmpty()) {
                notificationText.setText("Created Smithcard!");
                notificationText.setStyle("-fx-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;"); // Green text
                controller.clickCreateSmithcardbtn(questionField.getText(), answerField.getText(), folder);
                System.out.println("Created sc");
                questionField.setText("");
                answerField.setText("");
            }
            else { // color red
                notificationText.setText("Cannot create with empty question or answer!");
                notificationText.setStyle("-fx-fill: #FF0000; -fx-font-size: 16px; -fx-font-weight: bold;"); // Green text
            }
            notificationText.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> notificationText.setVisible(false));
            pause.play();
        });

        Button folderButton = new Button (" Choose Folder ");
        folderButton.getStyleClass().add("folder-button");
        folderButton.setPrefWidth(200);
        folderButton.setPrefHeight(50);

        Tooltip folderButtonTooltip = new Tooltip("Click to choose a folder \nand start creating Smithcards!");
        folderButtonTooltip.setShowDelay(Duration.seconds(0.1));
        Tooltip.install(folderButton, folderButtonTooltip);

        folderButton.setOnAction(e -> {
            controller.clickChooseFolder(folder);
        });


        Pane root1 = new Pane();
        root1.getChildren().addAll(title2, description, question, answer, questionField, answerField, folderButton, createButton, notificationText);
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

        createButton.layoutXProperty().bind(Bindings.multiply(0.29, root1.widthProperty()));
        createButton.layoutYProperty().bind(Bindings.multiply(0.67, root1.heightProperty()));

        folderButton.layoutXProperty().bind(Bindings.multiply(0.55 , root1.widthProperty()));
        folderButton.layoutYProperty().bind(Bindings.multiply(0.67, root1.heightProperty()));

        notificationText.layoutXProperty().bind(Bindings.multiply(0.38, root1.widthProperty()));
        notificationText.layoutYProperty().bind(Bindings.multiply(0.80, root1.heightProperty()));

        // Scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("createSmithCard.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image(getClass().getResource("/SignUpPage/final.png").toExternalForm());
        stage.getIcons().clear();
        stage.getIcons().add(icon);

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
}
