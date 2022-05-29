package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Buildings.MineStation;
import app.ageofspice.MapController;
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
    public int bonusAttack = 0;    //bonusowy atak z WarStation


    public void resetstats(){
        for (int i = 0; i < unitstorage.size(); i++){
            unitstorage.get(i).movementSpeedleft = unitstorage.get(i).movementSpeed;
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


    public unit searchforunit(int x,int y){
        for (int i = 0; i<this.unitstorage.size();i++){
            if (unitstorage.get(i).position.x  == x && unitstorage.get(i).position.y  == y){
                return  unitstorage.get(i);
            }

        }
        return null;
    }

    public int searchforunitindex(int x,int y){
        for (int i = 0; i<this.unitstorage.size();i++){
            if (unitstorage.get(i).position.x  == x && unitstorage.get(i).position.y  == y){
                return  i;
            }

        }
        return 0;
    }


    public static int movementCalculator(int oldx,int oldy,int newx,int newy){
        if (Math.abs(newx-oldx) >= Math.abs(newy-oldy))
            return Math.abs(newx-oldx);

        return Math.abs(newy-oldy);
    }

    public static int movement(unit UnittoMove, StatusandDirection Dir,int NewCorX , int NewCorY){

    //sprawdzanie poprawnosci z mapa
        if ((NewCorX > HORIZONTAL_TILE_COUNT || NewCorX <   0) || (NewCorY > VERTICAL_TILE_COUNT || NewCorY < 0 ))
            return -1;
    //sprawdzanie zakresu
        if ((NewCorX > UnittoMove.movementSpeedleft + UnittoMove.position.x || NewCorX <   UnittoMove.position.x - UnittoMove.movementSpeedleft)
        || (NewCorY > UnittoMove.movementSpeedleft + UnittoMove.position.y || NewCorY <  UnittoMove.position.y - UnittoMove.movementSpeedleft ))
            return -1;


        switch (Dir){
            case UP -> UnittoMove.imageView.setRotate(0);
            case DOWN -> UnittoMove.imageView.setRotate(180);
            case LEFT -> UnittoMove.imageView.setRotate(270);
            case RIGHT -> UnittoMove.imageView.setRotate(90);

        }



        switch (MapController.board[NewCorX][NewCorY].getTileType()){
            case EMPTY_SPACE:
                MapController.board[NewCorX][NewCorY].setTileType(UnittoMove.shipType);
                MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
                UnittoMove.imageView.relocate(NewCorX*TILE_SIZE,NewCorY*TILE_SIZE);
                UnittoMove.movementSpeedleft = UnittoMove.movementSpeedleft - movementCalculator(UnittoMove.position.x,UnittoMove.position.y,NewCorX,NewCorY);
                UnittoMove.position.y = NewCorY;
                UnittoMove.position.x = NewCorX;

            case DRED_SHIP,SCOUT_SHIP,DESTROYER_SHIP,EXPLORER_SHIP:
                //poszukaj u siebie
                //poszukaj u przeciwnika


                break;
            case JAV_PARENTAL_STATION,LUD_PARENTAL_STATION,SZR_PARENTAL_STATION:
            default:
                break;
        }



            return 0;
    };

    public ArrayList<absBuilding> getBuildingstorage() {
        return buildingstorage;
    }

    public int getsize() {
        return this.unitstorage.size();
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
