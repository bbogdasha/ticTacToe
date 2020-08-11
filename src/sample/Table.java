package sample;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Table {

    private final static Pane root = new Pane();
    private final static int SIZE_X = 600;
    private final static int SIZE_Y = 600;

    public static Parent createTable() {

        root.setPrefSize(SIZE_X, SIZE_Y);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                root.getChildren().add(tile);
            }
        }
        return root;
    }
}
