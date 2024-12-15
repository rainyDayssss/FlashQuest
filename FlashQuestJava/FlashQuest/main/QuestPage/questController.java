package QuestPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import FolderPage.folder;
import MenuPage.menu;
import javafx.stage.Stage;

public class questController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public questController(Stage stage, FlashQuestController flashQuestController) {
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
    public void clickStartButton() {

    }

    public void clickViewButton() {
//        folder Folder = new folder(stage, flashQuestController, folder);
//        Folder.show();
    }
}
