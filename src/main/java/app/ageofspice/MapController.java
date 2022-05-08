package app.ageofspice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class MapController implements Initializable {

    public static final int TILE_SIZE = 60;
    public static final int HORIZONTAL_TILE_COUNT = AgeOfSpiceApp.SCREEN_WIDTH / TILE_SIZE;
    public static final int VERTICAL_TILE_COUNT = AgeOfSpiceApp.SCREEN_HEIGHT / TILE_SIZE;

    public static final int ALGA_QUANTITY = 10;
    public static final int VIBRANIUM_QUANTITY = 10;
    public static final int CRYSTAL_QUANTITY = 10;
    public static final int SPICE_QUANTITY = 5;
    public static final int PLANET_QUANTITY = ALGA_QUANTITY + VIBRANIUM_QUANTITY + CRYSTAL_QUANTITY + SPICE_QUANTITY;

    private int algaOnMap = 0;
    private int vibraniumOnMap = 0;
    private int spiceOnMap = 0;
    private int crystalOnMap = 0;

    @FXML private AnchorPane anchorPane;
    ImageView background = new ImageView();
    Group tileGroup = new Group();
    Group planetGroup = new Group();
    Tile[][] board = new Tile[HORIZONTAL_TILE_COUNT][VERTICAL_TILE_COUNT];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        anchorPane.getChildren().add(background);
        background.setImage(new Image(String.valueOf(getClass().getResource("arts\\cosmos.jpg"))));

        tileGroup.setStyle("-fx-view-order: -5;");      //z-index im mniejszy tym obiekt jest blizej ekranu
        anchorPane.getChildren().addAll(tileGroup, planetGroup);

        for(int y = 0; y < VERTICAL_TILE_COUNT; y++){
            for(int x = 0; x < HORIZONTAL_TILE_COUNT; x++){

                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);

                board[x][y] = tile;

            }
        }

        for(int i = 0; i < PLANET_QUANTITY; i++){
            Random random = new Random();

            boolean again;
            TileType planetType;
            do {
                again = false;
                planetType = TileType.randomPlanetType();
                if(planetType == TileType.ALGA_PLANET && algaOnMap >= ALGA_QUANTITY) again = true;
                else if(planetType == TileType.VIBRANIUM_PLANET && vibraniumOnMap >= VIBRANIUM_QUANTITY) again = true;
                else if(planetType == TileType.CRYSTAL_PLANET && crystalOnMap >= CRYSTAL_QUANTITY) again = true;
                else if(planetType == TileType.SPICE_PLANET && spiceOnMap >= SPICE_QUANTITY) again = true;

                switch(planetType){
                    case ALGA_PLANET -> algaOnMap++;
                    case VIBRANIUM_PLANET -> vibraniumOnMap++;
                    case CRYSTAL_PLANET -> crystalOnMap++;
                    case SPICE_PLANET -> spiceOnMap++;
                }

            }while(again);

            int randX = random.nextInt(HORIZONTAL_TILE_COUNT);
            int randY = random.nextInt(VERTICAL_TILE_COUNT);

            board[randX][randY].setTileType(planetType);
            Planet planet = new Planet(randX, randY, planetType);
            planetGroup.getChildren().add(planet);
        }
    }
}
