package app.ageofspice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class MapController implements Initializable {

    public static final int STROKE_TILE_WIDTH = 3;

    //################################################  Rozmiary kafelkow
    public static final int TILE_SIZE = 70;
    public static final int HORIZONTAL_TILE_COUNT = AgeOfSpiceApp.SCREEN_WIDTH / TILE_SIZE;
    public static final int VERTICAL_TILE_COUNT = (AgeOfSpiceApp.SCREEN_HEIGHT - AgeOfSpiceApp.FRAME_SIZE) / TILE_SIZE;

    //################################################ Poczatkowe pozycje statkow macierzytych dla ras
    public static final int JAV_X = 0, JAV_Y = 1;
    public static final int LUD_X = HORIZONTAL_TILE_COUNT / 2, LUD_Y = VERTICAL_TILE_COUNT - 1;
    public static final int SZR_X = HORIZONTAL_TILE_COUNT - 1, SZR_Y = 1;

    //################################################  Okreslenie liczby generowanych planet
    public static final int ALGA_QUANTITY = 3;
    public static final int VIBRANIUM_QUANTITY = 3;
    public static final int CRYSTAL_QUANTITY = 3;
    public static final int SPICE_QUANTITY = 3;
    public static final int PLANET_QUANTITY = ALGA_QUANTITY + VIBRANIUM_QUANTITY + CRYSTAL_QUANTITY + SPICE_QUANTITY;

    //###############################################  Ile jest juz planet na mapie
    private int algaOnMap = 0;
    private int vibraniumOnMap = 0;
    private int spiceOnMap = 0;
    private int crystalOnMap = 0;

    @FXML private AnchorPane anchorPane;
    @FXML private Pane pane;
    ImageView background = new ImageView();
    Group tileGroup = new Group();                                      //###### Grupy kafelkow, planet i stacji
    Group planetGroup = new Group();
    Group parentalStationGroup = new Group();
    public static Tile[][] board = new Tile[HORIZONTAL_TILE_COUNT][VERTICAL_TILE_COUNT];          //##### Tablica kafelkow

    public Pane getPane(){ return this.pane; }

    public static Pane notBozyPane;

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

        pane.getChildren().addAll(tileGroup, planetGroup, parentalStationGroup);                    //######  Wyswietlenie wszystkiego na scenie

        //generacja kafelkow
        for(int y = 0; y < VERTICAL_TILE_COUNT; y++){
            for(int x = 0; x < HORIZONTAL_TILE_COUNT; x++){

                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);

                board[x][y] = tile;

            }
        }

        /// TODO: Zamiana kolorow na enumy z klasy PlayerResources ..., dodanie denerowanych obiektow do tej klasy
        //generacja stacji macierzystych kazdej z ras (stale polozenie)
        board[JAV_X][JAV_Y].setTileType(TileType.JAV_PARENTAL_STATION);
        board[JAV_X][JAV_Y].setStroke(Color.rgb(0,230,250));
        board[JAV_X][JAV_Y].setStrokeWidth(STROKE_TILE_WIDTH);
        ParentalStation javParentalStation = new ParentalStation(JAV_X, JAV_Y, TileType.JAV_PARENTAL_STATION);

        board[LUD_X][LUD_Y].setTileType(TileType.LUD_PARENTAL_STATION);
        board[LUD_X][LUD_Y].setStroke(Color.rgb(221, 44,0));
        board[LUD_X][LUD_Y].setStrokeWidth(STROKE_TILE_WIDTH);
        ParentalStation ludParentalStation = new ParentalStation(LUD_X, LUD_Y, TileType.LUD_PARENTAL_STATION);

        board[SZR_X][SZR_Y].setTileType(TileType.SZR_PARENTAL_STATION);
        board[SZR_X][SZR_Y].setStroke(Color.rgb(233,30,98));
        board[SZR_X][SZR_Y].setStrokeWidth(STROKE_TILE_WIDTH);
        ParentalStation szrParentalStation = new ParentalStation(SZR_X, SZR_Y, TileType.SZR_PARENTAL_STATION);

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


        ///TODO:   Ostra fuszera jest tu wlasnie robiona xd
        notBozyPane = pane;
        GameLoop gameLoop = new GameLoop();
        gameLoop.startGame();

    }
}
