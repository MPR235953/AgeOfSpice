package app.ageofspice.Buildings;

import app.ageofspice.Resourcesandcosts.Cost;
import app.ageofspice.TileType;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.ImageView;


/**
 * podobnie jak jednostki głowna klasa abstrakyjna
 * budynki dają różne bonusy
 */

/* TODO: 16.05.2022 Budowa tej klasy
    * kupowanie + budowanie
    * bonusy: -wojskowa zwiększa bonus do ataku
    * bycie atakowanym
*/

public abstract class absBuilding {
    //public ImageView imageView;
    public TileType buildType;
    public int baseHP;
    public int actualHP;
    public Cost baseCost;
    public ActualPosition pos;

    /**
     * usuwa z gry bonusy tego budynku
     */
    abstract void destroy(UnitsStorage playerStorage);

    void changeHP(int change) {
        actualHP = Math.min(actualHP + change, baseHP);
    }
}
