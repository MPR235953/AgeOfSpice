package app.ageofspice;

import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Windows.OnClickSpaceWin;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public int x, y;
    public boolean active = false;      //true jezeli kafelek zostal klikniety, zmiana na false poprzez klasy okienek

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

    //zmiana po najechaniu na kafelek
    public void tileHover(){
        this.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue){
                if(!active) this.setStroke(Color.WHITE);
                this.setStrokeWidth(3);
                this.setStyle("-fx-cursor: hand;");
            }
            else{
                switch(this.tileType){
                    case JAV_PARENTAL_STATION -> this.setStroke(SpeciesColors.javColor);
                    case LUD_PARENTAL_STATION -> this.setStroke(SpeciesColors.ludColor);
                    case SZR_PARENTAL_STATION -> this.setStroke(SpeciesColors.szrColor);
                    default -> {
                        if(!active)
                            this.setStroke(Color.TRANSPARENT);
                    }
                }
                this.setStrokeWidth(3);
                this.setStyle("-fx-cursor: default;");
            }
        });
    }

    //Zmiana po kliknieciu w kafelek
    public void tileClick(){
        this.setOnMouseClicked(event -> {
            //zmien  na switcha
            if(this.tileType == TileType.EMPTY_SPACE) {
                active = true;
                //zmodyfikuj pod stacje
                OnClickSpaceWin win = new OnClickSpaceWin();
                win.setParentTile(this);
                win.makeWin(this.x, this.y);
            }
            else if (this.tileType == TileType.JAV_PARENTAL_STATION || this.tileType == TileType.LUD_PARENTAL_STATION
            || this.tileType == TileType.SZR_PARENTAL_STATION){
                ///TODO: Dodanie ogranicznika(Ludzie mogą klikac tylko na swoja stacje i wykonywac akcje itp)

                OnClickSpaceWin win = new OnClickSpaceWin();
                win.setParentTile(this);
                win.makeWinForStation(this.x, this.y);

            }

            ///TODO: inne okienka dla innych rodzajow obiektow
        });


    }
}
