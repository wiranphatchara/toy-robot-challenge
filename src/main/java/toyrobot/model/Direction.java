package toyrobot.model;

import lombok.Getter;

@Getter
public enum Direction {
    //2 Dimension for direction that robot can move
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        //E,W = deltaX
        this.deltaX = deltaX;
        //N,S = deltaY
        this.deltaY = deltaY;
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    public Direction turnRight() {
        //turn right = clockwise
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
}