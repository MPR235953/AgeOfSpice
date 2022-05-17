package app.ageofspice;

import app.ageofspice.UnitandBuildingStorage.PlayerResourcesandUnitsStorage;
import app.ageofspice.units_classes.ExplorerShip;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLoop{
    PlayerResourcesandUnitsStorage[] playerResources = {    new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage()
                                                        };

    public void startGame() {

        ///TODO: Sakowicz nie krzywdz pls ;(
        boolean oneShipGenerated = false;
        for(int i = 0; i < MapController.HORIZONTAL_TILE_COUNT; i++){
            for(int j = 0; j < MapController.VERTICAL_TILE_COUNT; j++){
                if(MapController.board[i][j].getTileType() == TileType.EMPTY_SPACE && !oneShipGenerated){
                    oneShipGenerated = true;

                    MapController.board[i][j].setTileType(TileType.JAVALERERS_EXPLORER_SHIP);

                    ExplorerShip javExplorerShip = new ExplorerShip();
                    javExplorerShip.imageView = new ImageView();

                    javExplorerShip.imageView.setFitWidth(MapController.TILE_SIZE);
                    javExplorerShip.imageView.setFitHeight(MapController.TILE_SIZE);
                    javExplorerShip.imageView.relocate(i * MapController.TILE_SIZE, j * MapController.TILE_SIZE);

                    javExplorerShip.imageView.setImage(new Image(String.valueOf(getClass().getResource("arts\\Javalerzy_textures\\Javalerzy_explorer_ship.png"))));
                    MapController.notBozyPane.getChildren().add(javExplorerShip.imageView);
                }
            }
        }

        System.out.println("TEST");

        for(int i = 0; i < playerResources.length; i++){

        }
    }
}
