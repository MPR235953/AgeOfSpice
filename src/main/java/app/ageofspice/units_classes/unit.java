package app.ageofspice.units_classes;

import app.ageofspice.MapController;
import app.ageofspice.Resourcesandcosts.Cost;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import static app.ageofspice.MapController.SAS_SCALE;
import static app.ageofspice.MapController.SAS_SCALE_POS;


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
    public ImageView imageView ;
    public TileType shipType ;
    public int baseHP;
    public int actualHP;
    public int movementSpeed;
    public int movementSpeedleft;
    public int baseDMG;
    public Cost baseCost;
    public ActualPosition position =new ActualPosition();

    public void imageviewconstructor(String imview){
        this.imageView = new ImageView();
        this.imageView.setFitHeight(MapController.TILE_SIZE * SAS_SCALE);
        this.imageView.setFitWidth(MapController.TILE_SIZE * SAS_SCALE);
        this.imageView.relocate(position.x * MapController.TILE_SIZE + SAS_SCALE_POS, position.y * MapController.TILE_SIZE + SAS_SCALE_POS);
        this.imageView.setImage(new Image(new File(imview).toURI().toString()));
        MapController.staticPane.getChildren().add(this.imageView);

    }
}
