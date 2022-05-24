package app.ageofspice.Buildings;

import app.ageofspice.Planet;
import app.ageofspice.Resourcesandcosts.*;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import javafx.scene.image.Image;

/**
 * Zwiększa wydobycie surowca z planety
 */

public class MineStation extends absBuilding{
    AbstractResource minedResource;

    public MineStation(Planet planet, UnitsStorage playerStorage){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));

        switch(planet.planetType){
            case ALGA_PLANET -> minedResource = new AlgiRes(10);
            case VIBRANIUM_PLANET -> minedResource = new VibraniumRes(10);
            case CRYSTAL_PLANET -> minedResource = new CrystalRes(10);
            case SPICE_PLANET -> minedResource = new SpiceRes(10);
        }

        playerStorage.getBuildingstorage().add(this);
    }


    @Override
    void destroy(UnitsStorage playerStorage) {
        playerStorage.getBuildingstorage().remove(this);
    }
}
