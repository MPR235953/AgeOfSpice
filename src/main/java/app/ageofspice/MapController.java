package app.ageofspice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class MapController implements Initializable {

    //################################################  Rozmiary kafelkow
    public static final int TILE_SIZE = 70;
    public static final int HORIZONTAL_TILE_COUNT = AgeOfSpiceApp.SCREEN_WIDTH / TILE_SIZE;
    public static final int VERTICAL_TILE_COUNT = (AgeOfSpiceApp.SCREEN_HEIGHT - AgeOfSpiceApp.FRAME_SIZE) / TILE_SIZE;

    //################################################  Okreslenie liczby generowanych planet
    public static final int ALGA_QUANTITY = 5;
    public static final int VIBRANIUM_QUANTITY = 5;
    public static final int CRYSTAL_QUANTITY = 5;
    public static final int SPICE_QUANTITY = 10;
    public static final int PLANET_QUANTITY = ALGA_QUANTITY + VIBRANIUM_QUANTITY + CRYSTAL_QUANTITY + SPICE_QUANTITY;

    private int algaOnMap = 0;
    private int vibraniumOnMap = 0;
    private int spiceOnMap = 0;
    private int crystalOnMap = 0;

    @FXML private AnchorPane anchorPane;
    @FXML private Pane pane;
    ImageView background = new ImageView();
    Group tileGroup = new Group();
    Group planetGroup = new Group();
    Group parentalStationGroup = new Group();
    Tile[][] board = new Tile[HORIZONTAL_TILE_COUNT][VERTICAL_TILE_COUNT];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.setPrefWidth(AgeOfSpiceApp.SCREEN_WIDTH);
        anchorPane.setPrefHeight(AgeOfSpiceApp.SCREEN_HEIGHT);

        pane.getChildren().add(background);
        pane.setPrefWidth(AgeOfSpiceApp.SCREEN_WIDTH);
        pane.setPrefHeight(AgeOfSpiceApp.SCREEN_HEIGHT - AgeOfSpiceApp.FRAME_SIZE);
        pane.relocate(0, AgeOfSpiceApp.FRAME_SIZE);

        background.setImage(new Image(String.valueOf(getClass().getResource("arts\\space_map.png"))));
        background.setFitWidth(AgeOfSpiceApp.SCREEN_WIDTH);
        background.setFitHeight(AgeOfSpiceApp.SCREEN_HEIGHT );  //- AgeOfSpiceApp.FRAME_SIZE);

        tileGroup.setStyle("-fx-view-order: -5;");      //z-index im mniejszy tym obiekt jest blizej ekranu
        pane.getChildren().addAll(tileGroup, planetGroup, parentalStationGroup);

        //generacja kafelkow
        for(int y = 0; y < VERTICAL_TILE_COUNT; y++){
            for(int x = 0; x < HORIZONTAL_TILE_COUNT; x++){

                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);

                board[x][y] = tile;

            }
        }

        //generacja stacji macierzystych kazdej z ras (stale polozenie)
        board[1][1].setTileType(TileType.JAV_PARENTAL_STATION);
        ParentalStation javParentalStation = new ParentalStation(1,1,TileType.JAV_PARENTAL_STATION);

        board[HORIZONTAL_TILE_COUNT / 2][VERTICAL_TILE_COUNT - 1].setTileType(TileType.LUD_PARENTAL_STATION);
        ParentalStation ludParentalStation = new ParentalStation(HORIZONTAL_TILE_COUNT / 2,VERTICAL_TILE_COUNT - 1,TileType.LUD_PARENTAL_STATION);

        board[HORIZONTAL_TILE_COUNT - 1][1].setTileType(TileType.SZR_PARENTAL_STATION);
        ParentalStation szrParentalStation = new ParentalStation(HORIZONTAL_TILE_COUNT - 1,1, TileType.SZR_PARENTAL_STATION);

        parentalStationGroup.getChildren().addAll(javParentalStation, ludParentalStation, szrParentalStation);

        //generacja planet
        for(int i = 0; i < PLANET_QUANTITY; i++){
            Random random = new Random();

            int randX = random.nextInt(HORIZONTAL_TILE_COUNT);
            int randY = random.nextInt(VERTICAL_TILE_COUNT);

            if(board[randX][randY].getTileType() != TileType.EMPTY_SPACE) continue;

            boolean again;
            TileType parentalStationType;
            do {
                again = false;
                parentalStationType = TileType.randomPlanetType();
                if(parentalStationType == TileType.ALGA_PLANET && algaOnMap >= ALGA_QUANTITY) again = true;
                else if(parentalStationType == TileType.VIBRANIUM_PLANET && vibraniumOnMap >= VIBRANIUM_QUANTITY) again = true;
                else if(parentalStationType == TileType.CRYSTAL_PLANET && crystalOnMap >= CRYSTAL_QUANTITY) again = true;
                else if(parentalStationType == TileType.SPICE_PLANET && spiceOnMap >= SPICE_QUANTITY) again = true;

                switch(parentalStationType){
                    case ALGA_PLANET -> algaOnMap++;
                    case VIBRANIUM_PLANET -> vibraniumOnMap++;
                    case CRYSTAL_PLANET -> crystalOnMap++;
                    case SPICE_PLANET -> spiceOnMap++;
                }

            }while(again);


            board[randX][randY].setTileType(parentalStationType);
            Planet planet = new Planet(randX, randY, parentalStationType);
            planetGroup.getChildren().add(planet);
        }

    }
}
