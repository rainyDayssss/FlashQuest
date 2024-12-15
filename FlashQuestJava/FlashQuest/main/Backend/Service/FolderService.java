package Backend.Service;

import Backend.Model.Flashcard;
import Backend.Model.Folder;
import Backend.Model.FolderStorage;
import Backend.Model.User;

import java.util.ArrayList;

public class FolderService {
    private User user;
    private FolderStorage folderStorage;

    public FolderService() {

    }

    public void setUser(User user) { // set the user and user's fodler storage object
        this.user = user;
    }

    public void setFolderStorage() {
        folderStorage = user.getFolderStorage();
    }

    public void addFolder(String folderName) {
        folderStorage.addFolder(folderName);
    }

    public void addFlashcardToFolder(String question, String answer, String folderName) {
        folderStorage.addFlashcardToFolder(question, answer, folderName);
    }

    public void addFlashcardToFolderId(String question, String answer, int folderId) {
        folderStorage.addFlashcardToFolderId(question, answer, folderId);
    }

    public void addFlashcardToDefaultFolder(String question, String answer) {
        folderStorage.addFlashcardToDefaultFolder(question, answer);
    }

    public ArrayList<Flashcard> getAllFlashcardsByFolderName(String folderName) {
        return folderStorage.getAllFlashcardsByFolderName(folderName);
    }

    public ArrayList<Flashcard> getAllFlashcardsByFolderId(int folderId) {
        return folderStorage.getAllFlashcardsByFolderId(folderId);
    }

    public Folder getFolderByFolderId(int folderId) {
        return folderStorage.getFolderByFolderId(folderId);
    }

    public Folder getFolderByName(String folderName) {
        return folderStorage.getFolderByFolderName(folderName);
    }

    public ArrayList<Folder> getAllFolders() {
        return folderStorage.getAllFolders();
    }

    public void addFlashcardToFolderByFolderId(String question, String answer, int folderId) {
        folderStorage.addFlashcardToFolderId(question, answer, folderId);
    }

    public ArrayList<Folder> getAllFoldersWithWrongFlashcards() {
        return folderStorage.getAllFoldersWithWrongFlashcards();
    }


    public ArrayList<Flashcard> getAllWrongFlashcardsByFolderId(int folderId) {
        return folderStorage.getFolderByFolderId(folderId).getAllWrongFlashcards();
    }

    public ArrayList<Flashcard> getAllCorrectFlashcardsByFolderId(int folderId) {
        return folderStorage.getFolderByFolderId(folderId).getAllCorrectFlashcards();
    }

    public void deleteFlashcardByFolderIdAndFlashcardId(int flashcardId, int folderId) {
        Flashcard flashcard = folderStorage.getFolderByFolderId(folderId).getFlashcardByFlashcardId(flashcardId);
        folderStorage.getFolderByFolderId(folderId).deleteFlashcard(flashcard);
    }

    public void editFlashcardByFolderIdAndFlashcardId(String newQuestion, String newAnswer, Folder folder, Flashcard flashcard) {
        Flashcard flashcard2 = folderStorage.getFolderByFolderId(folder.getId()).getFlashcardByFlashcardId(flashcard.getId());
        flashcard2.editFlashcard(newQuestion, newAnswer);
    }
}
