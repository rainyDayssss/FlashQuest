package MenuPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Folder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.control.ScrollPane;

public class menu{
    private Stage stage;
    private FlashQuestController flashQuestController;

    public menu(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;  // Store the Stage passed to the constructor
        this.flashQuestController = flashQuestController;
    }
    public void show() {
        // Load the background image
        Font vcrFont = Font.loadFont(getClass().getResource("VCR-OSD-MONO.ttf").toExternalForm(), 130);

        //Background fill
        Image image = new Image(getClass().getResource("MenuPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(750);

        //For the sidebar icon and profile
        Image quest = new Image(getClass().getResource("Quest.png").toExternalForm());
        Image note = new Image(getClass().getResource("Note.png").toExternalForm());
        Image menu = new Image(getClass().getResource("Content.png").toExternalForm());
        Image user = new Image(getClass().getResource("User.png").toExternalForm());
        Image health = new  Image(getClass().getResource("heart.png").toExternalForm());
        Image mana = new  Image(getClass().getResource("mana.png").toExternalForm());
        Image experience = new  Image(getClass().getResource("Experience.png").toExternalForm());
        Image defense = new  Image(getClass().getResource("shield.png").toExternalForm());
        Image attack = new  Image(getClass().getResource("attack.png").toExternalForm());

        // Sidebar menu (VBox)
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

        // TODO MENU BTNS
        menuController controller = new menuController(stage, flashQuestController);
        menuButton.setOnAction(e -> controller.clickMenuButton());
        smithCardButton.setOnAction(e -> controller.clickSmithFolderButton());
        questButton.setOnAction(e -> controller.clickQuestButton());
        userButton.setOnAction(e -> controller.clickUserButton());


        // Add a spacer to push the userButton to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        sidebar.getChildren().addAll(title, menuButton, smithCardButton, questButton, spacer, userButton);
        sidebar.setPrefWidth(333);

        // Root layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar); // Sidebar on the left
        root.getChildren().add(imageView); // Add the background image to the root

        // Ensure the background image stays behind the sidebar
        imageView.toBack();

        ProgressBar healthBar = new ProgressBar(1);
        healthBar.getStyleClass().add("health-bar");
        healthBar.setTranslateX(500);
        healthBar.setTranslateY(145);
        ProgressBar defenseBar = new ProgressBar(1);
        defenseBar.getStyleClass().add("defense-bar");
        defenseBar.setTranslateX(500);
        defenseBar.setTranslateY(185);
        ProgressBar attackBar = new ProgressBar(1);
        attackBar.getStyleClass().add("attack-bar");
        attackBar.setTranslateX(500);
        attackBar.setTranslateY(225);

        //Sets how much the attack, defense, and health player has
        int Health = flashQuestController.getStatsObject().getHealth();
        double Defense = flashQuestController.getStatsObject().getDefenseInPercent();
        int Attack = flashQuestController.getStatsObject().getAttack();

        //This variables determine how much the progress of the player
        double currentEXP = flashQuestController.getLevelObject().getCurrentExperience();
        double levelEXP = flashQuestController.getLevelObject().getExperienceNeededToLevelUp();
        int currentLevel = flashQuestController.getLevelObject().getCurrentLevel();
//        double req = 1.0;
//        currentEXP += 0.2;
        ProgressBar experienceBar = new ProgressBar(currentEXP / flashQuestController.getLevelObject().getExperienceNeededToLevelUp());
        experienceBar.getStyleClass().add("experience-bar");
        experienceBar.setTranslateX(500);
        experienceBar.setTranslateY(265);
        //Leveling System
//        if (currentEXP >= levelEXP) {
//            currentEXP -= levelEXP; // Carry over the remaining XP to the next level
//            req = levelEXP += 0.5;
//            currentLevel++;
//            Health+=15;
//            Defense+=10;
//            Attack+=5;
//            experienceBar.setProgress(0); // Reset progress bar// Increase the experience requirement for the next level
//        }
//        else
//            experienceBar.setProgress(currentEXP / levelEXP);  // This expression set the progress or update the progress.

        Text experienceTotal = new Text (currentEXP + "/" + flashQuestController.getLevelObject().getExperienceNeededToLevelUp());
        experienceTotal.getStyleClass().add("total-info");
        experienceTotal.setTranslateX(765);
        experienceTotal.setTranslateY(285);

        //Display the text of total each category
        Text healthTotal = new Text (Health+"");
        healthTotal.getStyleClass().add("total-info");
        healthTotal.setTranslateX(765);
        healthTotal.setTranslateY(165);
        Text defenseTotal = new Text (Defense * 100 +"%");
        defenseTotal.getStyleClass().add("total-info");
        defenseTotal.setTranslateX(765);
        defenseTotal.setTranslateY(205);
        Text attackTotal = new Text (Attack+"");
        attackTotal.getStyleClass().add("total-info");
        attackTotal.setTranslateX(765);
        attackTotal.setTranslateY(245);

        //Info of the user
        Text userName = new Text (flashQuestController.getUser().getUsername());
        userName.getStyleClass().add("info");
        userName.setTranslateX(450);
        userName.setTranslateY(70);
        Text userClass = new Text ("Class:" + flashQuestController.getUser().getAvatarObject().getAvatarName());
        userClass.getStyleClass().add("info");
        userClass.setTranslateX(450);
        userClass.setTranslateY(100);
        Text userLevel = new Text ("Level: "+ flashQuestController.getLevelObject().getCurrentLevel());
        userLevel.getStyleClass().add("info");
        userLevel.getStyleClass().add("info");
        userLevel.setTranslateX(450);
        userLevel.setTranslateY(130);
        ImageView healthView = new ImageView(health);
        healthView.setTranslateX(450);
        healthView.setTranslateY(140);
        healthView.setFitWidth(40);
        healthView.setFitHeight(40);
        ImageView defenseView = new ImageView(defense);
        defenseView.setTranslateX(412);
        defenseView.setTranslateY(147);
        defenseView.setFitWidth(230);
        defenseView.setFitHeight(150);
        ImageView attackView = new ImageView(attack);
        attackView.setTranslateX(447);
        attackView.setTranslateY(215);
        attackView.setFitWidth(50);
        attackView.setFitHeight(50);
        ImageView experienceView = new ImageView(experience);
        experienceView.setTranslateX(454);
        experienceView.setTranslateY(260);
        experienceView.setFitWidth(30);
        experienceView.setFitHeight(30);

        //for the userIcon or pfp based on what class the user has chosen
        //example "1" represents as a "Warrior"

        // TODO here is the identifier
        ImageView userPfp = userIcon(flashQuestController.getUser().getAvatarObject().getAvatarName().toLowerCase());

        VBox questLayout = new VBox(5);
        questLayout.setAlignment(Pos.TOP_CENTER);

        Text title1 = new Text("New Quest");
        title1.setTranslateY(20); // Sets the text down a little bit
        title1.setTranslateX(15); // Sets the text to the center
        title1.setStyle("-fx-fill: #FFCF0E;");
        title1.getStyleClass().add("Title");
        questLayout.getChildren().add(title1);

        //Creates buttons based on how many the user has added folders
        for (Folder folder : flashQuestController.getAllFolderWithWrongFlashcards()) {
            HBox questItem = questShow(folder);
            questLayout.getChildren().add(questItem);
            questItem.setFocusTraversable(false); // Prevent focus on questLayout
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(questLayout);
        scrollPane.setFitToWidth(true); // Ensure the VBox stretches to the ScrollPane's width
        scrollPane.setMaxSize(700, 300); // Constrain maximum size
        scrollPane.setFocusTraversable(false); // Prevent focus on questLayout
        scrollPane.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;"); // Optional styling
        scrollPane.getStyleClass().add("scroll-pane");


        StackPane boxlayout = new StackPane(scrollPane);
        boxlayout.getChildren().addAll(questLayout);
        boxlayout.setTranslateY(150); // Make the quest box go bottom

        imageView.toBack();

        //Layout for the profile text and icons
        Pane root1 = new Pane();
        root1.getChildren().addAll(attackTotal, healthTotal, defenseTotal, experienceTotal, healthBar, defenseBar, attackBar,experienceBar, userPfp, userName,userClass,userLevel, healthView, experienceView,defenseView,attackView);
        root.setCenter(root1);
        root.setCenter(boxlayout);

        StackPane combinedLayout = new StackPane();
        combinedLayout.getChildren().addAll(boxlayout, root1); // Add both layouts to the StackPane
        root.setCenter(combinedLayout); // Set the combined layout as the center
        root1.setPickOnBounds(false); // It makes the button click or similar to ".toBack()" function since you can't click the buttons on the quest

        // Scene
        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("menuPage.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        stage.setTitle("Create a SmithCard");
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

    private HBox questShow(Folder folder) {
        menuController controller = new menuController(stage, flashQuestController);
        // Create a text node for the folder name
        Text text = new Text(folder.getFolderName());
        text.getStyleClass().add("question");

        // Create buttons
        Button view = new Button(" View ");
        Button start = new Button(" Start ");
        start.setPrefWidth(200);
        start.setPrefHeight(40);
        view.setPrefWidth(200);
        view.setPrefHeight(40);
        start.getStyleClass().add("start-button");
        view.getStyleClass().add("view-button");

        // Prevent focus on the buttons
        view.setFocusTraversable(false);
        start.setFocusTraversable(false);

        // TODO VIEW AND START FUNCTIONS BTN
        // Add button functionality
        view.setOnAction(e -> {
            // Handle "View" button click
            controller.clickViewButton(folder);
        });

        start.setOnAction(e -> {
            // Handle "Start" button click
            controller.clickStartButton(folder);
        });

        // Layout
        HBox layout = new HBox(25, text, view, start);
        layout.setTranslateY(50);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 10;"); // Optional spacing around the box
        layout.getStyleClass().add("quest-layout");

        return layout;
    }

    // TODO BE DYNAMIC, for battle scene
    private ImageView userIcon (String classes) {
        Image warrior = new Image(getClass().getResource("Warrior.gif").toExternalForm());
        Image mage = new Image(getClass().getResource("Mage.gif").toExternalForm());
        Image tank = new Image(getClass().getResource("Tank.gif").toExternalForm());

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
