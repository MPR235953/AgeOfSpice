package app.ageofspice.movement;

import app.ageofspice.units_classes.unit;

import java.lang.runtime.SwitchBootstraps;

public enum StatusandDirection {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3),
    NONE(-1),

    STATUS_OK(4),
    STATUS_ENEMY(5),
    STATUS_WRONG_TILE(6);
    int status;

    StatusandDirection(int statusandDirection){
        this.status = statusandDirection;

    }
    StatusandDirection(StatusandDirection statusandDirection){
        this.status = statusandDirection.status;

    }
}
