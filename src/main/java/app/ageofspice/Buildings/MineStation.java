package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.*;

/**
 * Zwiększa wydobycie surowca z planety
 */

public class MineStation extends absBuilding{
    public MineStation(){
        baseHP = 40;
        actualHP = baseHP;
        baseCost = new Cost(new AlgiRes(20),new SpiceRes(20),new VibraniumRes(30),new CrystalRes(10));
    }

    @Override
    void destroy() {

    }
}
