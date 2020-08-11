package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(Table.createTable()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}