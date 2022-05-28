package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Buildings.MineStation;
import app.ageofspice.MapController;
import app.ageofspice.Planet;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.unit;
import app.ageofspice.Buildings.absBuilding;
import java.util.ArrayList;

import static app.ageofspice.MapController.*;

/**
 * klasa przeznaczona do trzymania informacji o ilosci i budynkach oraz jednostakch danej nacji/gracza.
 */
/// TODO: 16.05.2022 Modyfikacja funkcji do ruchu i rozbudowa ich
/// TODO: 16.05.2022 Modyfikacja movementu
    /// TODO: 23.05.2022 Switche ruchu (rotate)
public class UnitsStorage {


    private ArrayList<unit> unitstorage = new ArrayList<unit>();
    private ArrayList<absBuilding> buildingstorage = new ArrayList<absBuilding>();
    private ArrayList<Planet> planetStorage = new ArrayList<Planet>();
    public int bonusAttack = 0;    //bonusowy atak z WarStation


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


    public static void movement(unit UnittoMove, StatusandDirection Dir,int NewCorX , int NewCorY){

    //sprawdzanie poprawnosci z mapa
        if ((NewCorX > HORIZONTAL_TILE_COUNT || NewCorX <   0) || (NewCorY > VERTICAL_TILE_COUNT || NewCorY < 0 ))
            return;
    //sprawdzanie zakresu
        if ((NewCorX > UnittoMove.movementSpeedleft + UnittoMove.position.x || NewCorX <   UnittoMove.position.x - UnittoMove.movementSpeedleft)
        || (NewCorY > UnittoMove.movementSpeedleft + UnittoMove.position.y || NewCorY <  UnittoMove.position.y - UnittoMove.movementSpeedleft ))
            return;

        int x1=NewCorX,y1=NewCorY;

        //rotate


        switch (Dir){
            case UP -> UnittoMove.imageView.setRotate(0);
            case DOWN -> UnittoMove.imageView.setRotate(180);
            case LEFT -> UnittoMove.imageView.setRotate(270);
            case RIGHT -> UnittoMove.imageView.setRotate(90);

        }



        switch (MapController.board[x1][y1].getTileType()){
            case EMPTY_SPACE:
                MapController.board[x1][y1].setTileType(UnittoMove.shipType);
                MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
                UnittoMove.imageView.relocate(x1*TILE_SIZE,y1*TILE_SIZE);
                UnittoMove.position.y = y1;
                UnittoMove.position.x = x1;
                return;
            //case E
            default:

                break;
        }




    };

    public ArrayList<absBuilding> getBuildingstorage() {
        return buildingstorage;
    }

/*
    void gatherResources(ResourceStorage resources){
        //TODO: pętla przechodząca przez wszystkie planety
        //resources.algi.quantity += 10;

        for(absBuilding building : buildingstorage){
            if(building instanceof MineStation){
            //if(building.getClass() == MineStation.class){
                resources.algi.quantity += building.minedResource;    //TODO: jak rozpoznawać tą klasę i odpowiednie surowce?
            }
        }
    }
 */

}
