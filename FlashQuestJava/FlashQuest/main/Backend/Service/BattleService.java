package Backend.Service;
import Backend.Model.Flashcard;
import Backend.Model.Folder;
import Backend.Model.User;

import java.util.Iterator;
import java.util.Scanner;

public class BattleService {
    private User user;

    public BattleService() {

    }//TODO here

    public void setUser(User user) {
        this.user = user;
    }

    public void startBattle(int folderId) {
        Battle battle = new Battle(user);
        battle.startBattle(folderId);
    }
}

class Battle {
    private User user;

    public Battle(User user) {
        this.user = user;
    }
    // TODO add the user avatar and stats and level and ability
    // we need the folder id for the clicking of btn
    public void startBattle(int folderId) {
        Scanner scan = new Scanner(System.in);
        // Quest button
        // Choose from all the folders with start quest btn
        // then if folder A is clicked
        Folder folder = user.getFolderStorage().getFolderByFolderId(folderId);
        if (!folder.getAllWrongFlashcards().isEmpty()) {// meaning ther are still wrong answeres
            Iterator<Flashcard> iterator = folder.getAllWrongFlashcards().iterator();
            while (iterator.hasNext()) {
                Flashcard flashcard = iterator.next();
                System.out.println(flashcard.getQuestion());
                System.out.print("answer: ");
                if (scan.nextLine().equals(flashcard.getAnswer())) {
                    System.out.println("Correct");
                    iterator.remove(); // Safe removae
                    folder.addToCorrectFlashcards(flashcard);
                }
                else {
                    System.out.println("Wrong");
                }
            }
        }
        else { // meaning there no wrong answeres, all have been correctly answered
            folder.setEmptyCorrectFlashcardList();
            folder.setEmptyWrongFlashcardList();
            for (Flashcard flashcard : folder.getAllFlashcards()) { // start from the flashcardlist
                System.out.println(flashcard.getQuestion());
                System.out.print("answer: ");
                if (scan.nextLine().equals(flashcard.getAnswer())) {
                    System.out.println("Correct");
                    folder.addToCorrectFlashcards(flashcard);
                }
                else {
                    System.out.println("Wrong");
                    folder.addToWrongFlashcards(flashcard);
                }
            }
        }
        // if ther are still incorrectly answered flashcards
            // then start with those flashcards
            // the same process until return or have correctly answered all flashcards
        // else (meaning all flashcards have been correctly answered)
            // set the both correct and wrong list to empty
            // then print all the questions of each flashcard in folder A 1 by 1 (based from the flashcardList)
            // prompt the user to enter the asnwer of each q
            // if correct add the flashcard to the correctStorage
            // else let ir stay in the wrong list
            // until all the flashcards are in the correctStorage or return
        // track what correctly answered and not correclty answered


    }
}

