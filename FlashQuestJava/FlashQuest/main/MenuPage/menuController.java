package MenuPage;

import AccountPage.AccountPage;
import Backend.Controller.FlashQuestController;
import Backend.Model.Folder;
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
        transitionToMenu();
    }

    public void clickSmithFolderButton() {
        transitionToCreateFolderPage();
    }

    public void clickQuestButton() {
        transitionToQuestPage();
    }

    public void clickUserButton() {
        // TODO HAGGAI
        AccountPage accountPage = new AccountPage(stage, flashQuestController);
        accountPage.show();
    }

    private void transitionToMenu() {
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }

    private void transitionToCreateFolderPage() {
        createFolderPage Smithfolder = new createFolderPage(stage, flashQuestController);
        Smithfolder.show();
    }

    private void transitionToQuestPage() {
        quest Quest = new quest(stage, flashQuestController);
        Quest.show();
    }

    // TODO ADD VIEW AND START IN THE NEW QUESTs
    public void clickViewButton(Folder folder) {

    }

    public void clickStartButton(Folder folder) {

    }
}
