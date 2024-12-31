module com.example.flashquestjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ChooseClassPage to javafx.fxml;
    exports ChooseClassPage;

    opens CreateFolderPage to javafx.fxml;
    exports CreateFolderPage;

    opens CreateSmithCard to javafx.fxml;
    exports CreateSmithCard;

    opens FolderPage to javafx.fxml;
    exports FolderPage;

    opens LoginPage to javafx.fxml;
    exports LoginPage;

    opens MenuPage to javafx.fxml;
    exports MenuPage;

    opens QuestPage to javafx.fxml;
    exports QuestPage;

    opens SignUpPage to javafx.fxml;
    exports SignUpPage;

    opens StartingPage to javafx.fxml;
    exports StartingPage;

    opens FightPage to javafx.fxml;
    exports FightPage;
}
