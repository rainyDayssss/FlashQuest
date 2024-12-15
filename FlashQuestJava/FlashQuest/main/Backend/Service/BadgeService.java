package Backend.Service;
import Backend.Model.Badge;
import Backend.Model.BadgeStorage;
import Backend.Model.User;

import java.util.ArrayList;

public class BadgeService {
    private User user;
    private BadgeStorage badgeStorage;

    public BadgeService() {

    }

    public void setUser(User user) {
        this.user = user;
        badgeStorage = user.getBadgeStorage();
    }

    public ArrayList<Badge> getAllBadges() {
        return badgeStorage.getAllBadges();
    }

    public void addBadge(String badgeName, String badgeRequirement) {
        badgeStorage.addBadge(badgeName, badgeRequirement);
    }
}
