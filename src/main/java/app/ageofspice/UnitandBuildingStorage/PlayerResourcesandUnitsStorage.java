package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Resourcesandcosts.AlgiRes;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Resourcesandcosts.VibraniumRes;
import app.ageofspice.SpeciesType;
import app.ageofspice.units_classes.unit;

/**
 * Głowna klasa do obsługi gracza.
 * Posiada informacje o jednostkach, budynkach,zasobach gracza.
 * WAZNA
 */

/// TODO: 16.05.2022 Rozbudowa i zastanowienie sie nad kolejnymi polami klasy (status gracza,nacja,żyje czy nie itp.)
/// TODO: 17.05.2022 Funkcja endturaction do rozbudowy. Ma zawierać wszystkie akcje dziejące się na koniec tury(zasoby itp)
/// TODO: 17.05.2022 Buyunits funkcja dokonczyc spawnowanie sie statkow na mapi i przemysleć kod

public class PlayerResourcesandUnitsStorage {


    private UnitsStorage unitBuilData;
    private ResourceStorage resources;
    private String playerName;
    private SpeciesType speciesType;

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public SpeciesType getSpeciesType() { return speciesType; }
    public void setSpeciesType(SpeciesType speciesType) { this.speciesType = speciesType; }
    public UnitsStorage getUnitBuilData() {
        return unitBuilData;
    }

    public void setUnitBuilData(UnitsStorage unitBuilData) {
        this.unitBuilData = unitBuilData;
    }

    public ResourceStorage getResources() {
        return resources;
    }

    public void setResources(ResourceStorage resources) {
        this.resources = resources;
    }

    void buyunits(unit unittobuy){
        if (resources.algi.quantity >= unittobuy.baseCost.algi.quantity && resources.wibranium.quantity >= unittobuy.baseCost.wibranium.quantity
         && resources.krysztal.quantity >= unittobuy.baseCost.krysztal.quantity && resources.przyprawa.quantity >= unittobuy.baseCost.przyprawa.quantity){
            resources.algi.quantity -= unittobuy.baseCost.algi.quantity;
            resources.wibranium.quantity -= unittobuy.baseCost.wibranium.quantity;
            resources.krysztal.quantity -= unittobuy.baseCost.krysztal.quantity;
            resources.przyprawa.quantity -= unittobuy.baseCost.przyprawa.quantity;
            //########Do rozważenia
                unitBuilData.getUnitstorage().add(unittobuy);
            //########
        }
    }


    //WAZNA
    public void endturactions(){
        unitBuilData.resetstats();
    }
}
