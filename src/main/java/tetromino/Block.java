package main.java.tetromino;

import java.awt.*;
import java.util.Objects;

public class Block extends Rectangle implements Cloneable {

    public int x, y; // FIXME: Make private.
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


    public void draw(Graphics2D graphics2D) {
        int margin = 2;
        graphics2D.setColor(colour);
        graphics2D.fillRect(x + margin , y + margin, SIZE - (margin * 2), SIZE - (margin * 2));
    }


    public void drawOutline(Graphics2D graphics2D) {
        graphics2D.setColor(colour);
        graphics2D.fillRect(x, y, Block.SIZE, Block.SIZE);
        graphics2D.fillRect(x, y, Block.SIZE, Block.SIZE);
        graphics2D.fillRect(x, y, Block.SIZE, Block.SIZE);
        graphics2D.fillRect(x, y, Block.SIZE, Block.SIZE);

        int margin = 2;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(x + margin, y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(x + margin, y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(x + margin, y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(x + margin, y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
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


    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
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