package app.ageofspice;

import app.ageofspice.Species.SpeciesType;
import app.ageofspice.UnitandBuildingStorage.PlayerResourcesandUnitsStorage;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.ExplorerShip;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static app.ageofspice.UnitandBuildingStorage.UnitsStorage.movement;

public class GameLoop{
    public static PlayerResourcesandUnitsStorage[] playerResources = {    new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage()
                                                        };
    public static int roundNumber = 1;
    public static int playerNumber = 0;
    public static ArrayList<Planet> allPlanetStorage = new ArrayList<Planet>();
    public static ArrayList<ParentalStation> allParentalStationStorage = new ArrayList<ParentalStation>();
    public void startGame() {
        //Ustawienie tylow gatonkow graczy i odpowiadajace im kolory
        playerResources[0].setSpeciesType(SpeciesType.JAVALERZY);
        playerResources[1].setSpeciesType(SpeciesType.LUDZIE);
        playerResources[2].setSpeciesType(SpeciesType.SZRUNGALE);
        for(int i = 0; i < playerResources.length; i++)
            playerResources[i].setSpeciesColor();

        /*System.out.println(freePlanetStorage);
        freePlanetStorage.remove(2);
        System.out.println(freePlanetStorage);*/

        ///TODO: Wygenerowanie jednego statku dla testow i wklejenie go na ekran poprzez
       /* boolean oneShipGenerated = false;
        for(int i = 0; i < MapController.HORIZONTAL_TILE_COUNT; i++){
            for(int j = 0; j < MapController.VERTICAL_TILE_COUNT; j++){
                if(MapController.board[i][j].getTileType() == TileType.EMPTY_SPACE){
                    oneShipGenerated = true;

                    MapController.board[i][j].setTileType(TileType.JAVALERERS_EXPLORER_SHIP);

                    ExplorerShip javExplorerShip = new ExplorerShip();
                    javExplorerShip.imageView = new ImageView();

                    javExplorerShip.imageView.setFitWidth(MapController.TILE_SIZE);
                    javExplorerShip.imageView.setFitHeight(MapController.TILE_SIZE);
                    javExplorerShip.imageView.relocate(i * MapController.TILE_SIZE, j * MapController.TILE_SIZE);

                    javExplorerShip.imageView.setImage(new Image(String.valueOf(getClass().getResource("arts\\Javalerzy_textures\\Javalerzy_explorer_ship.png"))));
                    MapController.staticPane.getChildren().add(javExplorerShip.imageView);
                    javExplorerShip.position.x =i;
                    javExplorerShip.position.y =j;
                    playerResources[0].getUnitBuilData().getUnitstorage().add(javExplorerShip);
                }
                if(oneShipGenerated) break;
            }
            if(oneShipGenerated) break;
        }
        System.out.println("TEST");
        StatusandDirection direction =  StatusandDirection.RIGHT;
        int a =0;
        while(true) {

          //  for (int i = 0; i < 100000000; i++) ;

       //     movement(playerResources[0].getUnitBuilData().getUnitstorage().get(0), direction,playerResources[0].getUnitBuilData().getUnitstorage().get(0).position.x+2,playerResources[0].getUnitBuilData().getUnitstorage().get(0).position.y+3);

            a++;
            if (a ==5){
                break;
            }
            //break;
        }*/
    }
}
