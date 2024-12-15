package MenuPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import QuestPage.quest;
import javafx.stage.Stage;

public class menuController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public menuController(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }
    public void clickMenuButton() {
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }

    // create smith folder and card
    public void clickSmithFolderButton() {
        createFolderPage Smithfolder = new createFolderPage(stage, flashQuestController);
        Smithfolder.show();
    }

    public void clickQuestButton() {
        quest Quest = new quest(stage, flashQuestController);
        Quest.show();
    }

    public void clickUserButton() {

    }
    public void clickStartButton() {

    }
    public void clickViewButton() {

    }
}
