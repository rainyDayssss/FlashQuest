package Backend.Model;

public class Badge {
    private String badgeName;
    private String badgeRequirement;

    public Badge(String badgeName, String badgeRequirement) {
        this.badgeName = badgeName;
        this.badgeRequirement = badgeRequirement;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public String getBadgeRequirement() {
        return badgeRequirement;
    }
}
