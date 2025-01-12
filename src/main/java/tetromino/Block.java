package main.java.tetromino;

import java.awt.*;
import java.util.Objects;

public class Block extends Rectangle {

    public int x, y;
    public static final int SIZE = 30; // 30x30 block
    public Color colour;


    public Block(Color colour) {
        this.colour = colour;
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