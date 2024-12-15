package ChooseClassPage;

import Backend.Controller.FlashQuestController;
import MenuPage.menu;
import javafx.stage.Stage;



public class chooseClassPageController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public chooseClassPageController(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }

    public void clickWarriorBtn() {
        // Warrior logic
        flashQuestController.chooseWarrior();
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }

    public void clickMageBtn() {
        flashQuestController.chooseMage();
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }

    public void clickTankBtn() {
        flashQuestController.chooseTank();
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }
}