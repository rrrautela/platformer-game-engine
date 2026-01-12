package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
//    fps and ups both are frequency
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;
    //constructor
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); //so this component gets input focus
        startGameLoop();
        //it should be the last line as it's an infinite loop
    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void update(){
        player.update();
    }
    public void render(Graphics g){
        player.render(g);


    }


    public Player getPlayer(){
        return player;
    }


    public void windowFocusLost() {
        player.resetDirBooleans();

    }
    @Override
    public void run() {
        //the code we have here is the code we want to run on seperate thread

        // coz we want it in nanoseconds, not in seconds
        double timePerFrame = 1000000000.0/ FPS_SET;
        double timePerUpdate = 1000000000.0/ UPS_SET;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        //game loop:
        while(true){
            //infinite loop
            //set amount of frames per second

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }



            //if one second has passed since the last fps check then we do a new fps check
            //and save the new fps check as the lastfps check and repeat this process forever
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0; //reset frames for the next second
                updates = 0;
            }

        }
    }



}
