package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.ActualPosition;

/**
 * Daje jednostkom bonus do ataku
 */

public class WarStation extends absBuilding{
    public ActualPosition position;

    public WarStation(ActualPosition newPosition, UnitsStorage playerStorage){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(20));

        position = newPosition;

        playerStorage.bonusAttack += 20;
        playerStorage.getBuildingstorage().add(this);
    }

    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.bonusAttack -= 20;
        playerStorage.getBuildingstorage().remove(this);
    }
}
