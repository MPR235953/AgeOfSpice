package app.ageofspice;

import app.ageofspice.Species.SpeciesType;
import app.ageofspice.UnitandBuildingStorage.PlayerResourcesandUnitsStorage;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.Windows.*;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.movement.StatusandDirection;
import app.ageofspice.units_classes.ExplorerShip;
import app.ageofspice.units_classes.Lasers;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static app.ageofspice.UnitandBuildingStorage.UnitsStorage.movement;

public class GameLoop{
    public static PlayerResourcesandUnitsStorage[] playerResources = {    new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage(),
                                                            new PlayerResourcesandUnitsStorage()
                                                        };
    public static ArrayList<BuildWinForBuildings> arrayBuildWinForBuildings = new ArrayList<BuildWinForBuildings>();
    public static ArrayList<BuildWinForParentalStation> arrayBuildWinForParentalStation = new ArrayList<BuildWinForParentalStation>();
    public static ArrayList<OnClickSpaceWin> arrayOnClickSpaceWin = new ArrayList<OnClickSpaceWin>();
    public static ArrayList<OnClickSpaceWinForUnits> arrayOnClickSpaceWinForUnits = new ArrayList<OnClickSpaceWinForUnits>();
    public static ArrayList<OnClickSpaceWinForBuildings> arrayOnClickSpaceWinForBuildings = new ArrayList<OnClickSpaceWinForBuildings>();
    public static ArrayList<Lasers> laserArrayList = new ArrayList<Lasers>();
    public static int roundNumber = 1;
    public static int playerNumber = 0;
    public static int winner = 2;
    public static ArrayList<Planet> allPlanetStorage = new ArrayList<Planet>();
    public static ArrayList<ParentalStation> allParentalStationStorage = new ArrayList<ParentalStation>();
    public void startGame() {
        //Ustawienie tylow gatonkow graczy i odpowiadajace im kolory
        playerResources[0].setSpeciesType(SpeciesType.JAVALERZY);
        playerResources[1].setSpeciesType(SpeciesType.LUDZIE);
        playerResources[2].setSpeciesType(SpeciesType.SZRUNGALE);
        for(int i = 0; i < playerResources.length; i++)
            playerResources[i].setSpeciesColor();


        Lasers lasers = new Lasers();

        lasers.position = new ActualPosition();
        lasers.speciesType =SpeciesType.JAVALERZY;
        lasers.path = "src/main/resources/app/ageofspice/arts/laser_textures/laser_jav.png";
        laserArrayList.add(lasers);

        Lasers lasers1 = new Lasers();

        lasers1.position = new ActualPosition();
        lasers1.speciesType =SpeciesType.LUDZIE;
        lasers1.path = "src/main/resources/app/ageofspice/arts/laser_textures/laser_lud.png";
        laserArrayList.add(lasers1);

        Lasers lasers2 = new Lasers();

        lasers2.position = new ActualPosition();
        lasers2.speciesType =SpeciesType.SZRUNGALE;
        lasers2.path = "src/main/resources/app/ageofspice/arts/laser_textures/laser_szr.png";
        laserArrayList.add(lasers2);

        Explosion explosion = new Explosion();

    }
}
