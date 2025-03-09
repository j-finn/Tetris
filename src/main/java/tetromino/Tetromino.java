package main.java.tetromino;

import java.awt.*;

public abstract class Tetromino implements Cloneable {

    Block[] blocks = new Block[4];

    public int direction = 1; // there are 4 directions (1/2/3/4)
    private boolean isMinoActive = true;
    private boolean isBlockDeactivating = false;


    public Block[] getBlocks() {
        return blocks;
    }


    public Color getMinoColor() {
        return blocks[0].getColour();
    }


    public void create(Color c) {
        blocks[0] = new Block(c);
        blocks[1] = new Block(c);
        blocks[2] = new Block(c);
        blocks[3] = new Block(c);
    }


    @Override
    public Tetromino clone() {
        try {
            Tetromino clonedTetromino = (Tetromino) super.clone();

            clonedTetromino.blocks = new Block[4];
            for (int i = 0; i < blocks.length; i++) {
                clonedTetromino.blocks[i] = new Block(this.blocks[i]);
            }

            return clonedTetromino;
        } catch (CloneNotSupportedException e) {
            // should be impossible but this is a checked exception and I don't want to propagate it
            throw new IllegalArgumentException(this.getClass() + " does not implement cloneable.");
        }
    }


    public abstract void setXY(int x, int y);


    public boolean isActive() {
        return isMinoActive;
    }


    public void setMinoActive(boolean minoActive) {
        isMinoActive = minoActive;
    }


    public Tetromino rotatePosition() {
        direction = (direction % 4);
        return rotateToPosition(direction);
    }


    abstract Tetromino rotateToPosition(int number);


    public Tetromino moveRight() {
        blocks[0].setBlockX(blocks[0].getBlockX() + Block.SIZE);
        blocks[1].setBlockX(blocks[1].getBlockX() + Block.SIZE);
        blocks[2].setBlockX(blocks[2].getBlockX() + Block.SIZE);
        blocks[3].setBlockX(blocks[3].getBlockX() + Block.SIZE);

        return this;
    }


    public Tetromino moveLeft() {
        blocks[0].setBlockX(blocks[0].getBlockX() - Block.SIZE);
        blocks[1].setBlockX(blocks[1].getBlockX() - Block.SIZE);
        blocks[2].setBlockX(blocks[2].getBlockX() - Block.SIZE);
        blocks[3].setBlockX(blocks[3].getBlockX() - Block.SIZE);

        return this;
    }


    public Tetromino moveDown() {
        blocks[0].setBlockY(blocks[0].getBlockY() + Block.SIZE);
        blocks[1].setBlockY(blocks[1].getBlockY() + Block.SIZE);
        blocks[2].setBlockY(blocks[2].getBlockY() + Block.SIZE);
        blocks[3].setBlockY(blocks[3].getBlockY() + Block.SIZE);

        return this;
    }


    public Tetromino moveUp() {
        blocks[0].setBlockY(blocks[0].getBlockY() - Block.SIZE);
        blocks[1].setBlockY(blocks[1].getBlockY() - Block.SIZE);
        blocks[2].setBlockY(blocks[2].getBlockY() - Block.SIZE);
        blocks[3].setBlockY(blocks[3].getBlockY() - Block.SIZE);

        return this;
    }


    public boolean isBlockDeactivating() {
        return isBlockDeactivating;
    }


    public void setBlockDeactivating(boolean blockDeactivating) {
        isBlockDeactivating = blockDeactivating;
    }
}