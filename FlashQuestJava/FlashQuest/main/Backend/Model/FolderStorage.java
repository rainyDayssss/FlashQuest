package Backend.Model;

import java.util.ArrayList;
// Does auto save
public class FolderStorage {
    private final ArrayList<Folder> folderList;
    private final Folder defaultFolder;
    private int folderId;
    private final ArrayList<Folder> folderListWithCorrectFlashcards;
    private final ArrayList<Folder> folderListWithWrongFlashcards;

    public FolderStorage() {
        defaultFolder = new Folder("Default Folder");
        folderList = new ArrayList<>();
        folderList.add(defaultFolder);
        folderId = 1; // 1 id for the defaultFolder
        folderListWithCorrectFlashcards = new ArrayList<>();
        folderListWithWrongFlashcards = new ArrayList<>();
    }

    public void addFolder(String topic) {
        Folder folder = new Folder(topic);
        folderList.add(folder);
        folder.setId(folderId++);
    }

    public void addFlashcardToFolder(String question, String answer, String folderName) {
        for (Folder folder : folderList) {
            if (folder.getFolderName().equals(folderName)) {
                folder.addFlashcard(question, answer);
                return;
            }
        }
        System.out.println("Folder was not found");
    }

    public void addFlashcardToDefaultFolder(String question, String answer) {
        defaultFolder.addFlashcard(question, answer);
    }

    public Folder getFolderByFolderName(String folderName) {
        for (Folder folder : folderList) {
            if (folder.getFolderName().equals(folderName))
                return folder;
        }
        return null;
    }

    public Folder getFolderByFolderId(int id) {
        for (Folder folder : folderList) {
            if (folder.getId() == id)
                return folder;
        }
        return null;
    }

    public ArrayList<Folder> getAllFolders() {
        return folderList;
    }


    public ArrayList<Flashcard> getAllFlashcardsByFolderName(String folderName) {
        for (Folder folder : folderList) {
            if (folder.getFolderName().equals(folderName))
                return folder.getAllFlashcards();
        }
        return null;
    }

    public ArrayList<Flashcard> getAllFlashcardsByFolderId(int id) {
        for (Folder folder : folderList) {
            if (folder.getId() == id)
                return folder.getAllFlashcards();
        }
        return null;
    }

    public int getTotalNumOfCorrectFlashcardsFromAllFolders() {
        int total = 0;
        for (Folder folder : folderList) {
            total += folder.getTotalNumOfCorrectFlashcards();
        }
        return total;
    }

    public int getTotalNumOfWrongFlashcardsFromAllFolders() {
        int total = 0;
        for (Folder folder : folderList) {
            total += folder.getTotalNumOfWrongFlashcards();
        }
        return total;
    }

    public void updateFolderListWithWrongFlashcards() {
        for (Folder folder : folderList) {
            if (folder.getTotalNumOfWrongFlashcards() > 0) {
                folderListWithWrongFlashcards.add(folder);
            }
        }
    }


    public void updateFolderListWithCorrectFlashcards() {
        for (Folder folder : folderList) {
            if (folder.getTotalNumOfCorrectFlashcards() > 0) {
                folderListWithCorrectFlashcards.add(folder);
            }
        }
    }

    // this could be use for the folders in new quest
    public ArrayList<Folder> getAllFoldersWithWrongFlashcards() {
        folderListWithWrongFlashcards.clear();
        for (Folder folder : folderList) {
            if (!folder.getAllWrongFlashcards().isEmpty()) {
                folderListWithWrongFlashcards.add(folder);
            }
        }
        return folderListWithWrongFlashcards;
    }

    public void addFlashcardToFolderId(String question, String answer, int folderId) {
        for (Folder folder : folderList) {
            if (folder.getId() == folderId) {
                folder.addFlashcard(question, answer);
                break;
            }
        }
    }

    public void deleteFolder(Folder folder) {
        folderListWithCorrectFlashcards.remove(folder);
        folderListWithWrongFlashcards.remove(folder);
        folderList.remove(folder);
    }
}
