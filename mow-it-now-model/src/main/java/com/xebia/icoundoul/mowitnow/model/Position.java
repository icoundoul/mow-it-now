package com.xebia.icoundoul.mowitnow.model;

/**
 * A position is a combination of a location (x and y coordinates) and of an orientation (North, South, East, West).
 */
public class Position {

    private int x;

    private int y;

    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "" + x + " " + y + " " + orientation;
    }

}
