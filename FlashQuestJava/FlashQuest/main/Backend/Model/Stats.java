package Backend.Model;

public class Stats {
    private int health;
    private int attack;
    private int defenseInPercent;

    public Stats(int health, int attack, int defenseInPercent) {
        this.health = health;
        this.attack = attack;
        this.defenseInPercent = defenseInPercent;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public double getDefenseInPercent() {
        return defenseInPercent / 100.0;
    }

    public void addAmountToAttack(int amount) {
        attack += amount;
    }

    public void addAmountToDefense(int amount) {
        defenseInPercent += amount;
    }

    public void addAmountToHealth(int amount) {
        health += amount;
    }

    public void minusAmountToAttack(int amount) {
        attack -= amount;
    }

    public void minusAmountToDefense(int amount) {
        defenseInPercent -= amount;
    }

    public void minusAmountToHealth(int amount) {
        health -= amount;
    }

}
