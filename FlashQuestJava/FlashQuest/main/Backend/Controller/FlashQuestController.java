package Backend.Controller;

import Backend.Model.*;
import Backend.Service.*;

import java.util.ArrayList;

public class FlashQuestController {
    private final UserService userService;
    private final AvatarService avatarService;
    private final FolderService folderService;
    private final BadgeService badgeSerice;
    private final BattleService battleService;
    private User user;

    public FlashQuestController(UserService userService, AvatarService avatarService, FolderService folderService, BadgeService badgeService, BattleService battleService) {
        this.userService = userService;
        this.avatarService = avatarService;
        this.folderService = folderService;
        this.badgeSerice = badgeService;
        this.battleService = battleService;
    }

    // if true then the user can log in, it the returns the User Object
    public User logIn(String username, String password) {
        user =  userService.logIn(username, password);
        userService.setUser(user); // and set the user object as the main source of actions
        avatarService.setUser(user);
        folderService.setUser(user); // and for the folder service also
        battleService.setUser(user);
        return user;
    }

    public void signUP(String email, String password, String username) {
        userService.signUp(email, password, username);
    }

    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUser() {
        return userService.getUser();
    }

    // Choosing of avatar
    public void chooseWarrior() {
        avatarService.chooseWarrior();
        folderService.setFolderStorage();
    }

    public void chooseMage() {
        avatarService.chooseMage();
        folderService.setFolderStorage();
    }

    public void chooseTank() {
        avatarService.chooseTank();
        folderService.setFolderStorage();
    }

    public Level getLevelObject() {
        return avatarService.getLevelObject();
    }

    public Ability getAbilityObject() {
        return avatarService.getAbilityObject();
    }

    public Stats getStatsObject() {
        return avatarService.getStatsObject();
    }

    public int getExperience() {
        return avatarService.getExperience();
    }

    public String getAbilityName() {
        return avatarService.getAbilityName();
    }

    public int getHealth() {
        return avatarService.getHealth();
    }

    public int getAttack() {
        return avatarService.getAttack();
    }

    public double getDefenseInPercent() {
        return avatarService.getDefenseInPercent();
    }

    // Creating flashcards and folders
    public void addFlashcardToDefaultFolder(String question, String answer) {
        folderService.addFlashcardToDefaultFolder(question, answer);
    }

    public void addFlashcardToFolderId(String question, String answer, int folderId) {
        folderService.addFlashcardToFolderByFolderId(question, answer, folderId);
    }

    public void addFolder(String folderName) {
        folderService.addFolder(folderName);
    }

    public ArrayList<Folder> getAllFolders() {
        return folderService.getAllFolders();
    }

    public ArrayList<Flashcard> getAllFlashcardsByFolderId(int folderId) {
        return folderService.getAllFlashcardsByFolderId(folderId);
    }

    public ArrayList<Flashcard> getAllFlashcardsByFolderName(String folderName) {
        return folderService.getAllFlashcardsByFolderName(folderName);
    }

    public ArrayList<Flashcard> getAllWrongFlashcardsByFolderId(int folderId) {
        return folderService.getAllWrongFlashcardsByFolderId(folderId);
    }

    public ArrayList<Flashcard> getAllCorrectFlashcardsByFolderId(int folderId) {
        return folderService.getAllCorrectFlashcardsByFolderId(folderId);
    }


    public ArrayList<Folder> getAllFolderWithWrongFlashcards() {
        return folderService.getAllFoldersWithWrongFlashcards();
    }
    // Start battle
    public void startQuest(int folderId) {
        battleService.startBattle(folderId);
    }


    public void deleteFlashcardByFolderIdAndFlashcardId(int flashcardId, int folderId) {
        folderService.deleteFlashcardByFolderIdAndFlashcardId(flashcardId, folderId);
    }

    // This uses object btw
    public void editFlashcardByFolderIdAndFlashcardId(String newQuestion, String newAnswer, Folder folder, Flashcard flashcard) {
        folderService.editFlashcardByFolderIdAndFlashcardId(newQuestion, newAnswer, folder, flashcard);
    }

    public void deleteFolder(Folder folder) {
        folderService.deleteFolder(folder);
    }
}
