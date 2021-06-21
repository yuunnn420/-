package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


public class MenuController implements Initializable {

    @FXML
    private Button button;
    private Stage prevStage;
    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }
    public void setMenu(){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toLevel1();
            }
        });
    }

    public void toLevel1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
            Parent root = loader.load();
            Scene level1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(level1);
            stage.setTitle("Level 1");
            Level1Controller controller=loader.getController();
            controller.start();
            controller.drawMap();
            prevStage.close();
            controller.setPrevStage(stage);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
