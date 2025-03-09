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


    // TODO: Colour to live on the mino? or the block?
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
        switch (direction) {
            case 1:
                direction = (direction % 4) + 1;
                return rotatePosition2();
            case 2:
                direction = (direction % 4) + 1;
                return rotatePosition3();
            case 3:
                direction = (direction % 4) + 1;
                return rotatePosition4();
            case 4:
                direction = (direction % 4) + 1;
                return rotatePosition1();
            default:
                throw new IllegalStateException("Rotated position was out of bounds, should be" +
                  "in either 1, 2, 3 or 4, but was: " + direction);
        }
    }


    abstract Tetromino rotatePosition1();


    abstract Tetromino rotatePosition2();


    abstract Tetromino rotatePosition3();


    abstract Tetromino rotatePosition4();


    public Tetromino moveRight() {
        blocks[0].setX(blocks[0].getBlockX() + Block.SIZE);
        blocks[1].setX(blocks[1].getBlockX() + Block.SIZE);
        blocks[2].setX(blocks[2].getBlockX() + Block.SIZE);
        blocks[3].setX(blocks[3].getBlockX() + Block.SIZE);

        return this;
    }


    public Tetromino moveLeft() {
        blocks[0].setX(blocks[0].getBlockX() - Block.SIZE);
        blocks[1].setX(blocks[1].getBlockX() - Block.SIZE);
        blocks[2].setX(blocks[2].getBlockX() - Block.SIZE);
        blocks[3].setX(blocks[3].getBlockX() - Block.SIZE);

        return this;
    }


    public Tetromino moveDown() {
        blocks[0].setY(blocks[0].getBlockY() + Block.SIZE);
        blocks[1].setY(blocks[1].getBlockY() + Block.SIZE);
        blocks[2].setY(blocks[2].getBlockY() + Block.SIZE);
        blocks[3].setY(blocks[3].getBlockY() + Block.SIZE);

        return this;
    }


    public Tetromino moveUp() {
        blocks[0].setY(blocks[0].getBlockY() - Block.SIZE);
        blocks[1].setY(blocks[1].getBlockY() - Block.SIZE);
        blocks[2].setY(blocks[2].getBlockY() - Block.SIZE);
        blocks[3].setY(blocks[3].getBlockY() - Block.SIZE);

        return this;
    }


    public boolean isBlockDeactivating() {
        return isBlockDeactivating;
    }

    public void setBlockDeactivating(boolean blockDeactivating) {
        isBlockDeactivating = blockDeactivating;
    }
}