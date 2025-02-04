package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Buildings.MineStation;
import app.ageofspice.MapController;
import app.ageofspice.Planet;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.unit;
import app.ageofspice.Buildings.absBuilding;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static app.ageofspice.Explosion.createview;
import static app.ageofspice.Explosion.imageViewArrayList;
import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.*;

/**
 * klasa przeznaczona do trzymania informacji o ilosci i budynkach oraz jednostakch danej nacji/gracza.
 */

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

    public absBuilding searchForBuilding(int x,int y){
        for (int i = 0; i<this.buildingstorage.size();i++){
            if (buildingstorage.get(i).pos.x  == x && buildingstorage.get(i).pos.y  == y)
                return  buildingstorage.get(i);
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


    public static void attackannimation(int nation, int NewCorX, int NewCorY,int time,int x ,int y,unit unit1,int shipindex,int nationtodestroy) throws InterruptedException {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(laserArrayList.get(nation).imageView);
        translateTransition.setDuration(Duration.millis(time));
        translateTransition.setFromX(x*TILE_SIZE);
        translateTransition.setFromY(y*TILE_SIZE);
        translateTransition.setToX(NewCorX*TILE_SIZE);
        translateTransition.setToY(NewCorY*TILE_SIZE);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    laserArrayList.get(nation).destroyView();
                    laserArrayList.get(playerNumber).destroyView();
                    if (playerResources[nationtodestroy].getUnitBuilData().searchforunitindex(NewCorX,NewCorY) !=-1) {
                        destroyint(nationtodestroy, shipindex, unit1, NewCorX, NewCorY);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        translateTransition.play();

    }

    public static void attackannimationforbuildings(int nation, int NewCorX, int NewCorY,int time,int x ,int y,unit unit1,int shipindex) throws InterruptedException {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(laserArrayList.get(playerNumber).imageView);
        translateTransition.setDuration(Duration.millis(time));
        translateTransition.setFromX(x*TILE_SIZE);
        translateTransition.setFromY(y*TILE_SIZE);
        translateTransition.setToX(NewCorX*TILE_SIZE);
        translateTransition.setToY(NewCorY*TILE_SIZE);
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                attackbuilding(unit1,nation,shipindex);
            }

        });
        translateTransition.play();

    }

    public static void destroyanimation(int nation,int NewCorX, int NewCorY,int shipindex){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(playerResources[nation].getUnitBuilData().searchforunit(NewCorX,NewCorY).imageView);
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    afterattackupdate(nation, NewCorX, NewCorY,shipindex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        fadeTransition.play();
    }
        // modyfikacja
    public static void explosionanimation(int nation,int NewCorX, int NewCorY,int shipindex,int imageviewnumber,int time,int size){

        ImageView imageView = createview(imageViewArrayList.get(imageviewnumber),size,NewCorX,NewCorY);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(imageView);
        scaleTransition.setDuration(Duration.millis(time));
        scaleTransition.setByX(2.0);
        scaleTransition.setByY(2.0);
        scaleTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                MapController.staticPane.getChildren().remove(imageView);
                if (imageviewnumber+1<imageViewArrayList.size()){
                    explosionanimation(nation, NewCorX, NewCorY, shipindex, imageviewnumber+1, time+30,size+10);
                }
            }
        });
        scaleTransition.play();
    }
    public static void afterattackupdate(int nation,int NewCorX, int NewCorY,int shipindex) throws InterruptedException {
        if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP <=0){
            MapController.staticPane.getChildren().remove(playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).imageView);
            playerResources[nation].getUnitBuilData().unitstorage.remove(shipindex);
            MapController.board[NewCorX][NewCorY].setTileType(TileType.EMPTY_SPACE);
            explosionanimation(nation,NewCorX,NewCorY,shipindex,0,50,20);
        }
    }
    private static void destroyint(int nation,int shipindex,unit UnittoMove, int NewCorX, int NewCorY) throws InterruptedException {

        ActualPosition actualPosition =new ActualPosition();
        if ( laserArrayList.get(nation).position != actualPosition && laserArrayList.get(playerNumber).position != actualPosition) {
            laserArrayList.get(nation).destroyView();
            laserArrayList.get(playerNumber).destroyView();
        }
        //my przezylismy,przeciwnik nie
        if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP <=0 && UnittoMove.actualHP>0){
            destroyanimation(nation,NewCorX,NewCorY,playerResources[nation].getUnitBuilData().searchforunitindex(NewCorX,NewCorY));
         UnittoMove.movementSpeedleft=0;
        }
        //my nie żyjemy,przeciwnik też
        else if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP <=0 && UnittoMove.actualHP<=0){
            destroyanimation(nation,NewCorX,NewCorY,playerResources[nation].getUnitBuilData().searchforunitindex(NewCorX,NewCorY));
            destroyanimation(playerNumber,UnittoMove.position.x,UnittoMove.position.y,playerResources[playerNumber].getUnitBuilData().searchforunitindex(UnittoMove.position.x,UnittoMove.position.y));
        }
        // my nie żyjemy, przeciwnik przeżył
        else if (playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP >0 && UnittoMove.actualHP<=0){
            destroyanimation(playerNumber,UnittoMove.position.x,UnittoMove.position.y,playerResources[playerNumber].getUnitBuilData().searchforunitindex(UnittoMove.position.x,UnittoMove.position.y));
        }
        //oboje przeżyliśmy
        else{
            UnittoMove.movementSpeedleft = 0;
        }

    }



    public static int movementCalculator(int oldx,int oldy,int newx,int newy){
        if (Math.abs(newx-oldx) >= Math.abs(newy-oldy))
            return Math.abs(newx-oldx);

        return Math.abs(newy-oldy);
    }



    public static int attack(int nation,int shipindex,unit UnittoMove, int NewCorX, int NewCorY,StatusandDirection Dir) throws InterruptedException {
        playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).actualHP -=UnittoMove.baseDMG+playerResources[playerNumber].getUnitBuilData().bonusAttack;
        UnittoMove.actualHP-=playerResources[nation].getUnitBuilData().unitstorage.get(shipindex).baseDMG/2+playerResources[nation].getUnitBuilData().bonusAttack;
        laserArrayList.get(nation).lasersImviewCreate();
        laserArrayList.get(playerNumber).lasersImviewCreate();
        laserArrayList.get(playerNumber).imageView.setRotate(Math.atan2(NewCorY-UnittoMove.position.y,NewCorX-UnittoMove.position.x)*(180/Math.PI)+90);
        laserArrayList.get(nation).imageView.setRotate(Math.atan2(NewCorY-UnittoMove.position.y,NewCorX-UnittoMove.position.x)*(180/Math.PI)+90);
        attackannimation(playerNumber,NewCorX,NewCorY,600,UnittoMove.position.x,UnittoMove.position.y,UnittoMove,shipindex,nation);
        attackannimation(nation,UnittoMove.position.x,UnittoMove.position.y,600,NewCorX,NewCorY,UnittoMove,shipindex,nation);


        return 0;
    }

    public static void  attackbuilding(unit UnittoMove, int nation1,int stationindex){

            playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex).changeHP(-UnittoMove.baseDMG-playerResources[playerNumber].getUnitBuilData().bonusAttack);
                            if (playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex).actualHP <=0){
                                MapController.staticPane.getChildren().remove(playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex).imageView);
                                MapController.board[playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex).pos.x][playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex).pos.y].setTileType(TileType.EMPTY_SPACE);
                                playerResources[playerNumber].getUnitBuilData().buildingstorage.remove(playerResources[nation1].getUnitBuilData().buildingstorage.get(stationindex));
                            }
        laserArrayList.get(playerNumber).destroyView();

    }

