package app.ageofspice;

import  app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Windows.OnClickSpaceWin;
import app.ageofspice.Windows.OnClickSpaceWinForUnits;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.unit;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.*;
import static app.ageofspice.MapController.board;
import static app.ageofspice.TileType.*;
import static app.ageofspice.UnitandBuildingStorage.UnitsStorage.movement;
import static app.ageofspice.UnitandBuildingStorage.UnitsStorage.statusandDirection;
import static app.ageofspice.Windows.OnClickSpaceWinForUnits.flagToMove;
import static app.ageofspice.Windows.OnClickSpaceWinForUnits.unitToMove;


public class Tile extends Rectangle {

    public int boardX, boardY;
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
        this.boardX = x;
        this.boardY = y;
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

            /*//position in board
            if(this.tileType == TileType.ALGA_PLANET){
                for(int i = 0; i < freePlanetStorage.toArray().length; i++){
                    if(freePlanetStorage.get(i).planetPosition.x == this.boardX && freePlanetStorage.get(i).planetPosition.y == this.boardY)
                        System.out.println(freePlanetStorage.get(i));
                }
            }*/

            /*//position in pixels
            if(this.tileType == TileType.ALGA_PLANET){
                for(int i = 0; i < freePlanetStorage.toArray().length; i++){
                    if(freePlanetStorage.get(i).x == this.x && freePlanetStorage.get(i).y == this.y)
                        System.out.println(freePlanetStorage.get(i));
                }
            }*/

            //zmien  na switcha
            if(this.tileType == TileType.EMPTY_SPACE && !flagToMove) {
                active = true;
                //zmodyfikuj pod stacje

                OnClickSpaceWin win = new OnClickSpaceWin();
                win.setParentTile(this);
                win.makeWinForBuildings(this.x, this.y);
            }
            else if (this.tileType == TileType.JAV_PARENTAL_STATION || this.tileType == TileType.LUD_PARENTAL_STATION
            || this.tileType == TileType.SZR_PARENTAL_STATION){

                /// TODO: 29.05.2022 Do poprawy. Zmienić na enumy i zbadać stacje
                if (this.tileType == JAV_PARENTAL_STATION && playerNumber == 0) {
                    OnClickSpaceWin win = new OnClickSpaceWin();
                    win.setParentTile(this);
                    win.makeWinForStation(this.x, this.y);
                }
                else if (this.tileType == LUD_PARENTAL_STATION && playerNumber == 1){
                    OnClickSpaceWin win = new OnClickSpaceWin();
                    win.setParentTile(this);
                    win.makeWinForStation(this.x, this.y);
                }
                else{
                    OnClickSpaceWin win = new OnClickSpaceWin();
                    win.setParentTile(this);
                    win.makeWinForStation(this.x, this.y);
                }

            }
            else if (flagToMove){

                if (unitToMove !=null) {
                    if ((this.x/TILE_SIZE >= unitToMove.position.x - unitToMove.movementSpeedleft && this.x/TILE_SIZE <= unitToMove.position.x + unitToMove.movementSpeedleft)
                            && (this.y/TILE_SIZE >= unitToMove.position.y - unitToMove.movementSpeedleft && this.y/TILE_SIZE <= unitToMove.position.y + unitToMove.movementSpeedleft)) {
                        int oldX = unitToMove.position.x;
                        int oldY = unitToMove.position.y;
                        int movspedlef = unitToMove.movementSpeedleft;
                       if ( movement(playerResources[playerNumber].getUnitBuilData().getUnitstorage().get(playerResources[playerNumber].getUnitBuilData().searchforunitindex(unitToMove.position.x, unitToMove.position.y)), statusandDirection(unitToMove.position.x, unitToMove.position.y,this.x/TILE_SIZE, this.y/TILE_SIZE), this.x/TILE_SIZE, this.y/TILE_SIZE) != -1){
                            flagToMove = false;
                            clearFields(oldX,oldY,movspedlef);
                        }
                    }
                }
                
            } else if (this.tileType == TileType.SCOUT_SHIP || this.tileType == TileType.EXPLORER_SHIP || this.tileType == TileType.DESTROYER_SHIP
            || this.tileType == TileType.DRED_SHIP){

             unit  unitToMove1 = playerResources[playerNumber].getUnitBuilData().searchforunit(this.x/TILE_SIZE,this.y/TILE_SIZE);

                if (unitToMove1 != null && unitToMove1.movementSpeedleft  != 0){
                    OnClickSpaceWinForUnits win = new OnClickSpaceWinForUnits(unitToMove1);
                    win.setParentTile(this);
                    win.makeWin(this.x, this.y);
                }
            }

            ///TODO: inne okienka dla innych rodzajow obiektow
        });


    }

    public void clearFields(int x1,int y1,int movleft){

        for (int i = x1-movleft; i<=x1+movleft;i++){
            for (int j =y1-movleft; j<=y1+movleft;j++) {
                if ((!((i < 0 || i >= HORIZONTAL_TILE_COUNT) || (j < 0 || j >= VERTICAL_TILE_COUNT)) ) &&  (board[i][j].getTileType() == EMPTY_SPACE || board[i][j].getTileType() == DESTROYER_SHIP || board[i][j].getTileType() ==DRED_SHIP || board[i][j].getTileType() == SCOUT_SHIP || board[i][j].getTileType() == EXPLORER_SHIP)) {
                            board[i][j].setStroke(Color.TRANSPARENT);
                            board[i][j].active = false;
                    }
                }
            }

    }


}
