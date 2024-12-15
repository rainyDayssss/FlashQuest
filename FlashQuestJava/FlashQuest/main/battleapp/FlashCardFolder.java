package battleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCardFolder{
    private final List<Flashcard> flashcards;
    private int currentFlashcardIndex = 0;   // Tracks the current flashcard

    public FlashCardFolder() {
        flashcards = new ArrayList<>();
    }

    public void addFlashcard(String question, String answer) {
        flashcards.add(new Flashcard(question, answer));
    }

    // Method to shuffle the flashcards
    public void shuffleFlashcards() {
        Collections.shuffle(flashcards);
        currentFlashcardIndex = 0; // Reset after shuffle
    }

    // Method to get the current question
    public String getCurrentQuestion() {
        if (flashcards.isEmpty()) {
            return "No flashcards available.";
        }
        return flashcards.get(currentFlashcardIndex).getQuestion();
    }

    // move to next flashcard
    public void nextFlashcard() {
        //if (!flashcards.isEmpty()) {
            currentFlashcardIndex = (currentFlashcardIndex + 1) % flashcards.size();
        //}
    }

    public String getCurrentAnswer(){
        return flashcards.get(currentFlashcardIndex).getAnswer();
    }

}

