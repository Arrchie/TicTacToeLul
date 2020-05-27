package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    public ChoiceBox<String> iconChoice, modeChoice, startChoice;
    public Menu strengthPicker;
    public Button start;
    private Scene secondScene;
    private Controller c;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeChoices();
    }

    public void setSecondScene(Scene scene, Controller c) {
        secondScene = scene;
        this.c = c;
    }

    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        c.initialize(iconChoice.getValue(), modeChoice.getValue(), startChoice.getValue());
    }


    public void makeChoices() {
        iconChoice.getItems().add("Play as X");
        iconChoice.getItems().add("Play as O");
        iconChoice.setValue("Play as X");

        modeChoice.getItems().add("Play vs AI");
        modeChoice.getItems().add("Play Player vs Player");
        modeChoice.setValue("Play vs AI");

        startChoice.getItems().add("X starts");
        startChoice.getItems().add("O starts");
        startChoice.setValue("X starts");

        start.setOnAction(this::openSecondScene);
    }
}
