package Backend.Model;

public class Ability {
    private final String abilityName;
    private final int correctFlashcardsNeeded;
    private final boolean isOn;

    public Ability(String name, int correctFlashcardsNeeded) {
        this.abilityName = name;
        this.correctFlashcardsNeeded = correctFlashcardsNeeded;
        isOn = false;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public int getCorrectFlashcardsNeeded() {
        return correctFlashcardsNeeded;
    }

    public boolean isAbilityReady(int numOfCorrectFlashcard) {
        if (!isOn) // already active
            return false;
        return numOfCorrectFlashcard == correctFlashcardsNeeded;
    }

    public boolean isAbilityOn() {
       return isOn;
    }
}
