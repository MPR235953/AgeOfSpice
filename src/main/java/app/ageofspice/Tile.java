package app.ageofspice;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private TileType tileType;

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Tile(int x, int y){
        this.setWidth(MapController.TILE_SIZE);
        this.setHeight(MapController.TILE_SIZE);
        this.relocate(x * MapController.TILE_SIZE, y * MapController.TILE_SIZE);
        this.setFill(Color.TRANSPARENT);
    }
}
