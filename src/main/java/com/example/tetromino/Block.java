package com.example.tetromino;

import java.awt.*;
import java.util.Objects;

public class Block extends Rectangle implements Cloneable {

    private int x, y;
    public static final int SIZE = 30; // 30x30 block
    private Color colour;


    public Block(Color colour) {
        this.colour = colour;
    }


    public Block(Block other) {
        this.x = other.x;
        this.y = other.y;
        this.width = other.width;
        this.height = other.height;
        this.colour = other.colour;
    }


    public Color getColour() {
        return colour;
    }


    public int getBlockX() {
        return x;
    }


    public int getBlockY() {
        return y;
    }


    public void setBlockX(int x) {
        this.x = x;
    }


    public void setBlockY(int y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Block block = (Block) o;
        return x == block.x && y == block.y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), x, y);
    }
}