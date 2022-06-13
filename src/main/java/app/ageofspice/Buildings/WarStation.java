package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.TileType;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.ActualPosition;

/**
 * Daje jednostkom bonus do ataku +20
 */

public class WarStation extends absBuilding{
    public static Cost staticBaseCost = new Cost(new AlgiRes(10),new SpiceRes(15),new VibraniumRes(30),new CrystalRes(20));

    public WarStation(ActualPosition newPosition, UnitsStorage playerStorage){
        baseHP = 80;
        actualHP = baseHP;
        buildType = TileType.WAR_STATION;
        baseCost = new Cost(new AlgiRes(10),new SpiceRes(15),new VibraniumRes(30),new CrystalRes(20));
        pos = newPosition;
    }


    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.bonusAttack -= 20;
        playerStorage.getBuildingstorage().remove(this);
    }
}
