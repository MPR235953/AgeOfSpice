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

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.*;

/**
 * klasa przeznaczona do trzymania informacji o ilosci i budynkach oraz jednostakch danej nacji/gracza.
 */
/// TODO: 16.05.2022 Modyfikacja funkcji do ruchu i rozbudowa ich
/// TODO: 16.05.2022 Modyfikacja movementu
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
        return -1;
    }




    public  static StatusandDirection statusandDirection(int oldx,int oldy,int newx,int newy) {
        int dirx1 = newx - oldx;
        int diry1 = newy - oldy;

        if ((dirx1 < 0 && diry1 < 0) || (dirx1 >= 0 && diry1 < 0)) {
            return StatusandDirection.UP;
        }
        else if (dirx1 < 0 && diry1 == 0){
            return StatusandDirection.LEFT;
        }
        else if (dirx1 > 0 && diry1 == 0){
            return StatusandDirection.RIGHT;
        }
        else {
            return StatusandDirection.DOWN;
        }

    }




    public static int movementCalculator(int oldx,int oldy,int newx,int newy){
        if (Math.abs(newx-oldx) >= Math.abs(newy-oldy))
            return Math.abs(newx-oldx);

        return Math.abs(newy-oldy);
    }
    public static int attack(int nation,int shipindex,unit UnittoMove, int NewCorX, int NewCorY,StatusandDirection Dir){
        playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP -=UnittoMove.baseDMG;
        UnittoMove.actualHP-=playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).baseDMG/2;


        //my przezylismy,przeciwnik nie
        if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP <=0 && UnittoMove.actualHP>0){
            MapController.staticPane.getChildren().remove(playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).imageView);
            playerResources[nation].getUnitBuilData().unitstorage.remove(shipindex);
            MapController.board[NewCorX][NewCorY].setTileType(TileType.EMPTY_SPACE);
            movement(UnittoMove,Dir,NewCorX,NewCorY);
            return 0;
        }
        //my nie żyjemy,przeciwnik też
        else if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP <=0 && UnittoMove.actualHP<=0){
            MapController.staticPane.getChildren().remove(playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).imageView);
            playerResources[nation].getUnitBuilData().unitstorage.remove(shipindex);
            MapController.board[NewCorX][NewCorY].setTileType(TileType.EMPTY_SPACE);
            MapController.staticPane.getChildren().remove(UnittoMove.imageView);
            MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
            playerResources[playerNumber].getUnitBuilData().unitstorage.remove(UnittoMove);

        }
        // my nie żyjemy, przeciwnik przeżył
        else if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP >0 && UnittoMove.actualHP<=0){
            MapController.staticPane.getChildren().remove(UnittoMove.imageView);
            MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
            playerResources[playerNumber].getUnitBuilData().unitstorage.remove(UnittoMove);
        }
        //oboje przeżyliśmy
        else{
            UnittoMove.movementSpeedleft = 0;
        }
        return 0;
    }



//tu odbywa się ruch jednostek i walka miedzy jednostkami
    public static int movement(unit UnittoMove, StatusandDirection Dir,int NewCorX , int NewCorY){

    //sprawdzanie poprawnosci z mapa
        if ((NewCorX > HORIZONTAL_TILE_COUNT || NewCorX <   0) || (NewCorY > VERTICAL_TILE_COUNT || NewCorY < 0 ))
            return -1;
    //sprawdzanie zakresu
        if ((NewCorX > UnittoMove.movementSpeedleft + UnittoMove.position.x || NewCorX <   UnittoMove.position.x - UnittoMove.movementSpeedleft)
        || (NewCorY > UnittoMove.movementSpeedleft + UnittoMove.position.y || NewCorY <  UnittoMove.position.y - UnittoMove.movementSpeedleft ))
            return -1;





        switch (MapController.board[NewCorX][NewCorY].getTileType()){
            case EMPTY_SPACE:
                MapController.board[NewCorX][NewCorY].setTileType(UnittoMove.shipType);
                MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
                UnittoMove.imageView.relocate(NewCorX*TILE_SIZE + SAS_SCALE_POS,NewCorY*TILE_SIZE + SAS_SCALE_POS);
                UnittoMove.movementSpeedleft = UnittoMove.movementSpeedleft - movementCalculator(UnittoMove.position.x,UnittoMove.position.y,NewCorX,NewCorY);
                UnittoMove.position.y = NewCorY;
                UnittoMove.position.x = NewCorX;

            case DRED_SHIP,SCOUT_SHIP,DESTROYER_SHIP,EXPLORER_SHIP:
            int shipindex =-2;
            int nation = playerNumber;

            for (int i = 0 ; i<playerResources[playerNumber].getUnitBuilData().unitstorage.size();i++){
                if (playerResources[playerNumber].getUnitBuilData().unitstorage.get(i).position.x  == NewCorX && playerResources[playerNumber].getUnitBuilData().unitstorage.get(i).position.y  == NewCorY){
                    shipindex = i;
                    break;
                }
            }
            if (shipindex == -2){

                nation++;
                if (nation==3)
                    nation=0;
                for (int i = 0 ; i<playerResources[nation].getUnitBuilData().unitstorage.size();i++){
                    if (playerResources[nation].getUnitBuilData().unitstorage.get(i).position.x  == NewCorX && playerResources[nation].getUnitBuilData().unitstorage.get(i).position.y  == NewCorY){
                        shipindex = i;
                        break;
                    }
                }
                if (shipindex !=-2 ){
                    attack(nation,shipindex,UnittoMove,NewCorX,NewCorY,Dir);
                }
                else{
                    nation++;
                    if (nation==3)
                        nation=0;
                    for (int i = 0 ; i<playerResources[nation].getUnitBuilData().unitstorage.size();i++){
                        if (playerResources[nation].getUnitBuilData().unitstorage.get(i).position.x  == NewCorX && playerResources[nation].getUnitBuilData().unitstorage.get(i).position.y  == NewCorY){
                            shipindex = i;
                            break;
                        }
                    }
                    attack(nation,shipindex,UnittoMove,NewCorX,NewCorY,Dir);
                }

            }
                break;
            case JAV_PARENTAL_STATION,LUD_PARENTAL_STATION,SZR_PARENTAL_STATION:

            case ALGA_PLANET,CRYSTAL_PLANET,SPICE_PLANET,VIBRANIUM_PLANET:
             //   if (MapController.board[NewCorX][NewCorY].getTileType().)
            default:
                break;
        }


        switch (Dir){
            case UP -> UnittoMove.imageView.setRotate(0);
            case DOWN -> UnittoMove.imageView.setRotate(180);
            case LEFT -> UnittoMove.imageView.setRotate(270);
            case RIGHT -> UnittoMove.imageView.setRotate(90);

        }

            return 0;
    };


    public ArrayList<absBuilding> getBuildingstorage() {
        return buildingstorage;
    }

    public int getsize() {
        return this.unitstorage.size();
    }


    public ArrayList<Planet> getPlanetStorage() {
        return allPlanetStorage;
    }

    void gatherResources(ResourceStorage resources){
        for(Planet planet : allPlanetStorage) {
            if (planet.owner == playerResources[playerNumber].getSpeciesType()) {
                switch (planet.planetType) {
                    case ALGA_PLANET -> resources.algi.quantity += planet.materialQuantity;
                    case VIBRANIUM_PLANET -> resources.wibranium.quantity += planet.materialQuantity;
                    case CRYSTAL_PLANET -> resources.krysztal.quantity += planet.materialQuantity;
                    case SPICE_PLANET -> resources.przyprawa.quantity += planet.materialQuantity;
                }
            }
        }
    }

}
