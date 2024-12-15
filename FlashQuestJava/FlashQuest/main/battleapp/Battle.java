package battleapp;

import QuestPage.questController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Battle extends Application {
    private Stage stage;
    public void start(Stage stage) {

        Font vcrFont = Font.loadFont(getClass().getResource("VCR-OSD-MONO.ttf").toExternalForm(), 130);
        // Load the background image
        Image image = new Image(getClass().getResource("classPage.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.35);
        imageView.setEffect(colorAdjust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(620);

        Image warrior = new Image(getClass().getResource("Warrior.png").toExternalForm());
        ImageView warriorView = new ImageView(warrior);
        warriorView.setFitWidth(217);
        warriorView.setFitHeight(457);

        Image tank = new Image(getClass().getResource("Tank.png").toExternalForm());
        ImageView tankView = new ImageView(tank);
        tankView.setFitWidth(217);
        tankView.setFitHeight(457);

        Image mage = new Image(getClass().getResource("Mage.png").toExternalForm());
        ImageView mageView = new ImageView(mage);
        mageView.setFitWidth(217);
        mageView.setFitHeight(457);

        Button chooseMage = new Button();
        chooseMage.setGraphic(mageView);
        chooseMage.getStyleClass().add("classes");

        Button chooseWarrior = new Button();
        chooseWarrior.setGraphic(warriorView);
        chooseWarrior.getStyleClass().add("classes");

        Button chooseTank = new Button();
        chooseTank.setGraphic(tankView);
        chooseTank.getStyleClass().add("classes");

        Text part1 = new Text("Choose a ");
        Text part2 = new Text("Class");
        TextFlow Title = new TextFlow(part1, part2);
        part1.setStyle("-fx-fill: white;");
        part2.setStyle("-fx-fill: #FFCF0E;");
        Title.getStyleClass().add("Title");

        Text part3 = new Text("You can ");
        Text part4 = new Text("only ");
        Text part5 = new Text("choose ");
        Text part6 = new Text("once");
        TextFlow description = new TextFlow(part3, part4, part5, part6);
        part3.setStyle("-fx-fill: white");
        part5.setStyle("-fx-fill: white");
        part4.setStyle("-fx-fill: #FFCF0E;");
        part6.setStyle("-fx-fill: #FF0000;");
        Title.getStyleClass().add("Title");
        description.getStyleClass().add("description");



        // Use a Pane for manual positioning
        Pane root = new Pane();
        root.getChildren().addAll(imageView, Title, description, chooseWarrior, chooseMage, chooseTank); //murag panel.add sa swing

        Title.layoutXProperty().bind(Bindings.multiply(0.3, root.widthProperty()));
        Title.layoutYProperty().bind(Bindings.multiply(0.0, root.heightProperty()));

        description.layoutXProperty().bind(Bindings.multiply(0.4, root.widthProperty()));
        description.layoutYProperty().bind(Bindings.multiply(0.15, root.heightProperty()));

        chooseWarrior.layoutXProperty().bind(Bindings.multiply(0.2, root.widthProperty()));
        chooseWarrior.layoutYProperty().bind(Bindings.multiply(0.2, root.heightProperty()));

        chooseMage.layoutXProperty().bind(Bindings.multiply(0.4, root.widthProperty()));
        chooseMage.layoutYProperty().bind(Bindings.multiply(0.2, root.heightProperty()));

        chooseTank.layoutXProperty().bind(Bindings.multiply(0.6, root.widthProperty()));
        chooseTank.layoutYProperty().bind(Bindings.multiply(0.2, root.heightProperty()));

        Scene scene = new Scene(root, 1280, 620);
        String css = this.getClass().getResource("chooseClass.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the stage
        stage.setTitle("FlashQuest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}