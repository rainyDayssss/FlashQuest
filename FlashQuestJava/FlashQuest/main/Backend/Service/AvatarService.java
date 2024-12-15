package Backend.Service;

import Backend.Model.*;

public class AvatarService {
    private User user;
    private Avatar avatar;

    public AvatarService() {

    }

    public void setUser(User user) {
        this.user = user;
        // this will become null, why??
    }

    public void setAvatar() {
        avatar = user.getAvatarObject();
    }

    public void chooseWarrior() {
        user.chooseWarrior();
        setAvatar();
    }

    public void chooseMage() {
        user.chooseMage();
        setAvatar();
    }

    public void chooseTank() {
        user.chooseTank();
        setAvatar();
    }

    public String getAbilityName() {
        return avatar.getAbilityObject().getAbilityName();
    }

    public int getHealth() {
        return avatar.getStatsObject().getHealth();
    }

    public int getAttack() {
        return avatar.getStatsObject().getAttack();
    }

    public double getDefenseInPercent() {
        return avatar.getStatsObject().getDefenseInPercent();
    }

    public Level getLevelObject() {
        return avatar.getLevelObject();
    }

    public Ability getAbilityObject() {
        return avatar.getAbilityObject();
    }

    public Stats getStatsObject() {
        return avatar.getStatsObject();
    }

    public int getExperience() {
        return avatar.getLevelObject().getCurrentExperience();
    }
}
