package app.ageofspice;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    }
}
