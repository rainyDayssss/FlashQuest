package CreditsPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import MenuPage.menu;
import QuestPage.quest;
import AccountPage.AccountPage;
import javafx.stage.Stage;

public class CreditsPageController
{
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public CreditsPageController(Stage stage, FlashQuestController flashQuestController)
    {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }

    public void clickBackButton()
    {
        AccountPage accountPage = new AccountPage(stage, flashQuestController);
        accountPage.show();
    }

    public void clickMenuButton()
    {
        menu Menu = new menu(stage, flashQuestController);
        Menu.show();
    }

    public void clickSmithFolderButton()
    {
        createFolderPage Smithfolder = new createFolderPage(stage, flashQuestController);
        Smithfolder.show();
    }

    public void clickQuestButton()
    {
        quest Quest = new quest(stage, flashQuestController);
        Quest.show();
    }

    public void clickUserButton()
    {
        AccountPage accountPage = new AccountPage(stage, flashQuestController);
        accountPage.show();
    }

    public void clickHiddenButton()
    {
        flashQuestController.setEighthBadgeUnlocked(true);
    }
}
