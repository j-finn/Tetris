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
        int margin = 2;
        graphics2D.setColor(colour);
        graphics2D.fillRect(x + margin , y + margin, SIZE - (margin * 2), SIZE - (margin * 2));
    }
}