package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.ActualPosition;

/**
 * Zmniejsza koszt budowy?
 */

//TODO: co robi ten budynek?

public class LabStation extends absBuilding{
    public ActualPosition position;

    public LabStation(ActualPosition newPosition, UnitsStorage playerStorage){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));

        position = newPosition;

        playerStorage.getBuildingstorage().add(this);
    }

    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.getBuildingstorage().remove(this);
    }
}
