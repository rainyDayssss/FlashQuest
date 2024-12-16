package QuestPage;

import AccountPage.AccountPage;
import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import CreateFolderPage.createFolderPage;
import MenuPage.menu;
import javafx.stage.Stage;

public class editQuestController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public editQuestController(Stage stage, FlashQuestController flashQuestController) {
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
        AccountPage accountPage = new AccountPage(stage, flashQuestController);
        accountPage.show();
    }


    public void clickDeleteButton(Flashcard flashcard, Folder folder) {
        // TODO delete logic
        flashQuestController.deleteFlashcardByFolderIdAndFlashcardId(flashcard.getId(), folder.getId());
        editQuest editQuestPage = new editQuest(stage, flashQuestController, folder);
        editQuestPage.show();
    }

    public void clickEditButton(Flashcard flashcard, Folder folder) {
        // TODO CREATE ANOTHER PAGE editingQuestPage
        editingQuest EditingQuestPage = new editingQuest(stage, flashQuestController, folder, flashcard);
        EditingQuestPage.show();
    }
}
