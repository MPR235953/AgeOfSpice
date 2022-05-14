package app.ageofspice;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Planet extends ImageView {

    private TileType planetType;
    public Planet(int x, int y, TileType planetType){
        this.setFitWidth(MapController.TILE_SIZE);
        this.setFitHeight(MapController.TILE_SIZE);
        this.relocate(x * MapController.TILE_SIZE, y * MapController.TILE_SIZE);
        this.planetType = planetType;

        // ################################# Grafika planet do zamieszczenia
        switch(this.planetType){
            case ALGA_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\alga_planet100.png"))));
            }
            case VIBRANIUM_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\vibranium_planet100.png"))));
            }
            case CRYSTAL_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\"))));
            }
            case SPICE_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\"))));
            }
        }
    }
}
