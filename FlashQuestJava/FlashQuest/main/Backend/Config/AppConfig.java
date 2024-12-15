package Backend.Config;

import Backend.Controller.FlashQuestController;
import Backend.Repo.FlashQuestRepo;
import Backend.Service.*;


public class AppConfig {
    public static UserService createUserService() {
        FlashQuestRepo flashQuestRepo = new FlashQuestRepo();
        return new UserService(flashQuestRepo);
    }

    public static AvatarService createAvatarService() {
        return new AvatarService();
    }

    public static FolderService createFolderService() {
        return new FolderService();
    }

    public static BadgeService createBadgeService() {
        return new BadgeService();
    }

    public static BattleService createBattleService() {
        return new BattleService();
    }

    public static FlashQuestController createController() {
        UserService userService = createUserService();
        AvatarService avatarService = createAvatarService();
        FolderService folderService = createFolderService();
        BadgeService badgeService = createBadgeService();
        BattleService battleService = createBattleService();
        return new FlashQuestController(userService, avatarService, folderService, badgeService, battleService);
    }
}
