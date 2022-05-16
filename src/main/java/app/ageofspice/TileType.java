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

    WAR_SHIP,
    EXPLORER_SHIP,
    NORMAL_SHIP,

    MINE_STATION,
    LAB_STATION,
    WAR_STATION;

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
