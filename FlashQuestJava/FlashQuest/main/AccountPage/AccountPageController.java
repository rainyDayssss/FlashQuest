package AccountPage;

import Backend.Controller.FlashQuestController;
import CreateFolderPage.createFolderPage;
import LoginPage.loginPage;
import MenuPage.menu;
import QuestPage.quest;
import javafx.stage.Stage;
import CreditsPage.CreditsPage;

public class AccountPageController
{
    private final Stage stage;
    private final FlashQuestController flashQuestController;

    public AccountPageController(Stage stage, FlashQuestController flashQuestController)
    {
        this.stage = stage;
        this.flashQuestController = flashQuestController;
    }

    public void clickCredits()
    {
        CreditsPage credits = new CreditsPage(stage, flashQuestController);
        credits.show();
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

    public void clickLogoutButton()
    {
        loginPage login = new loginPage(stage, flashQuestController);
        login.show();
    }
}