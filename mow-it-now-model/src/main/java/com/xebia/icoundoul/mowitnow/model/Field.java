package com.xebia.icoundoul.mowitnow.model;

/**
 * A field.
 * 
 * Note: the width and height start at 1.
 */
public class Field {

    private int width;

    private int height;

    public Field(int width, int height) {

        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Width and height must be >= 1");

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
