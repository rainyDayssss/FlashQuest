package Backend.Model;

import java.util.ArrayList;

public class BadgeStorage {
    private final ArrayList<Badge> badgeList;

    public BadgeStorage() {
        badgeList = new ArrayList<>();
    }

    public void addBadge(String badgeName, String badgeRequirement) {
        Badge badge = new Badge(badgeName, badgeRequirement);
        badgeList.add(badge);
    }

    public ArrayList<Badge> getAllBadges() {
        return badgeList;
    }
}
