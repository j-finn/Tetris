package main.java.tetromino;

import main.java.GamePanel;
import main.java.KeyHandler;
import main.java.PlayManager;

import java.awt.*;

public abstract class Tetromino {

    public Block[] b = new Block[4];
    public Block[] tempB = new Block[4];
    public Block[] droppedB = new Block[4];

    int autoDropCounter = 0; // mino drops once this hits the dropInterval
    public int direction = 1; // there are 4 directions (1/2/3/4)
    public boolean leftCollision, rightCollision, bottomCollision;
    private boolean isBlockActive = true;
    public boolean isBlockDeactivating = false;
    int deactivateCounter = 0;


    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);

        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);

        droppedB[0] = new Block(c);
        droppedB[1] = new Block(c);
        droppedB[2] = new Block(c);
        droppedB[3] = new Block(c);
    }


    public void setXY(int x, int y) {

    }

    public boolean isBlockActive() {
        return isBlockActive;
    }


    public void dropBlock() {

        // FIXME: This is a bit laggy. Possibly room for performance optimisations?
        tempB[0] = b[0];
        tempB[1] = b[1];
        tempB[2] = b[2];
        tempB[3] = b[3];

        while (!bottomCollision) {
            tempB[0].y++;
            tempB[1].y++;
            tempB[2].y++;
            tempB[3].y++;

            checkMovementCollision(tempB);

            if (bottomCollision) {
                b[0].y = tempB[0].y;
                b[1].y = tempB[1].y;
                b[2].y = tempB[2].y;
                b[3].y = tempB[3].y;
            }
        }
    }


    public Block[] dropBlockForDrawing() {

        // FIXME: This is a bit laggy. Possibly room for performance optimisations?
        droppedB[0].x = b[0].x;
        droppedB[0].y = b[0].y;
        droppedB[1].x = b[1].x;
        droppedB[1].y = b[1].y;
        droppedB[2].x = b[2].x;
        droppedB[2].y = b[2].y;
        droppedB[3].x = b[3].x;
        droppedB[3].y = b[3].y;

        while (!bottomCollision) {
            droppedB[0].y++;
            droppedB[1].y++;
            droppedB[2].y++;
            droppedB[3].y++;

            checkMovementCollision(droppedB);
        }

        return droppedB;
    }


    public void checkMovementCollision(Block[] minoBlock) {

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision(minoBlock);

        for (int i = 0; i < minoBlock.length; i++) {

            // Left wall
            if (minoBlock[i].x  == PlayManager.left_x) {
                leftCollision = true;
            }

            // Right wall
            if (minoBlock[i].x + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
            }

            // Bottom wall
            if (minoBlock[i].y + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }


    /**
     * If any of the (x,y) coordinates of the temporary (i.e. rotated)
     * block are equal to the left/right/bottom edge then we prevent the rotation.
     */
    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision(b);

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


    public void checkStaticBlockCollision(Block[] minoBlock) {

        for (int i = 0; i < PlayManager.staticBlocks.size(); i++) {
            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            for (int j = 0; j < minoBlock.length; j++) {
                if (minoBlock[j].y + Block.SIZE == targetY && minoBlock[j].x == targetX) {
                    bottomCollision = true;
                }

                if (minoBlock[j].y == targetY && minoBlock[j].x - Block.SIZE == targetX) {
                    leftCollision = true;
                }

                if (minoBlock[j].y == targetY && minoBlock[j].x + Block.SIZE == targetX) {
                    rightCollision = true;
                }
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


    public abstract void getDirection1();


    public abstract void getDirection2();


    public abstract void getDirection3();


    public abstract void getDirection4();


    public void update() {

        if (isBlockDeactivating) {
            deactivating();
        }

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
            GamePanel.soundEffect.play(3, true);
        }

        // Check for collision before moving
        checkMovementCollision(b);

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

        if (KeyHandler.dropPressed) {
            dropBlock();

            KeyHandler.dropPressed = false;
        }

        if (bottomCollision) {
            isBlockDeactivating = true;
            GamePanel.soundEffect.play(4, true);
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

    public void deactivating() {
        deactivateCounter++;

        // wait 45 frames until deactivating
        if (deactivateCounter == 45) {

            deactivateCounter = 0;
            checkMovementCollision(b); // check bottom is still touching

            if (bottomCollision) {
                isBlockActive = false;
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