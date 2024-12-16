package QuestPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import CreateFolderPage.createFolderPage;
import QuestPage.editQuest;
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
        // TODO HAGGAI
    }

    // TODO ADD BATTLE SCNECE LOGIC HERE
    public void clickStartButton(Folder folder) {

    }

    public void clickViewButton(Folder folder) {
        editQuest EditQuestPage = new editQuest(stage, flashQuestController, folder);
        EditQuestPage.show();
    }

    public void clickDeleteFolderBtn(Folder folder) {
        flashQuestController.deleteFolder(folder);
        quest QuestPage = new quest(stage, flashQuestController);
        QuestPage.show();
    }
}
