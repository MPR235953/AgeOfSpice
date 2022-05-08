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

        // ################################# pomieszana grafika, do poprawki (tylko prezentacja)
        switch(this.planetType){
            case ALGA_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Javeler_explorer_ship.png"))));
            }
            case VIBRANIUM_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Ludzie_stateczek2.png"))));
            }
            case CRYSTAL_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Szrungale_stateczek.png"))));
            }
            case SPICE_PLANET -> {
                this.setImage(new Image(String.valueOf(getClass().getResource("arts\\alga_planet.png"))));
            }
        }
    }
}
