package Backend.Model;

public class Level {
    private int currentLevel;
    private int currentExperience;
    private int experienceNeededToLevelUp;

    public Level() {
        currentLevel = 1;
        currentExperience = 0;
        experienceNeededToLevelUp = 10; // Base requirement for level 1 -> 2
    }

    public int gainExperience(int experienceGained) {
        currentExperience += experienceGained;
        return checkLevelUp();
    }


    private int checkLevelUp() {
        int countLevelGain = 0; // use to count how many level gain, then return it for the stats calc
        while (currentExperience >= experienceNeededToLevelUp) {
            currentExperience -= experienceNeededToLevelUp; // Use up experience points for leveling
            levelUpBy1();
            countLevelGain++;
            updateExperienceNeededToLevelUp();
        }
        return countLevelGain;
    }


    private void levelUpBy1() {
        currentLevel += 1;
    }


    private void updateExperienceNeededToLevelUp() {
        experienceNeededToLevelUp = 10 + (currentLevel * 5); // Example: Linear scaling
        // For exponential scaling:
        // experienceNeededToLevelUp = (int)(10 * Math.pow(1.5, currentLevel - 1));
    }


    public void correctFlashcardsToExperience(int totalCorrectFlashcards) {
        // Each 2 correct flashcards grant 1 experience point
        int experienceFromFlashcards = totalCorrectFlashcards / 2;
        gainExperience(experienceFromFlashcards);
    }

    // Getters for external use
    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getExperienceNeededToLevelUp() {
        return experienceNeededToLevelUp;
    }
}
