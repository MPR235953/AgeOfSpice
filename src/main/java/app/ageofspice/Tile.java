package app.ageofspice;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Map;

public class Tile extends Rectangle {

    private int x, y;

    private TileType tileType = TileType.EMPTY_SPACE;

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Tile(int x, int y){
        this.x = x * MapController.TILE_SIZE;
        this.y = y * MapController.TILE_SIZE;

        this.setWidth(MapController.TILE_SIZE);
        this.setHeight(MapController.TILE_SIZE);
        this.relocate(this.x , this.y);
        this.setFill(Color.TRANSPARENT);

        tileHover();
        tileClick();
    }

    public void tileHover(){
        this.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue){
                this.setStroke(Color.WHITE);
                this.setStrokeWidth(3);
                this.setStyle("-fx-cursor: hand;");
            }
            else{
                switch(this.tileType){
                    case JAV_PARENTAL_STATION -> this.setStroke(Color.rgb(0,230,250));
                    case LUD_PARENTAL_STATION -> this.setStroke(Color.rgb(221, 44,0));
                    case SZR_PARENTAL_STATION -> this.setStroke(Color.rgb(233,30,98));
                    default -> this.setStroke(Color.TRANSPARENT);
                }
                this.setStrokeWidth(3);
                this.setStyle("-fx-cursor: default;");
            }
        });
    }

    public void tileClick(){
        this.setOnMouseClicked(event -> {
            if(this.tileType == TileType.EMPTY_SPACE) {
                OnClickSpaceWin win = new OnClickSpaceWin();
                win.makeWin(this.x, this.y);
                MapController.staticPane.getChildren().add(win);
            }

            ///TODO: inne okienka dla innych rodzajow obiektow
        });


    }
}
