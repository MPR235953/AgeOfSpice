package app.ageofspice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TileType {
    //TU NIE ZMIENIAC NIC, FUNKCJE PONIZEJ KORZYSTAJA Z TYCHE ENUMOW
    EMPTY_SPACE,

    ALGA_PLANET,            //1
    VIBRANIUM_PLANET,
    CRYSTAL_PLANET,
    SPICE_PLANET,

    JAV_PARENTAL_STATION,
    LUD_PARENTAL_STATION,
    SZR_PARENTAL_STATION,

    //TUTAJ ROB CO CHCESZ

    DESTROYER_SHIP,
    EXPLORER_SHIP,
    SCOUT_SHIP,
    DRED_SHIP,

    NORMAL_SHIP,
    FRIENDLY_SHIP,
    ENEMY_SHIP,

    FRIENDLY_STRUCTURE,
    ENEMY_STRUCTURE,

    JAVALERERS_DESTROYER_SHIP,
    JAVALERERS_EXPLORER_SHIP,
    JAVALERERS_SCOUT_SHIP,
    JAVALERERS_DRED_SHIP,

    PEOPLE_DESTROYER_SHIP,
    PEOPLE_EXPLORER_SHIP,
    PEOPLE_SCOUT_SHIP,
    PEOPLE_DRED_SHIP,

    SZRUNGALS_DESTROYER_SHIP,
    SZRUNGALS_EXPLORER_SHIP,
    SZRUNGALS_SCOUT_SHIP,
    SZRUNGALS_DRED_SHIP,


    JAVALERERS_MINE_STATION,
    JAVALERERS_LAB_STATION,
    JAVALERERS_WAR_STATION,


    PEOPLE_MINE_STATION,
    PEOPLE_LAB_STATION,
    PEOPLE_WAR_STATION,

    SZRUNGALS_MINE_STATION,
    SZRUNGALS_LAB_STATION,
    SZRUNGALS_WAR_STATION;

    private static final List<TileType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    private static final int PLANETS_START = 1;
    private static final int PARENTAL_STATIONS_START = 5;

    private static final int PLANETS_QUANTITY = 4;
    private static final int PARENTAL_STATIONS_QUANTITY = 3;

    //private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static TileType randomPlanetType()  {
        return VALUES.get(RANDOM.nextInt(PLANETS_QUANTITY) + PLANETS_START);
    }

    public static TileType randomParentalStationType()  {
        return VALUES.get(RANDOM.nextInt(PARENTAL_STATIONS_QUANTITY) + PARENTAL_STATIONS_START);
    }
}
