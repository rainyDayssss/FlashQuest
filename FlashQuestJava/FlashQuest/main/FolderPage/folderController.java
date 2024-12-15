package FolderPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import CreateSmithCard.smithCard;
import MenuPage.menu;
import QuestPage.quest;
import javafx.stage.Stage;

public class folderController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public folderController(Stage stage, FlashQuestController flashQuestController) {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }
    public void clickMenuButton() {
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }
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
    public void clickSelectButton() {
        smithCard SmithCard = new smithCard(stage, flashQuestController);
        SmithCard.show();
    }
    public void clickEditButton() {

    }
}