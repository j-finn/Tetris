package main.java.tetromino;

import java.awt.*;

public class Block extends Rectangle {

    public int x, y;
    public static final int SIZE = 30; // 30x30 block
    public Color colour;


    public Block(Color colour) {
        this.colour = colour;
    }


    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(colour);
        graphics2D.fillRect(x, y, SIZE, SIZE);
    }
}