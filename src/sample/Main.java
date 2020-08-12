package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane root = new Pane();
    private final static int SIZE_X = 605;
    private final static int SIZE_Y = 605;
    private boolean play = true;
    private boolean change = true;
    private Tile[][] table = new Tile[3][3];
    private List<Logic> logic = new ArrayList<>();

    private Parent createTable() {

        root.setPrefSize(SIZE_X, SIZE_Y);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                root.getChildren().add(tile);
                table[j][i] = tile;
            }
        }

        for (int y = 0; y < 3; y++) {
            logic.add(new Logic(table[0][y], table[1][y], table[2][y]));
        }

        for (int x = 0; x < 3; x++) {
            logic.add(new Logic(table[x][0], table[x][1], table[x][2]));
        }

        logic.add(new Logic(table[0][0], table[1][1], table[2][2]));
        logic.add(new Logic(table[2][0], table[1][1], table[0][2]));

        return root;
    }

    private void checkPlay() {

        for (Logic logic : this.logic) {
            if (logic.isComplete()) {
                play = false;
                line(logic);
                break;
            }
        }
    }

    private void line(Logic logic) {

        Line line = new Line();
        line.setStartX(logic.tiles[0].getTranslateX() + 100);
        line.setStartY(logic.tiles[0].getTranslateY() + 100);
        line.setEndX(logic.tiles[0].getTranslateX() + 100);
        line.setEndY(logic.tiles[0].getTranslateY() + 100);
        line.setStroke(Color.RED);
        line.setStrokeWidth(3);

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), logic.tiles[2].getTranslateX() + 100),
                new KeyValue(line.endYProperty(), logic.tiles[2].getTranslateY() + 100)));
        timeline.play();
    }

    private class Logic {

        private Tile[] tiles;

        public Logic(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].text.getText().isEmpty())
                return false;

            return tiles[0].text.getText().equals(tiles[1].text.getText())
                    && tiles[0].text.getText().equals(tiles[2].text.getText());
        }
    }

    private class Tile extends StackPane {

        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(SIZE_X / 3, SIZE_Y / 3, Color.LIGHTBLUE);
            border.setStrokeWidth(5);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(85));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (play) {
                    switch (event.getButton()) {
                        case  PRIMARY:
                            if (!change) {
                                return;
                            }
                            text.setText("X");
                            change = false;
                            checkPlay();
                            break;
                        case SECONDARY:
                            if (change) {
                                return;
                            }
                            text.setText("O");
                            change = true;
                            checkPlay();
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(createTable()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}