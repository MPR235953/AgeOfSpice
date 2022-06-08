package app.ageofspice.UnitandBuildingStorage;

import app.ageofspice.Buildings.MineStation;
import app.ageofspice.Buildings.WarStation;
import app.ageofspice.Buildings.absBuilding;
import app.ageofspice.Planet;
import app.ageofspice.Resourcesandcosts.ResourceStorage;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.TileType;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.units_classes.*;
import javafx.scene.paint.Color;

import static app.ageofspice.Tile.clearFields;
import static app.ageofspice.Windows.OnClickSpaceWinForUnits.flagToMove;
import static app.ageofspice.Windows.OnClickSpaceWinForUnits.unitToMove;

/**
 * Głowna klasa do obsługi gracza.
 * Posiada informacje o jednostkach, budynkach,zasobach gracza.
 * WAZNA
 */


public class PlayerResourcesandUnitsStorage {


    private UnitsStorage unitBuilData = new UnitsStorage();
    private ResourceStorage resources = new ResourceStorage();
    private String playerName;
    private SpeciesType speciesType = SpeciesType.NONE;
    private Color speciesColor = Color.BLACK;
    public boolean Alive = true;
    //private

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


    public int enoughMoney(unit unittobuy){
        if (resources.algi.quantity >= unittobuy.baseCost.algi.quantity && resources.wibranium.quantity >= unittobuy.baseCost.wibranium.quantity
                && resources.krysztal.quantity >= unittobuy.baseCost.krysztal.quantity && resources.przyprawa.quantity >= unittobuy.baseCost.przyprawa.quantity) {
            resources.algi.quantity -= unittobuy.baseCost.algi.quantity;
            resources.wibranium.quantity -= unittobuy.baseCost.wibranium.quantity;
            resources.krysztal.quantity -= unittobuy.baseCost.krysztal.quantity;
            resources.przyprawa.quantity -= unittobuy.baseCost.przyprawa.quantity;
        return 1;
        }
        return 0;
    }

    public int enoughMoneyB(absBuilding buildingToBuy){
        if (resources.algi.quantity >= buildingToBuy.baseCost.algi.quantity && resources.wibranium.quantity >= buildingToBuy.baseCost.wibranium.quantity
                && resources.krysztal.quantity >= buildingToBuy.baseCost.krysztal.quantity && resources.przyprawa.quantity >= buildingToBuy.baseCost.przyprawa.quantity) {
            resources.algi.quantity -= buildingToBuy.baseCost.algi.quantity;
            resources.wibranium.quantity -= buildingToBuy.baseCost.wibranium.quantity;
            resources.krysztal.quantity -= buildingToBuy.baseCost.krysztal.quantity;
            resources.przyprawa.quantity -= buildingToBuy.baseCost.przyprawa.quantity;
            return 1;
        }
        return 0;
    }

    public int buyunits(TileType type, ActualPosition pos){

        switch (type) {
            case SCOUT_SHIP -> {
                ScoutShip scout = new ScoutShip();
                scout.position = pos;
                if (enoughMoney(scout) == 0)
                    return -1;
                switch (speciesType) {
                    case LUDZIE -> scout.imageviewconstructor("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_scout.png");
                    case JAVALERZY -> scout.imageviewconstructor("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_scout_ship.png");
                    case SZRUNGALE -> scout.imageviewconstructor("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_scout.png");
                }
                this.unitBuilData.getUnitstorage().add(scout);
            }
            case DESTROYER_SHIP -> {
                DestroyerShip destroyerShip = new DestroyerShip();
                destroyerShip.position = pos;
                if (enoughMoney(destroyerShip) == 0)
                    return -1;
                switch (speciesType) {
                    case LUDZIE -> destroyerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_destroyer.png");
                    case JAVALERZY -> destroyerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_sniper_ship.png");
                    case SZRUNGALE -> destroyerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_destroyer.png");
                }
                this.unitBuilData.getUnitstorage().add(destroyerShip);
            }
            case DRED_SHIP -> {
                DredShip dredShip = new DredShip();
                dredShip.position = pos;
                if (enoughMoney(dredShip) == 0)
                    return -1;
                switch (speciesType) {
                    case LUDZIE -> dredShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_dreadnought.png");
                    case JAVALERZY -> dredShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_tank_ship.png");
                    case SZRUNGALE -> dredShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_dreadnought.png");
                }
                this.unitBuilData.getUnitstorage().add(dredShip);
            }
            case EXPLORER_SHIP -> {
                ExplorerShip explorerShip = new ExplorerShip();
                explorerShip.position = pos;
                if (enoughMoney(explorerShip) == 0)
                    return -1;
                switch (speciesType) {
                    case LUDZIE -> explorerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_explorer_ship.png");
                    case JAVALERZY -> explorerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_explorer_ship.png");
                    case SZRUNGALE -> explorerShip.imageviewconstructor("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_explorer.png");
                }
                this.unitBuilData.getUnitstorage().add(explorerShip);
            }
        }

        return  0;
    }

    public absBuilding buyBuilding(TileType type, ActualPosition pos){
        absBuilding building;
        switch (type) {
            case MINE_STATION -> {
                building = new MineStation(pos, unitBuilData);
                if (enoughMoneyB(building) == 0)
                    return null;
                unitBuilData.getBuildingstorage().add(building);
            }
            case WAR_STATION -> {
                building = new WarStation(pos, unitBuilData);
                if (enoughMoneyB(building) == 0)
                    return null;
                unitBuilData.bonusAttack += 20;
                unitBuilData.getBuildingstorage().add(building);
            }
            default -> {
                return null;
            }
        }
        return building;
    }


    //WAZNA
    public void endturactions(){
        unitBuilData.resetstats();
        //resources.resourcesaction();
        unitBuilData.gatherResources(resources);
        if(flagToMove) {
            clearFields(unitToMove.position.x, unitToMove.position.y, unitToMove.movementSpeedleft);
            unitToMove = null;
            flagToMove = false;
        }
    }

    public Color getSpeciesColor(){ return this.speciesColor; }

    public void setSpeciesColor(){
        switch(this.speciesType){
            case JAVALERZY -> this.speciesColor = SpeciesColors.javColor;
            case LUDZIE -> this.speciesColor = SpeciesColors.ludColor;
            case SZRUNGALE -> this.speciesColor = SpeciesColors.szrColor;
        }
    }

}
