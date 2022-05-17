package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Resourcesandcosts.AlgiRes;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Resourcesandcosts.VibraniumRes;
import app.ageofspice.SpeciesType;
import javafx.scene.paint.Color;

/**
 * Głowna klasa do obsługi gracza.
 * Posiada informacje o jednostkach, budynkach,zasobach gracza.
 * WAZNA
 */

/// TODO: 16.05.2022 Rozbudowa i zastanowienie sie nad kolejnymi polami klasy (status gracza,nacja,żyje czy nie itp.)


public class PlayerResourcesandUnitsStorage {
    private UnitsStorage unitBuilData;
    private ResourceStorage resources;
    private String playerName;
    private SpeciesType speciesType = SpeciesType.NONE;
    private Color speciesColor = Color.BLACK;

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public SpeciesType getSpeciesType() { return speciesType; }
    public void setSpeciesType(SpeciesType speciesType) { this.speciesType = speciesType; }

    public Color getSpeciesColor(){ return this.speciesColor; }
    public void setSpeciesColor(){
        switch(this.speciesType){
            case JAVALERZY -> this.speciesColor = Color.rgb(0,230,250);
            case LUDZIE -> this.speciesColor = Color.rgb(221, 44,0);
            case SZRUNGALE -> this.speciesColor = Color.rgb(233,30,98);
        }
    }

}
