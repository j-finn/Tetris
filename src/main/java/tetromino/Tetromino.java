package main.java.tetromino;

import main.java.KeyHandler;
import main.java.PlayManager;

import java.awt.*;

public class Tetromino {

    public Block[] b = new Block[4];
    public Block[] tempB = new Block[4];

    int autoDropCounter = 0; // mino drops once this hits the dropInterval
    public int direction = 1; // there are 4 directions (1/2/3/4)
    public boolean leftCollision, rightCollision, bottomCollision;
    private boolean isBlockActive = true;


    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);

        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }


    public void setXY(int x, int y) {

    }

    public boolean isBlockActive() {
        return isBlockActive;
    }


    public void checkMovementCollision() {

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        for (int i = 0; i < b.length; i++) {

            // Left wall
            if (b[i].x  == PlayManager.left_x) {
                leftCollision = true;
            }

            // Right wall
            if (b[i].x + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
            }

            // Bottom wall
            if (b[i].y + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }


    /**
     * If any of the (x,y) coordinates of the temporary (i.e. rotated)
     * block are equal to the left/right/bottom edge then we block the rotation.
     */
    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        for (int i = 0; i < tempB.length; i++) {

            // Left wall
            if (tempB[i].x  == PlayManager.left_x) {
                leftCollision = true;
            }

            // Right wall
            if (tempB[i].x == PlayManager.right_x) {
                rightCollision = true;
            }

            // Bottom wall
            if (tempB[i].y == PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }


    /**
     * We update the arrays values via a temp array. This enables us to handle collision,
     * when we rotate the temp array, if the temp array collides with a boundary, we cancel
     * the rotation and the block remains in its current (non-temporary) position.
     *
     * @param direction
     */
    public void updateXY(int direction) {

        checkRotationCollision();

        if (!(leftCollision || rightCollision || bottomCollision)) {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }


    public void getDirection1() {
        // NO-OP
    }


    public void getDirection2() {
        // NO-OP
    }


    public void getDirection3() {
        // NO-OP
    }


    public void getDirection4() {
        // NO-OP
    }


    public void update() {

        // Move mino
        if (KeyHandler.upPressed) {

            switch (direction) {
                case 1: getDirection2();
                break;

                case 2: getDirection3();
                break;

                case 3: getDirection4();
                break;

                case 4: getDirection1();
                break;
            }

            KeyHandler.upPressed = false;
        }

        // Check for collision before moving
        checkMovementCollision();

        if (KeyHandler.downPressed) {

            if (!bottomCollision) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                // reset
                autoDropCounter = 0;
            }

            KeyHandler.downPressed = false;
        }

        if (KeyHandler.rightPressed) {

            if (!rightCollision) {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }

            KeyHandler.rightPressed = false;
        }

        if (KeyHandler.leftPressed) {

            if (!leftCollision) {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
            }

            KeyHandler.leftPressed = false;
        }

        if (bottomCollision) {
            isBlockActive = false;
        } else {
            autoDropCounter++; // counter increases every frame

            if (autoDropCounter == PlayManager.dropInterval) {
                // TODO: Why can't we just set b[0] and update the rest in one go?
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }


    public void draw(Graphics2D graphics2D) {

        int margin = 2; // for black outline
        graphics2D.setColor(b[0].colour);
        graphics2D.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
        graphics2D.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE  - (margin * 2));
    }
}