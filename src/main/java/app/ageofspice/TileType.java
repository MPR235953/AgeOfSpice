package app.ageofspice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TileType {
    ALGA_PLANET,            //0
    VIBRANIUM_PLANET,
    CRYSTAL_PLANET,
    SPICE_PLANET,

    WAR_SHIP,               //4
    EXPLORER_SHIP,
    NORMAL_SHIP,

    PARENTAL_STATION,       //7
    MINE_STATION,
    LAB_STATION,
    WAR_STATION;

    private static final List<TileType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    private static final int PLANETS_START = 0;
    private static final int SHIPS_START = 4;
    private static final int STATIONS_START = 7;

    private static final int PLANETS_QUANTITY = 4;
    private static final int SHIPS_QUANTITY = 3;
    private static final int STATIONS_QUANTITY = 4;

    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static TileType randomPlanetType()  {
        return VALUES.get(RANDOM.nextInt(PLANETS_QUANTITY) + PLANETS_START);
    }

    public static TileType randomShipType()  {
        return VALUES.get(RANDOM.nextInt(SHIPS_QUANTITY) + SHIPS_START);
    }

    public static TileType randomStationType()  {
        return VALUES.get(RANDOM.nextInt(STATIONS_QUANTITY) + STATIONS_START);
    }
}
