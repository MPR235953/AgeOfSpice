package app.ageofspice.Buildings;

import app.ageofspice.Planet;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;

/**
 * Zwiększa wydobycie surowca z planety
 */

public class MineStation extends absBuilding{
    public static Cost staticBaseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));

    public MineStation(ActualPosition newPosition, UnitsStorage playerStorage){
        baseHP = 40;
        actualHP = baseHP;
        buildType = TileType.MINE_STATION;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));
    }


    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.getBuildingstorage().remove(this);
    }
}
