package com.insight.states;

import com.insight.graphics.Screen;

import java.awt.*;

public class BallPong  {

    private int x, y,cx,cy, speed; // Position of the ball
    private int size;
    private int color;
    static final int maxSpeed = 7 ;

    public BallPong(int x, int y, int size, int color, int cx, int cy, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.cx = cx;
        this.cy=cy;
        this.speed = speed;
    }

    public void render(Screen screen) {
        // Render the ball as a square
        screen.fillSquare(x, y, size, color);
    }

    public int getSize() {
        return size;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveBall() {
        x += cx;
        y += cy;
    }

    public void bounceOffEdges(int top, int bottom){

        //if the y value is at the bottom of the screen
        if (y > bottom-size){
            reverseY();
        }
        //if y value is at top of screen
        else if(y < top){
            reverseY();
        }


    }

    public void increaseSpeed(){
        if(speed < maxSpeed){
            speed ++;

            //update cy and cx with the new speed
            cx = (cx / Math.abs(cx)*speed);
            cy = (cy / Math.abs(cy)*speed);

        }

    }


    public void reverseX(){
        cx *= -1;
    }

    public void reverseY(){
        cy *= -1;
    }


}
