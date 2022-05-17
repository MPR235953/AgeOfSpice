package app.ageofspice.units_classes;

import app.ageofspice.Resourcesandcosts.Cost;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.ImageView;


/**
 * Klasa abstrakcyjna z której beda dziedziczyć wszystkie klasy jednostek
 * Dodawać metody i zmienne tu
 *
 */

//statystyki randomowo dałem
//można zmienic do jednostek
// TODO: 16.05.2022 Zmienic ilosci przy zmiennych(baseHP itp) oraz zastanowic sie czy nad ostatecznym wygladem tych klas
// TODO: 16.05.2022 Przemyslec i potestowac stworzone klasy
// TODO: 16.05.2022 Przemyslec czy bawimy sie w armor czy nie
//Koszt zmienic o klase zawierającą koszty
public abstract class unit {
    public ImageView imageView;
    public TileType shipType;
    public int baseHP;
    public int actualHP;
    public int movementSpeed;
    public int movementSpeedleft;
    public int baseDMG;
    public Cost baseCost;
    public ActualPosition position;

}
