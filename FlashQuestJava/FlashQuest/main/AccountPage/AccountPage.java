package AccountPage;

import Backend.Controller.FlashQuestController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;

public class AccountPage
{
    private final Stage stage;
    private FlashQuestController flashQuestController;

    public AccountPage(Stage stage, FlashQuestController flashQuestController)
    {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }

    public void show()
    {
        Font vcrFont = Font.loadFont(getClass().getResource("/StartingPage/VCR-OSD-MONO.ttf").toExternalForm(), 25);
        if (vcrFont == null) {
            System.out.println("Font could not be loaded!");
        }

        //Import Images
        Image backgroundPic = loadImage("/AccountPage/Background.gif");
        Image firstBadgeLocked = loadImage("/AccountPage/1st_Badge.png");
        Image secondBadgeLocked = loadImage("/AccountPage/2nd_Badge.png");
        Image thirdBadgeLocked = loadImage("/AccountPage/3rd_Badge.png");
        Image fourthBadgeLocked = loadImage("/AccountPage/4th_Badge.png");
        Image fifthBadgeLocked = loadImage("/AccountPage/5th_Badge.png");
        Image sixthBadgeLocked = loadImage("/AccountPage/6th_Badge.png");
        Image seventhBadgeLocked = loadImage("/AccountPage/7th_Badge.png");
        Image eighthBadgeLocked = loadImage("/AccountPage/8th_Badge.png");
        Image menu = loadImage("/AccountPage/Content.png");
        Image note = loadImage("/AccountPage/Note.png");
        Image quest = loadImage("/AccountPage/Quest.png");
        Image user = loadImage("/AccountPage/User.png");

        ////////////////The waterfall background
        // Background image
        ImageView background = new ImageView(backgroundPic);
        background.setFitWidth(1280);       // Set width to match the scene
        background.setFitHeight(620);      // Set height to match the scene
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        background.setEffect(colorAdjust);
        background.setFitWidth(1280);
        background.setFitHeight(750);

        ////////////////Account area with the character avatar and account details
        // Character image
        ImageView Avatarpfp = userIcon(flashQuestController.getUser().getAvatarObject().getAvatarName().toLowerCase());
        Avatarpfp.setFitWidth(170);
        Avatarpfp.setFitHeight(170);
        Avatarpfp.setTranslateX(-165);
        Avatarpfp.setTranslateY(25);

        // Username Text
        Text userName = new Text (flashQuestController.getUser().getUsername());
        userName.setFont(vcrFont);
        userName.setFill(Color.LIGHTYELLOW);
        userName.setTranslateX(10);

        // Level Text
        Text userLevel = new Text ("Level: "+ flashQuestController.getLevelObject().getCurrentLevel());
        userLevel.getStyleClass().add("info");
        userLevel.getStyleClass().add("info");
        userLevel.setFont(vcrFont);
        userLevel.setFill(Color.LIGHTYELLOW);
        userLevel.setTranslateX(10);

        Text userClass = new Text ("Class:" + flashQuestController.getUser().getAvatarObject().getAvatarName());
        userClass.getStyleClass().add("info");
        userClass.setFill(Color.LIGHTGOLDENRODYELLOW);
        userClass.setTranslateX(10);

        // Account Area for username and level
        VBox accountArea = new VBox(20); // Spacing between elements
        accountArea.setAlignment(Pos.TOP_LEFT);
        accountArea.getChildren().addAll(userName, userLevel, userClass);
        accountArea.setTranslateX(575);  // Adjust horizontal position of account area
        accountArea.setTranslateY(50);  // Move account info lower to avoid overlap with title

        ////////////////The word "Achievements" and its black box background above the badges
        // Achievements title text
        Text achievementsTitle = new Text("Achievements");
        achievementsTitle.setFont(vcrFont);
        achievementsTitle.setFill(Color.LIGHTYELLOW);
        achievementsTitle.setTranslateY(241);  // Move it above the achievementsRectangle
        achievementsTitle.setTranslateX(720);

        // Black background for Achievements title
        Rectangle titleBackground = new Rectangle();
        titleBackground.setWidth(195);  // Width of the title background
        titleBackground.setHeight(40);  // Height of the title background
        titleBackground.setFill(Color.BLACK);  // Set background color to black
        titleBackground.setTranslateY(235);  // Align with the title text
        titleBackground.setTranslateX(710);

        ////////////////The Logout Button and Credit Button with its background
        Text logOut = new Text("Logout");
        logOut.setFont(vcrFont);
        logOut.setFill(Color.LIGHTYELLOW);

        // Create the black background rectangle
        Rectangle logOutRectangle = new Rectangle();
        logOutRectangle.setWidth(185);  // Width of the button background
        logOutRectangle.setHeight(40);  // Height of the button background
        logOutRectangle.setFill(Color.BLACK);  // Set background color to black

        // Combine the text and rectangle using a StackPane
        StackPane logOutContent = new StackPane();
        logOutContent.getChildren().addAll(logOutRectangle, logOut); // Add rectangle first, then text

        // Create the button and set the StackPane as its graphic
        Button logOutButton = new Button();
        logOutButton.setGraphic(logOutContent); // Use StackPane as the button's graphic
        logOutButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;"); // Transparent button background
        AccountPageController controller = new AccountPageController(stage, flashQuestController);
        logOutButton.setOnAction(e -> controller.clickLogoutButton());

        //Credits
        Text credits = new Text("Credits");
        credits.setFont(vcrFont);
        credits.setFill(Color.LIGHTYELLOW);

        // Black background for Credit Title
        Rectangle creditsRectangle = new Rectangle();
        creditsRectangle.setWidth(185);  // Width of the title background
        creditsRectangle.setHeight(40);  // Height of the title background
        creditsRectangle.setFill(Color.BLACK);  // Set background color to black

        StackPane creditsContent = new StackPane();
        creditsContent.getChildren().addAll(creditsRectangle, credits);

        // Create the button and set the StackPane as its graphic
        Button creditsButton = new Button();
        creditsButton.setGraphic(creditsContent); // Use StackPane as the button's graphic
        creditsButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;"); // Transparent button background
        creditsButton.setOnAction(e -> controller.clickCredits());

        // Group the buttons
        HBox buttonContainer = new HBox(15);
        buttonContainer.setTranslateY(10);
        buttonContainer.setMaxWidth(10);
        buttonContainer.getChildren().addAll(creditsButton, logOutButton);

        //////////////// Badge Area
        //The transparent rectangle behind the achievements
        Rectangle achievementsRectangle = new Rectangle();
        achievementsRectangle.setWidth(900);          // Set the width of the rectangle
        achievementsRectangle.setHeight(320);         // Set the height of the rectangle
        achievementsRectangle.setFill(Color.rgb(255, 255, 255, 0.40)); // White with transparency
        achievementsRectangle.setStroke(Color.TRANSPARENT);// No border
        achievementsRectangle.setTranslateY(125);
        achievementsRectangle.setTranslateX(165);
        achievementsRectangle.setMouseTransparent(true);

        // Convert the image to grayscale
        assert firstBadgeLocked != null;
        Image firstBadgeGrayscale = convertToGrayscale(firstBadgeLocked);
        assert secondBadgeLocked != null;
        Image secondBadgeGrayscale = convertToGrayscale(secondBadgeLocked);
        assert thirdBadgeLocked != null;
        Image thirdBadgeGrayscale = convertToGrayscale(thirdBadgeLocked);
        assert fourthBadgeLocked != null;
        Image fourthBadgeGrayscale = convertToGrayscale(fourthBadgeLocked);
        assert fifthBadgeLocked != null;
        Image fifthBadgeGrayscale = convertToGrayscale(fifthBadgeLocked);
        assert sixthBadgeLocked != null;
        Image sixthBadgeGrayscale = convertToGrayscale(sixthBadgeLocked);
        assert seventhBadgeLocked != null;
        Image seventhBadgeGrayscale = convertToGrayscale(seventhBadgeLocked);
        assert eighthBadgeLocked != null;
        Image eighthBadgeGrayscale = convertToGrayscale(eighthBadgeLocked);

        // Create ImageView for the grayscale badge
        ImageView firstBadge = new ImageView(firstBadgeGrayscale);
        firstBadge.setFitWidth(150);
        firstBadge.setFitHeight(150);

        // Create a button with the grayscale image and set its tooltip
        Button firstBadgeButton = createImageButton(firstBadgeGrayscale, 150, 150);
        firstBadgeButton.setTooltip(new Tooltip("First Medal, Locked\nMust correctly answer 5 Smith Cards in a row"));
        firstBadgeButton.setOnAction(e -> System.out.println("First Badge"));
        firstBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView secondBadge = new ImageView(secondBadgeGrayscale);
        firstBadge.setFitWidth(150);
        firstBadge.setFitHeight(150);

        Button secondBadgeButton = createImageButton(secondBadgeGrayscale, 150, 150);
        secondBadgeButton.setTooltip(new Tooltip("Second Medal, Locked\nMust correctly answer 20 Smith Cards in a row"));
        secondBadgeButton.setOnAction(e -> System.out.println("Second Badge"));
        secondBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView thirdBadge = new ImageView(thirdBadgeGrayscale);
        thirdBadge.setFitWidth(150);
        thirdBadge.setFitHeight(150);

        Button thirdBadgeButton = createImageButton(thirdBadgeGrayscale, 150, 150);
        thirdBadgeButton.setTooltip(new Tooltip("Third Medal, Locked\nMust correctly answer 50 Smith Cards in a row"));
        thirdBadgeButton.setOnAction(e -> System.out.println("Third Badge"));
        thirdBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView fourthBadge = new ImageView(fourthBadgeGrayscale);
        fourthBadge.setFitWidth(150);
        fourthBadge.setFitHeight(150);

        Button fourthBadgeButton = createImageButton(fourthBadgeGrayscale, 150, 150);
        fourthBadgeButton.setTooltip(new Tooltip("Fourth Medal, Locked\nMust correctly answer 100 Smith Cards in a row"));
        fourthBadgeButton.setOnAction(e -> System.out.println("Fourth Badge"));
        fourthBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));


        ImageView fifthBadge = new ImageView(fifthBadgeGrayscale);
        fifthBadge.setFitWidth(150);
        fifthBadge.setFitHeight(150);

        Button fifthBadgeButton = createImageButton(fifthBadgeGrayscale, 150, 150);
        fifthBadgeButton.setTooltip(new Tooltip("Fifth Medal, Locked\nMust login for the 2nd time"));
        fifthBadgeButton.setOnAction(e -> System.out.println("Fifth Badge"));
        fifthBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView sixthBadge = new ImageView(sixthBadgeGrayscale);
        sixthBadge.setFitWidth(150);
        sixthBadge.setFitHeight(150);

        Button sixthBadgeButton = createImageButton(sixthBadgeGrayscale, 150, 150);
        sixthBadgeButton.setTooltip(new Tooltip("Sixth Medal, Locked\nMust create a total of 50 smith cards"));
        sixthBadgeButton.setOnAction(e -> System.out.println("Sixth Badge"));
        sixthBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView seventhBadge = new ImageView(seventhBadgeGrayscale);
        seventhBadge.setFitWidth(150);
        seventhBadge.setFitHeight(150);

        Button seventhBadgeButton = createImageButton(seventhBadgeGrayscale, 150, 150);
        seventhBadgeButton.setTooltip(new Tooltip("Seventh Medal, Locked\nMust create a total of 500 smith cards"));
        seventhBadgeButton.setOnAction(e -> System.out.println("Seventh Badge"));
        seventhBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        ImageView eigthBadge = new ImageView(eighthBadgeGrayscale);
        eigthBadge.setFitWidth(150);
        eigthBadge.setFitHeight(150);

        Button eigthBadgeButton = createImageButton(eighthBadgeGrayscale, 150, 150);
        eigthBadgeButton.setTooltip(new Tooltip("Eigth Medal, Locked\nMust find the hidden button"));
        eigthBadgeButton.setOnAction(e -> System.out.println("Eigth Badge"));
        eigthBadgeButton.getTooltip().setShowDelay(Duration.seconds(0.1));

        // Create HBox for horizontal arrangement of badges
        HBox firstRowBadges = new HBox(20); // 20px spacing between badges
        firstRowBadges.setAlignment(Pos.CENTER); // Center badges in the first row
        firstRowBadges.setTranslateX(100);

        HBox secondRowBadges = new HBox(20);
        secondRowBadges.setAlignment(Pos.CENTER); // Center badges in the second row
        secondRowBadges.setTranslateX(100);

        firstRowBadges.getChildren().addAll(firstBadgeButton, secondBadgeButton, thirdBadgeButton, fourthBadgeButton);
        secondRowBadges.getChildren().addAll(fifthBadgeButton, sixthBadgeButton, seventhBadgeButton, eigthBadgeButton);

        VBox badgeContainer = new VBox(10); // 20px vertical spacing
        badgeContainer.setAlignment(Pos.CENTER);
        badgeContainer.setTranslateY(130);
        badgeContainer.setMaxWidth(10);
        badgeContainer.setTranslateX(80);
        badgeContainer.getChildren().addAll(firstRowBadges, secondRowBadges);

        //////////////// Side Bar Area
        // Sidebar menu (VBox)
        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");

        Text title = new Text("FlashQuest");
        title.getStyleClass().add("Title");

        // Menu items
        Button menuButton = sideBarIcons("Menu", menu, 40, 40);
        menuButton.getStyleClass().add("menu-button");

        Button smithCardButton = sideBarIcons("Smithcard", note, 40, 40);
        smithCardButton.getStyleClass().add("menu-button");

        Button questButton = sideBarIcons("Quest", quest, 40, 40);
        questButton.getStyleClass().add("menu-button");

        Button userButton = sideBarIcons(flashQuestController.getUser().getUsername(), user, 30, 40);
        userButton.getStyleClass().add("menu-button");

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

        // Create a layout and add all elements to StackPane
        StackPane root = new StackPane();
        root.getChildren().add(background);
        root.getChildren().add(Avatarpfp);
        root.getChildren().add(accountArea);
        root.getChildren().add(titleBackground);
        root.getChildren().add(achievementsTitle);
        root.getChildren().add(buttonContainer);
        root.getChildren().add(achievementsRectangle);
        root.getChildren().add(badgeContainer);
        root.getChildren().add(sidebar);

        //Alignment
        StackPane.setAlignment(Avatarpfp, Pos.TOP_CENTER);
        StackPane.setAlignment(accountArea, Pos.TOP_LEFT);
        StackPane.setAlignment(titleBackground, Pos.TOP_LEFT);
        StackPane.setAlignment(achievementsTitle, Pos.TOP_LEFT);
        StackPane.setAlignment(sidebar, Pos. TOP_LEFT);
        StackPane.setAlignment(buttonContainer, Pos.TOP_RIGHT);
        StackPane.setAlignment(achievementsRectangle, Pos.CENTER);
        StackPane.setAlignment(badgeContainer, Pos.CENTER);

        // Create and set the Scene
        Scene scene = new Scene(root, 1280, 620); // Scene size
        String css = (this.getClass().getResource("/AccountPage/menuPage.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Ducay's FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Image convertToGrayscale(Image originalImage)
    {
        if (originalImage == null)
            throw new IllegalArgumentException("The provided image is null.");

        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight();

        WritableImage grayscaleImage = new WritableImage(width, height);
        PixelReader pixelReader = originalImage.getPixelReader();
        PixelWriter pixelWriter = grayscaleImage.getPixelWriter();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // Read the pixel color
                javafx.scene.paint.Color color = pixelReader.getColor(x, y);

                // Compute the grayscale value
                double gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                // Create a grayscale color
                javafx.scene.paint.Color grayscaleColor = new javafx.scene.paint.Color(gray, gray, gray, color.getOpacity());

                // Write the grayscale color to the new image
                pixelWriter.setColor(x, y, grayscaleColor);
            }
        }

        return grayscaleImage;
    }

    private Button createImageButton(Image image, double width, double height)
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent;"); // Transparent background
        return button;
    }
    private Button sideBarIcons(String text, Image image, double height, double width)
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Button button = new Button(text);
        button.setGraphic(imageView);
        button.setPrefWidth(260);
        button.setPrefHeight(40);

        return button;
    }
    private Image loadImage(String path) {
        URL imageUrl = getClass().getResource(path);
        if (imageUrl == null) {
            System.out.println("Image not found: " + path);
            return null; // Or return a default image if you prefer
        }
        return new Image(imageUrl.toExternalForm());
    }
    private ImageView userIcon (String classes) {
        Image warrior = new Image(getClass().getResource("/AccountPage/Warrior.gif").toExternalForm());
        Image mage = new Image(getClass().getResource("/AccountPage/Mage.gif").toExternalForm());
        Image tank = new Image(getClass().getResource("/AccountPage/Tank.gif").toExternalForm());

        if (classes.equals("warrior")) {
            ImageView warriorView = new ImageView(warrior);
            warriorView.setTranslateX(220);
            warriorView.setTranslateY(30);
            warriorView.setFocusTraversable(false); // Prevent focus on questLayout
            return warriorView;
        }
        else if (classes.equals("mage")) {
            ImageView mageView = new ImageView(mage);
            mageView.setTranslateX(200);
            mageView.setTranslateY(30);
            mageView.setFocusTraversable(false); // Prevent focus on questLayout
            return mageView;
        }
        else {
            ImageView tankView = new ImageView(tank);
            tankView.setTranslateX(195);
            tankView.setTranslateY(15);
            tankView.setFitWidth(250);
            tankView.setFitHeight(250);
            tankView.setFocusTraversable(false); // Prevent focus on questLayout
            return tankView;
        }
    }
}
