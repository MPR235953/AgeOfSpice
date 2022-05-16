package app.ageofspice;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Planet extends ImageView {

    public static final int MATERIAL_MAX = 30;
    public static final int MATERIAL_MIN = 5;
    private TileType planetType;
    private int materialQuantity;

    public Planet(int x, int y, TileType planetType){
        this.setFitWidth(MapController.TILE_SIZE);
        this.setFitHeight(MapController.TILE_SIZE);
        this.relocate(x * MapController.TILE_SIZE, y * MapController.TILE_SIZE);

        this.planetType = planetType;
        this.materialQuantity = new Random().nextInt(MATERIAL_MAX - MATERIAL_MIN) + MATERIAL_MIN;

        // ################################# Grafika planet do zamieszczenia
        switch(this.planetType){
            case ALGA_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\alga_planet100.png"))));
            case VIBRANIUM_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\vibranium_planet100.png"))));
            case CRYSTAL_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\crystal_planet100.png"))));
            case SPICE_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\spiceplanet.png"))));
        }
    }
}
