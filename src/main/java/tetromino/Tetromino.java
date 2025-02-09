package main.java.tetromino;

import java.awt.*;

public abstract class Tetromino {

    Block[] blocks = new Block[4];
    Block[] tempB = new Block[4];
    Block[] droppedB = new Block[4];

    public int direction = 1; // there are 4 directions (1/2/3/4)
    private boolean isBlockActive = true;
    private boolean isBlockDeactivating = false;


    public Block[] getBlocks() {
        return blocks;
    }

    public Block[] getTempB() {
        return tempB;
    }

    public void create(Color c) {
        blocks[0] = new Block(c);
        blocks[1] = new Block(c);
        blocks[2] = new Block(c);
        blocks[3] = new Block(c);

        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);

        droppedB[0] = new Block(c);
        droppedB[1] = new Block(c);
        droppedB[2] = new Block(c);
        droppedB[3] = new Block(c);
    }


    public abstract void setXY(int x, int y);


    public boolean isBlockActive() {
        return isBlockActive;
    }


    // TODO Move this to game controller?
//    public void dropBlock() {
//
//        // FIXME: This is a bit laggy. Possibly room for performance optimisations?
//        tempB[0] = b[0];
//        tempB[1] = b[1];
//        tempB[2] = b[2];
//        tempB[3] = b[3];
//
//        while (!bottomCollision) {
//            tempB[0].y++;
//            tempB[1].y++;
//            tempB[2].y++;
//            tempB[3].y++;
//
//            checkMovementCollision(tempB);
//
//            if (bottomCollision) {
//                b[0].y = tempB[0].y;
//                b[1].y = tempB[1].y;
//                b[2].y = tempB[2].y;
//                b[3].y = tempB[3].y;
//            }
//        }
//    }


//    public Block[] dropBlockForDrawing() {
//
//        // FIXME: This is a bit laggy. Possibly room for performance optimisations?
//        droppedB[0].x = b[0].x;
//        droppedB[0].y = b[0].y;
//        droppedB[1].x = b[1].x;
//        droppedB[1].y = b[1].y;
//        droppedB[2].x = b[2].x;
//        droppedB[2].y = b[2].y;
//        droppedB[3].x = b[3].x;
//        droppedB[3].y = b[3].y;
//
//        while (!bottomCollision) {
//            droppedB[0].y++;
//            droppedB[1].y++;
//            droppedB[2].y++;
//            droppedB[3].y++;
//
////            checkMovementCollision(droppedB);
//        }
//
//        return droppedB;
//    }


    /**
     * TODO
     */
    public void resetTemp() {
        tempB[0].x = blocks[0].x;
        tempB[0].y = blocks[0].y;
        tempB[1].x = blocks[1].x;
        tempB[1].y = blocks[1].y;
        tempB[2].x = blocks[2].x;
        tempB[2].y = blocks[2].y;
        tempB[3].x = blocks[3].x;
        tempB[3].y = blocks[3].y;
    }

    /**
     * <p>We update the arrays values via a temp array.</p>
     *
     */
    public void rotate() {
            blocks[0].x = tempB[0].x;
            blocks[0].y = tempB[0].y;
            blocks[1].x = tempB[1].x;
            blocks[1].y = tempB[1].y;
            blocks[2].x = tempB[2].x;
            blocks[2].y = tempB[2].y;
            blocks[3].x = tempB[3].x;
            blocks[3].y = tempB[3].y;

        direction = (direction % 4) + 1; // 1 -> 2 -> 3 -> 4 -> 1
    }


    /**
     * Returns the Tetromino with its temp position rotated.
     */
    public Tetromino getRotatedPosition() {
        switch (direction) {
            case 1:
                return getRotatedPosition2();
            case 2:
                return getRotatedPosition3();
            case 3:
                return getRotatedPosition4();
            case 4:
                return getRotatedPosition1();
            default:
                throw new IllegalStateException("Rotated position was out of bounds, should be" +
                  "in either 1, 2, 3 or 4, but was: " + direction);
        }
    }


    abstract Tetromino getRotatedPosition1();


    abstract Tetromino getRotatedPosition2();


    abstract Tetromino getRotatedPosition3();


    abstract Tetromino getRotatedPosition4();


    public Tetromino getRightMovePosition() {
        tempB[0].x = blocks[0].x + Block.SIZE;
        tempB[0].y = blocks[0].y;
        tempB[1].x = blocks[0].x + Block.SIZE;
        tempB[1].y = blocks[0].y;
        tempB[2].x = blocks[0].x + Block.SIZE;
        tempB[2].y = blocks[0].y;
        tempB[3].x = blocks[0].x + Block.SIZE;
        tempB[3].y = blocks[0].y;

        return this;
    }


    public Tetromino getLeftMovePosition() {
        tempB[0].x = blocks[0].x - Block.SIZE;
        tempB[0].y = blocks[0].y;
        tempB[1].x = blocks[0].x - Block.SIZE;
        tempB[1].y = blocks[0].y;
        tempB[2].x = blocks[0].x - Block.SIZE;
        tempB[2].y = blocks[0].y;
        tempB[3].x = blocks[0].x - Block.SIZE;
        tempB[3].y = blocks[0].y;

        return this;
    }


    public Tetromino getDownMovePosition() {
        tempB[0].x = blocks[0].x;
        tempB[0].y = blocks[0].y + Block.SIZE;
        tempB[1].x = blocks[0].x;
        tempB[1].y = blocks[0].y + Block.SIZE;
        tempB[2].x = blocks[0].x;
        tempB[2].y = blocks[0].y + Block.SIZE;
        tempB[3].x = blocks[0].x;
        tempB[3].y = blocks[0].y + Block.SIZE;

        return this;
    }


    public void moveRight() {
        blocks[0].x = blocks[0].x + Block.SIZE;
        blocks[1].x = blocks[1].x + Block.SIZE;
        blocks[2].x = blocks[2].x + Block.SIZE;
        blocks[3].x = blocks[3].x + Block.SIZE;
    }


    public void moveLeft() {
        blocks[0].x = blocks[0].x - Block.SIZE;
        blocks[1].x = blocks[1].x - Block.SIZE;
        blocks[2].x = blocks[2].x - Block.SIZE;
        blocks[3].x = blocks[3].x - Block.SIZE;
    }


    public void moveDown() {
        blocks[0].y = blocks[0].y + Block.SIZE;
        blocks[1].y = blocks[1].y + Block.SIZE;
        blocks[2].y = blocks[2].y + Block.SIZE;
        blocks[3].y = blocks[3].y + Block.SIZE;
    }



    public void draw(Graphics2D graphics2D) {

        int margin = 2; // for black outline
        graphics2D.setColor(blocks[0].colour);
        graphics2D.fillRect(blocks[0].x + margin, blocks[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(blocks[1].x + margin, blocks[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(blocks[2].x + margin, blocks[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(blocks[3].x + margin, blocks[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
    }

    public boolean isBlockDeactivating() {
        return isBlockDeactivating;
    }

    public void setBlockDeactivating(boolean blockDeactivating) {
        isBlockDeactivating = blockDeactivating;
    }
}