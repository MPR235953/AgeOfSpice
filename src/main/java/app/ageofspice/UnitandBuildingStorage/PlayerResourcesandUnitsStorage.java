package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Resourcesandcosts.AlgiRes;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Resourcesandcosts.VibraniumRes;
import app.ageofspice.SpeciesType;

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
    private SpeciesType speciesType;

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public SpeciesType getSpeciesType() { return speciesType; }
    public void setSpeciesType(SpeciesType speciesType) { this.speciesType = speciesType; }
}
