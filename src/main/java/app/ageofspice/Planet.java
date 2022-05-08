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

        // ################################# Grafika do podmianki
        switch(this.planetType){
            case ALGA_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\grain100.png"))));
            }
            case VIBRANIUM_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\mountain100.png"))));
            }
            case CRYSTAL_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\grass100.png"))));
            }
            case SPICE_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\forest100.png"))));
            }
        }
    }
}
