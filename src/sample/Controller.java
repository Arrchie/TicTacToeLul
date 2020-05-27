package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    public GridPane gamePane;
    private Scene firstScene;
    private Stage stage;
    Game game = new Game(this);

    public void setFirstScene(Stage stage, Scene scene) {
        firstScene = scene;
        this.stage = stage;
    }

    public void openFirstScene(Stage primaryStage) {
        primaryStage.setScene(firstScene);
    }

    public void initialize(String s1, String s2, String s3) {
        game.setSettings(s1, s2, s3);
        game.restart();
        gamePane.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            game.handlePlayerMove(x, y);
        });
    }

    public void handleWin(Player player, boolean isWin) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Play Again?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Game over");
        if(isWin) {
            alert.setHeaderText(player.getIcon() + " has won the game.");
        } else {
            alert.setHeaderText("The game was a draw.");
        }
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO) {
            openFirstScene(stage);
        } else {
            game.restart();
        }
    }

    public void restart() {
        gamePane.getChildren().retainAll(gamePane.getChildren().get(0));
    }

    public void drawMove(Player player, int x, int y) {
        Label move = new Label(player.getIcon());
        move.setScaleX(10);
        move.setScaleY(10);
        GridPane.setHalignment(move, HPos.CENTER);
        GridPane.setValignment(move, VPos.CENTER);
        gamePane.add(move, y, x);
    }
}