//tu odbywa się ruch jednostek i walka miedzy jednostkami
    public static int movement(unit UnittoMove, StatusandDirection Dir,int NewCorX , int NewCorY) throws InterruptedException {

    //sprawdzanie poprawnosci z mapa
        if ((NewCorX > HORIZONTAL_TILE_COUNT || NewCorX <   0) || (NewCorY > VERTICAL_TILE_COUNT || NewCorY < 0 ))
            return -1;
    //sprawdzanie zakresu
        if ((NewCorX > UnittoMove.movementSpeedleft + UnittoMove.position.x || NewCorX <   UnittoMove.position.x - UnittoMove.movementSpeedleft)
        || (NewCorY > UnittoMove.movementSpeedleft + UnittoMove.position.y || NewCorY <  UnittoMove.position.y - UnittoMove.movementSpeedleft ))
            return -1;

        UnittoMove.imageView.setRotate(Math.atan2(NewCorY-UnittoMove.position.y,NewCorX-UnittoMove.position.x)*(180/Math.PI)+90);


        switch (MapController.board[NewCorX][NewCorY].getTileType()){
            case EMPTY_SPACE:
                MapController.board[NewCorX][NewCorY].setTileType(UnittoMove.shipType);
                MapController.board[UnittoMove.position.x][UnittoMove.position.y].setTileType(TileType.EMPTY_SPACE);
                //ZOSTAWIC NIE RUSZAC
                UnittoMove.imageView.relocate(2*SAS_SCALE_POS,2*SAS_SCALE_POS);
                //
                Path path = new Path();

                path.getElements().add(new MoveTo((UnittoMove.position.x)*TILE_SIZE + SAS_SCALE_POS,UnittoMove.position.y*TILE_SIZE + SAS_SCALE_POS));
                path.getElements().add(new CubicCurveTo(NewCorX*TILE_SIZE + SAS_SCALE_POS,UnittoMove.position.y*TILE_SIZE + SAS_SCALE_POS,UnittoMove.position.x*TILE_SIZE + SAS_SCALE_POS,NewCorY*TILE_SIZE + SAS_SCALE_POS,(Math.abs(NewCorX))*TILE_SIZE + SAS_SCALE_POS,(Math.abs(NewCorY))*TILE_SIZE + SAS_SCALE_POS));
                PathTransition pathTransition = new PathTransition();
                //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setDuration(Duration.millis(4000));
                pathTransition.setPath(path);
                pathTransition.setNode(UnittoMove.imageView);
                pathTransition.play();



                UnittoMove.movementSpeedleft = UnittoMove.movementSpeedleft - movementCalculator(UnittoMove.position.x,UnittoMove.position.y,NewCorX,NewCorY);
                UnittoMove.position.y = NewCorY;
                UnittoMove.position.x = NewCorX;
            break;
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
                int i = 0;
                for (; i<allParentalStationStorage.size();i++){
                    if (allParentalStationStorage.get(i).stationposition.x == NewCorX && allParentalStationStorage.get(i).stationposition.y == NewCorY)
                        break;
                }

                if (i != playerNumber){
                    allParentalStationStorage.get(i).setHP( allParentalStationStorage.get(i).getHP() - (double)UnittoMove.baseDMG/2);
                    UnittoMove.movementSpeedleft = 0;
                }
                if (allParentalStationStorage.get(i).getHP()<=0){
                    allParentalStationStorage.get(i).setImage(null);
                    board[NewCorX][NewCorY].setStroke(Color.TRANSPARENT);
                    board[NewCorX][NewCorY].active = false;
                    board[NewCorX][NewCorY].setTileType(TileType.EMPTY_SPACE);
                    destroyPlayer(i);

                }
                break;
            case MINE_STATION,WAR_STATION:
                int stationindex =-2;
                int nation1 = playerNumber;
                laserArrayList.get(playerNumber).lasersImviewCreate();
                laserArrayList.get(playerNumber).imageView.setRotate(Math.atan2(NewCorY-UnittoMove.position.y,NewCorX-UnittoMove.position.x)*(180/Math.PI)+90);

                for (int loop = 0 ; loop<playerResources[playerNumber].getUnitBuilData().buildingstorage.size();loop++){
                    if (playerResources[playerNumber].getUnitBuilData().buildingstorage.get(loop).pos.x  == NewCorX && playerResources[playerNumber].getUnitBuilData().buildingstorage.get(loop).pos.y  == NewCorY){
                        stationindex = loop;
                        break;
                    }
                }
                if (stationindex == -2) {

                    nation1++;
                    if (nation1 == 3)
                        nation1 = 0;
                    for (int loop = 0 ; loop<playerResources[nation1].getUnitBuilData().buildingstorage.size();loop++){
                        if (playerResources[nation1].getUnitBuilData().buildingstorage.get(loop).pos.x  == NewCorX && playerResources[nation1].getUnitBuilData().buildingstorage.get(loop).pos.y  == NewCorY){
                            stationindex = loop;
                            break;
                        }
                    }
                    if (stationindex != -2) {
                        attackannimationforbuildings(nation1,NewCorX,NewCorY,600,UnittoMove.position.x,UnittoMove.position.y,UnittoMove,stationindex);

                    } else {
                        nation1++;
                        if (nation1 == 3)
                            nation1 = 0;
                        for (int loop = 0 ; loop<playerResources[nation1].getUnitBuilData().buildingstorage.size();loop++){
                            if (playerResources[nation1].getUnitBuilData().buildingstorage.get(loop).pos.x  == NewCorX && playerResources[nation1].getUnitBuilData().buildingstorage.get(loop).pos.y  == NewCorY){
                                stationindex = loop;
                                break;
                            }
                        }

                        attackannimationforbuildings(nation1,NewCorX,NewCorY,600,UnittoMove.position.x,UnittoMove.position.y,UnittoMove,stationindex);

                    }
                }
            default:
                break;
        }




            return 0;
    };
    public static void destroyPlayer(int playernumber){

        playerResources[playernumber].Alive = false;
        while (!playerResources[playernumber].getUnitBuilData().unitstorage.isEmpty()) {
            MapController.staticPane.getChildren().remove(playerResources[playernumber].getUnitBuilData().unitstorage.get(0).imageView);
            MapController.board[playerResources[playernumber].getUnitBuilData().unitstorage.get(0).position.x][playerResources[playernumber].getUnitBuilData().unitstorage.get(0).position.y].setTileType(TileType.EMPTY_SPACE);
            playerResources[playernumber].getUnitBuilData().unitstorage.remove(playerResources[playernumber].getUnitBuilData().unitstorage.get(0));
        }
        for (Planet planet : allPlanetStorage) {
            if (playerResources[playernumber].getSpeciesType() == planet.owner) {
                planet.owner = SpeciesType.NONE;
                board[planet.planetPosition.x][planet.planetPosition.y].setStroke(Color.TRANSPARENT);
                board[planet.planetPosition.x][planet.planetPosition.y].active = false;
            }
        }
    }

    public ArrayList<absBuilding> getBuildingstorage() {
        return buildingstorage;
    }

    public ArrayList<Planet> getPlanetStorage() {
        return allPlanetStorage;
    }

    void gatherResources(ResourceStorage resources){
        //surowce ze stacji macierzystej
        resources.algi.quantity += 10;
        resources.wibranium.quantity += 10;
        resources.krysztal.quantity += 10;
        resources.przyprawa.quantity += 5;

        //surowce z posiadanych planet
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
        //bonus z kopalni
        for (absBuilding building:buildingstorage) {
            if(building instanceof MineStation) {
                resources.algi.quantity += 15;
                resources.wibranium.quantity += 10;
                resources.krysztal.quantity += 10;
                resources.przyprawa.quantity += 10;
            }
        }
    }

}
