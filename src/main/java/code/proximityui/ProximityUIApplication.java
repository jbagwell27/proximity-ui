package code.proximityui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProximityUIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProximityUIApplication.class.getResource("proximitymain-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 360);
        stage.setTitle("Proximity UI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}