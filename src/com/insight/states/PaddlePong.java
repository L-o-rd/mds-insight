package com.insight.states;

import com.insight.graphics.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PaddlePong {

    private int height,width, x, y, speed, color;

    static final int PADDLE_WIDTH = 15;

    public PaddlePong(int height, int width, int x, int y, int speed, int color) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }

    public void render(Screen screen) {
        // Draw the paddle as a simple rectangle
        screen.fill(x, y, width, height, color);
    }

    public int getHeight() {
        return height;
    }

    public void moveTowards(int moveToY) {

        int centerY = y + height / 2; //center of the paddle

        if(Math.abs(centerY - moveToY) > speed){
            //if the center of the paddle is too far down
            if(centerY > moveToY){
                //move the paddle up by the speed
                y -= speed;
            }
            //if the center of the paddle is too far up
            if(centerY < moveToY){
                //move the paddle down by speed
                y += speed;
            }
        }

    }

    public boolean checkCollision(BallPong b){

        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + height;

        //check if the Ball is between the x values
        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){
            //check if Ball is between the y values
            if(b.getY() > y && b.getY() < bottomY){
                //if we get here, we know the ball and the paddle have collided
                return true;
            }
        }

        //if we get here, one of the checks failed, and the ball has not collided
        return false;

    }

}
