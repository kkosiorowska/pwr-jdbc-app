package app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    public void changeScene(String view, MouseEvent mouseEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app/views/" + view + ".fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene(fxmlLoader.load()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
