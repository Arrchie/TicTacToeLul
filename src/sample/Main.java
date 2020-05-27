package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent start = startLoader.load();
        Scene startScene = new Scene(start);
        StartController startController = startLoader.getController();

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent game = gameLoader.load();
        Scene gameScene = new Scene(game);
        Controller controller = gameLoader.getController();
        startController.setSecondScene(gameScene, controller);
        controller.setFirstScene(primaryStage, startScene);
        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
