package app.ageofspice;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Planet extends ImageView {

    public TileType planetType;
    public final int materialQuantity = 20;

    public Planet(int x, int y, TileType planetType){
        this.setFitWidth(MapController.TILE_SIZE);
        this.setFitHeight(MapController.TILE_SIZE);
        this.relocate(x * MapController.TILE_SIZE, y * MapController.TILE_SIZE);

        this.planetType = planetType;

        // ################################# Grafika planet
        switch(this.planetType){
            case ALGA_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\alga_planet100.png"))));
            case VIBRANIUM_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\vibranium_planet100.png"))));
            case CRYSTAL_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\crystal_planet100.png"))));
            case SPICE_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\spiceplanet.png"))));
        }
    }
}
