package Backend.Model;

import java.util.ArrayList;

public class Folder {
    private int id;
    private String folderName;
    private final ArrayList<Flashcard> flashcardList;
    private int flashcardId;
    private final ArrayList<Flashcard> correctFlashcardList;
    private final ArrayList<Flashcard> wrongFlashcardList;

    public Folder(String topic) {
        this.folderName = topic;
        flashcardList = new ArrayList<>();
        flashcardId = 0;
        correctFlashcardList = new ArrayList<>();
        wrongFlashcardList = new ArrayList<>();
    }

    public void addFlashcard(String question, String answer) {
        Flashcard flashcard = new Flashcard(question, answer);
        flashcardList.add(flashcard);
        // wrongFlashcardList.add(flashcard); // will add newly created flashcard to the wrong flashcard list
        flashcard.setId(flashcardId++);
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public boolean isEmpty() {
        return flashcardList.isEmpty();
    }

    public Flashcard getFlashcardByFlashcardId(int id) {
        for (Flashcard flashcard : flashcardList) {
            if (flashcard.getId() == id)
                return flashcard;
        }
        return null;
    }

    public ArrayList<Flashcard> getAllFlashcards() {
        return flashcardList;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTotalNumOfCorrectFlashcards() {
        return correctFlashcardList.size();
    }

    public int getTotalNumOfWrongFlashcards() {
        return wrongFlashcardList.size();
    }

    public void addToCorrectFlashcards(Flashcard flashcard) {
        correctFlashcardList.add(flashcard);
    }

    public void setEmptyWrongFlashcardList() {
        wrongFlashcardList.clear();
    }

    public void setEmptyCorrectFlashcardList() {
        correctFlashcardList.clear();
    }

    // WHen removing dont foget to call the add to wrongFLashcardList
    public void removeFlashcardFromCorrectFlashcardListByFlashcardId(int flashcardId) {
        for (Flashcard flashcard : correctFlashcardList) {
            if (flashcard.getId() == flashcardId) {
                correctFlashcardList.remove(flashcard);
                wrongFlashcardList.add(flashcard); // add to wrong list
                break;
            }
        }
    }

    public void removeFlashcardFromCorrectFlashcardList(int flashcardId) {
        for (Flashcard flashcard : correctFlashcardList) {
            if (flashcard.getId() == flashcardId) {
                correctFlashcardList.remove(flashcard);
                wrongFlashcardList.add(flashcard); // add to wrong list
                break;
            }
        }
    }

    public void addToWrongFlashcards(Flashcard flashcard) {
        wrongFlashcardList.add(flashcard);
    }

    public void removeFlashcardFromWrongFlashcardListByFlashcardID(int flashcardId) {
        for (Flashcard flashcard : wrongFlashcardList) {
            if (flashcard.getId() == flashcardId) {
                wrongFlashcardList.remove(flashcard);
                correctFlashcardList.add(flashcard); // add it to correct list
                break;
            }
        }
    }

    public ArrayList<Flashcard> getAllCorrectFlashcards() {
        return correctFlashcardList;
    }

    public ArrayList<Flashcard> getAllWrongFlashcards() {
        return wrongFlashcardList;
    }

    public void removeFlashcardByFlashcardId(int flashcardId) {
        for (Flashcard flashcard : flashcardList) {
            if (flashcard.getId() == flashcardId) {
                flashcardList.remove(flashcard); // remove from flashcardList
                wrongFlashcardList.remove(flashcard); // remove from correct list
                correctFlashcardList.remove(flashcard); // remove from wrong list
                break;
            }
        }
    }
}
