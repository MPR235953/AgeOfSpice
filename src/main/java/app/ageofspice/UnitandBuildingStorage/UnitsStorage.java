package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.MapController;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.unit;
import app.ageofspice.Buildings.absBuilding;
import java.util.ArrayList;

import static app.ageofspice.MapController.TILE_SIZE;

/**
 * klasa przeznaczona do trzymania informacji o ilosci i budynkach oraz jednostakch danej nacji/gracza.
 */
/// TODO: 16.05.2022 Modyfikacja funkcji do ruchu i rozbudowa ich
/// TODO: 16.05.2022 Modyfikacja movementu
public class UnitsStorage {


    private ArrayList<unit> unitstorage = new ArrayList<unit>();
    private ArrayList<absBuilding> buildingsstorage = new ArrayList<absBuilding>();


    void resetstats(){
        for (int i = 0; i < unitstorage.size(); i++){
            unitstorage.get(i).movementSpeedleft = unitstorage.get(i).movementSpeedleft;
             if (unitstorage.get(i).actualHP<unitstorage.get(i).baseHP)
             unitstorage.get(i).actualHP +=1;
        }
    }

    public ArrayList<unit> getUnitstorage() {
        return unitstorage;
    }

    public void setUnitstorage(ArrayList<unit> unitstorage) {
        this.unitstorage = unitstorage;
    }


    void movement(unit UnittoMove, StatusandDirection Dir){

        int x1=UnittoMove.position.x,y1=UnittoMove.position.y;

        switch (Dir){
            case UP -> x1=(x1+1)*TILE_SIZE;
            case DOWN -> x1=(x1-1)*TILE_SIZE;
            case LEFT -> y1=(y1-1)*TILE_SIZE;
            case RIGHT -> y1=(y1+1)*TILE_SIZE;

        }

        if (UnittoMove.position.x == x1 && UnittoMove.position.y ==y1)
                return;


        switch (MapController.board[y1][x1].getTileType()){
            case EMPTY_SPACE:
                MapController.board[y1][x1].setTileType(UnittoMove.shipType);
                MapController.board[UnittoMove.position.y*TILE_SIZE][UnittoMove.position.x*TILE_SIZE].setTileType(TileType.EMPTY_SPACE);
                UnittoMove.position.y = y1/TILE_SIZE;
                UnittoMove.position.x = x1/TILE_SIZE;
                break;
            default:
                break;
        }




    };



}
