package app.ageofspice;

import app.ageofspice.Species.SpeciesType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ParentalStation extends ImageView {

    public static final int BASIC_HP = 200;
    private TileType parentalStationType;
    private double HP;
    public ActualPosition stationposition= new ActualPosition(0,0);
    public double x, y;
    public SpeciesType owner;



    public double getHP(){ return this.HP; }
    public void setHP(double newHP){ this.HP = newHP; }

    public ParentalStation(int x, int y, TileType parentalStationType){
        this.setFitWidth(MapController.TILE_SIZE * MapController.SAS_SCALE);
        this.setFitHeight(MapController.TILE_SIZE * MapController.SAS_SCALE);
        //pozycja w pixelach
        this.x = x * MapController.TILE_SIZE + MapController.SAS_SCALE_POS;
        this.y = y * MapController.TILE_SIZE + MapController.SAS_SCALE_POS;
        this.relocate(this.x, this.y);
        //pozycja w tablicy
        stationposition.x = x;
        stationposition.y = y;

        this.owner = SpeciesType.NONE;
        this.parentalStationType = parentalStationType;
        this.HP = BASIC_HP;

        // ################################# Grafika planet do zamieszczenia
        switch(this.parentalStationType){
            case JAV_PARENTAL_STATION -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Javalerzy_textures\\Javalerzy_parental_station.png"))));
            case LUD_PARENTAL_STATION -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Ludzie_textures\\Ludzie_parental_station.png"))));
            case SZR_PARENTAL_STATION -> this.setImage(new Image(String.valueOf(getClass().getResource("arts\\Szrungale_textures\\Szrungale_parental_station.png"))));
        }
    }
}
