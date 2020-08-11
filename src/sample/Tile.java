package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private Text text = new Text();
    private boolean changeTileX = true;

    public Tile() {
        Rectangle rectangle = new Rectangle(200, 200);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle, text);

        text.setFont(Font.font(90));

        setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case  PRIMARY:
                    if (!changeTileX) {
                        return;
                    }
                    drawX();
                    changeTileX = false;
                    break;
                case SECONDARY:
                    if (changeTileX) {
                        return;
                    }
                    drawO();
                    changeTileX = true;
                    break;
            }
        });
    }

    private void drawX() {
        text.setText("X");
    }

    private void drawO() {
        text.setText("O");
    }
}
