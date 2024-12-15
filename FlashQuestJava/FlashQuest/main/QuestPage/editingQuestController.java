package QuestPage;

import Backend.Controller.FlashQuestController;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import CreateFolderPage.createFolderPage;
import MenuPage.menu;
import javafx.stage.Stage;

public class editingQuestController {
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public editingQuestController(Stage stage, FlashQuestController flashQuestController) {
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


    public void clickSaveBtn(String newQuestion, String newAnswer, Folder folder, Flashcard flashcard) {
        flashQuestController.editFlashcardByFolderIdAndFlashcardId(newQuestion, newAnswer, folder, flashcard);
        editingQuest EditingQuestPage = new editingQuest(stage, flashQuestController, folder, flashcard);
        EditingQuestPage.show();
    }
}
