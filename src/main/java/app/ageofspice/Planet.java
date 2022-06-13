package app.ageofspice;

import app.ageofspice.Species.SpeciesType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Planet extends ImageView {

    public TileType planetType;
    public int materialQuantity = 20;
    public ActualPosition planetPosition = new ActualPosition(0,0);
    public SpeciesType owner;
    public double x, y;

    public Planet(int x, int y, TileType planetType){
        this.setFitWidth(MapController.TILE_SIZE);
        this.setFitHeight(MapController.TILE_SIZE);
        //pozycja w pixelach
        this.x = x * MapController.TILE_SIZE;
        this.y = y * MapController.TILE_SIZE;
        this.relocate(this.x, this.y);
        //pozycja w tablicy
        this.planetPosition.x = x;
        this.planetPosition.y = y;

        this.planetType = planetType;
        this.owner = SpeciesType.NONE;

        // ################################# Grafika planet
        switch(this.planetType){
            case ALGA_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\alga_planet100.png"))));
            case VIBRANIUM_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\vibranium_planet100.png"))));
            case CRYSTAL_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\crystal_planet100.png"))));
            case SPICE_PLANET -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\resources_and_planets\\spiceplanet.png"))));
        }
    }
}
