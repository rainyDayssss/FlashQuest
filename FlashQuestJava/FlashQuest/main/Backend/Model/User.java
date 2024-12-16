package Backend.Model;

public class User {
    private final String username;
    private final String email;
    private final String password;
    private Avatar avatar; // will be null  if has not yet chosen an avatar
    private final FolderStorage folderStorage;
    private final BadgeStorage badgeStorage; // TODO on this, can get badge base on the total num of correct flashcards among others

    public User(String email, String password, String username) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.folderStorage = new FolderStorage();
        this.badgeStorage = new BadgeStorage();
    }

    public void chooseWarrior() {
        avatar = new Warrior();
    }

    public void chooseMage() {
        avatar = new Mage();
    }

    public void chooseTank() {
        avatar = new Tank();
    }

    public void checkLeveUp() {
        // TODO check if  the avatr can level up after every battle, so passing the stored correctFlashcardList from a folder
        // to the avatar then to the level object to check if it can level up

    }

    public Avatar getAvatarObject() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public FolderStorage getFolderStorage() {
        return folderStorage;
    }

    public BadgeStorage getBadgeStorage() {
        return badgeStorage;
    }

    public String getUsername() {
        return username;
    }
}
