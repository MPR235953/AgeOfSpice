package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.Cost;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.ImageView;


/**
 * podobnie jak jednostki głowna klasa abstrakyjna
 */

/// TODO: 16.05.2022 Budowa tej klasy

public abstract class absBuilding {
    public ImageView imageView;
    public TileType buildType;
    public int baseHP;
    public int actualHP;
    public Cost baseCost;

    /**
     * usuwa z gry bonusy tego budynku
     */
    abstract void destroy();

    void changeHP(int change) {
        actualHP = Math.min(actualHP + change, baseHP);
    }
}
