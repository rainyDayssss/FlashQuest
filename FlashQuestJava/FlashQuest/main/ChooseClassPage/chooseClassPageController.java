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
        transitionToMenu();
    }

    public void clickMageBtn() {
        flashQuestController.chooseMage();
        transitionToMenu();
    }

    public void clickTankBtn() {
        flashQuestController.chooseTank();
        transitionToMenu();
    }

    private void transitionToMenu() {
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }
}