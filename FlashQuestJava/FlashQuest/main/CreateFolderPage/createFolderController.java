package CreateFolderPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import CreateSmithCard.smithCard;
import FolderPage.folder;
import MenuPage.menu;
import QuestPage.quest;
import javafx.stage.Stage;



public class createFolderController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public createFolderController(Stage stage, FlashQuestController flashQuestController) {
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
        // TODO HAGGAIIII
    }

    // CREATE FOLDER BTN
    public void clickCreateFolderButton(String folderName) {
        // stay on the same page
        System.out.println("Folder has been created");
        // TODO create folder
        flashQuestController.addFolder(folderName);
    }

    // CHOOSE FOLDER BTN
    public void clickFolderButton() {
        folder Folder = new folder(stage, flashQuestController);
        Folder.show();
    }
}