package com.insight.states;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.*;
import com.insight.graphics.Button;

import java.awt.event.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class StatePong extends State implements MouseMotionListener {

    private boolean running;
    private Button back;
    private int xback = 0;
    private PaddlePong pcPaddle;
    private PaddlePong userPaddle;

    private BallPong ball;
    private int scorePc;
    private int scoreUser;
    private int bounceCount;
    private int userMouseY;
    private Timer timer;

    private final int margin = 50; // Margin around the pong board
    private final int courtWidth;
    private final int courtHeight;

    public StatePong(Game game) {
        super(game);
        courtWidth = Content.WIDTH - 2 * margin;
        courtHeight = Content.HEIGHT - 2 * margin;

        ball = new BallPong(100, 100, 5, 0xFF0000, 3, 3, 3);
        userPaddle = new PaddlePong(20, 2, 70, 75, 3, 0x0000FF);
        pcPaddle = new PaddlePong(20, 2, 250, 75, 3, 0x00FF00);

        userMouseY = 0;
        scorePc = 0;
        scoreUser = 0;
        bounceCount = 0;

        running = true;

        addMouseMotionListener(this);

        this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
            @Override
            public void clicked() {
                exit(); // Stop the current game logic
                game.setState(State.MINIGAMES_STATE);
            }
        };


        // Initialize and start the timer for game updates
        timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    gameLogic();
                }
            }
        });
        timer.start();
    }



    public void exit() {
        running = false; // Stop the game loop when the state is changed or the game exits
        timer.stop();
        reset();
    }

    public void gameLogic() {
        ball.moveBall();
        ball.bounceOffEdges(margin, courtHeight); //courtHeight+margin ??

        userPaddle.moveTowards(userMouseY);
        pcPaddle.moveTowards(ball.getY());

        if (pcPaddle.checkCollision(ball) || userPaddle.checkCollision(ball)) {
            // Reverse ball if they collide
            ball.reverseX();
            bounceCount++;
        }

        if (bounceCount == 5) {
            bounceCount = 0;
            ball.increaseSpeed();
        }

        if (ball.getX() < margin) {
            // Player has lost
            scorePc++;
            reset();
        } else if (ball.getX() >  courtWidth) { //margin + courtWidth ??
            // PC has lost
            scoreUser++;
            reset();
        }
    }

    public void reset() {
//        try{
//            Thread.sleep(1000);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        ball.setX(100);
        ball.setY(100);
        ball.setCx(3);
        ball.setCy(3);
        ball.setSpeed(3);
        bounceCount = 0;

    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        userMouseY = e.getY();
    }

    @Override
    public void render(Screen screen) {
        // Draw animated background
        screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
        screen.blitWrap(Art.back, xback * 1, 0);
        screen.blitWrap(Art.back, 30 + xback + Art.back.width, 0);
        xback++;

        // Define the pong board dimensions
        int xOffset = margin;
        int yOffset = margin;

        // Draw the pong court
        screen.fill(xOffset, yOffset, courtWidth, courtHeight, 0x333333);

        // Ball & paddle
        ball.render(screen);
        userPaddle.render(screen);
        pcPaddle.render(screen);

        Font.write(screen, "PC ", 90, 60, 0xdfefdf);
        Font.write(screen, "User ", 190, 60, 0xdfefdf);

        Font.write(screen, String.valueOf(scoreUser), 110, 60, 0xdfefdf);
        Font.write(screen, String.valueOf(scorePc), 225, 60, 0xdfefdf);

        this.back.x = (Content.WIDTH - SmallButton.width()) >> 1;
        this.back.y = yOffset + courtHeight;
        this.back.render(screen, game.input);
    }

    @Override
    public void update(Input input) {
        this.back.update(input);

        if (running) {
            gameLogic();
        }


    }

}
