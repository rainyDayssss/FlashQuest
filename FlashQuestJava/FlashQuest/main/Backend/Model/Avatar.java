package Backend.Model;

public abstract class Avatar {
    private final String avatarName;
    private final Level level;
    private final Stats stats;
    private final Ability ability;

    public Avatar(String name, Stats stats, Ability ability) {
        this.avatarName = name;
        this.stats = stats;
        this.ability = ability;
        level = new Level(); // same staring level
    }

    public String getAvatarName() {
        return avatarName;
    }

    public Level getLevelObject() {
        return level;
    }

    public Stats getStatsObject() {
        return stats;
    }

    public Ability getAbilityObject() {
        return ability;
    }

    public abstract void activateAbility(int numOfCorrectFlashcard);
    public abstract void disableAbility();
    public abstract void levelUpByExperience(int gainExperience);
}

class Warrior extends Avatar {
    public Warrior() {
        super("Warrior", new Stats(10, 10, 10), new Ability("Haggaigai", 3));
    }

    @Override
    public void activateAbility(int numOfCorrectFlashcard) {
        if (getAbilityObject().isAbilityReady(numOfCorrectFlashcard)) {
            getStatsObject().addAmountToHealth(20);
        }
    }

    @Override
    public void disableAbility() {
        if (getAbilityObject().isAbilityOn()) {
            getStatsObject().minusAmountToHealth(20);
        }
    }

    @Override
    public void levelUpByExperience(int gainExperience) {
        int countLevelGain = getLevelObject().gainExperience(gainExperience);
        getStatsObject().addAmountToHealth(2 * countLevelGain);
        getStatsObject().addAmountToAttack(countLevelGain); // equals to 1
        getStatsObject().addAmountToDefense(countLevelGain);
    }
}

class Mage extends Avatar {
    public Mage() {
        super("Mage", new Stats(5, 20, 5), new Ability("Aaronron", 5));
    }

    @Override
    public void activateAbility(int numOfCorrectFlashcard) {
        if (getAbilityObject().isAbilityReady(numOfCorrectFlashcard)) {
            getStatsObject().addAmountToAttack(20);
        }
    }

    @Override
    public void disableAbility() {
        if (getAbilityObject().isAbilityOn()) {
            getStatsObject().minusAmountToAttack(20);
        }
    }

    @Override
    public void levelUpByExperience(int gainExperience) {
        int countLevelGain = getLevelObject().gainExperience(gainExperience);
        getStatsObject().addAmountToHealth(countLevelGain);
        getStatsObject().addAmountToAttack(2 * countLevelGain);
        getStatsObject().addAmountToDefense(countLevelGain);
    }
}

class Tank extends Avatar {
    public Tank() {
        super("Tank", new Stats(10, 5, 20), new Ability("Ducaycay", 5));
    }

    @Override
    public void activateAbility(int numOfCorrectFlashcard) {
        if (getAbilityObject().isAbilityReady(numOfCorrectFlashcard)) {
            getStatsObject().addAmountToDefense(20);
        }
    }

    @Override
    public void disableAbility() {
        if (getAbilityObject().isAbilityOn()) {
            getStatsObject().minusAmountToDefense(20);
        }
    }

    @Override
    public void levelUpByExperience(int gainExperience) {
        int countLevelGain = getLevelObject().gainExperience(gainExperience);
        getStatsObject().addAmountToHealth(countLevelGain);
        getStatsObject().addAmountToAttack(countLevelGain);
        getStatsObject().addAmountToDefense(2 * countLevelGain);
    }
}