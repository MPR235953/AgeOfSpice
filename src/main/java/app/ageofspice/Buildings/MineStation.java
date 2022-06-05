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
    public static Cost staticBaseCost = new Cost(new AlgiRes(10),new SpiceRes(10),new VibraniumRes(2),new CrystalRes(30));

    public MineStation(ActualPosition newPosition, UnitsStorage playerStorage){
        baseHP = 100;
        actualHP = baseHP;
        buildType = TileType.MINE_STATION;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(10),new VibraniumRes(2),new CrystalRes(30));
        pos = newPosition;
    }


    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.getBuildingstorage().remove(this);
    }
}
