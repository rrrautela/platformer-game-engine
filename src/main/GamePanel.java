package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


//“This object is a Swing component.
//Swing is allowed to manage its lifecycle.”

public class GamePanel extends JPanel {

//    a painting has a frame and a panel(picture)
    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game){
        this.game = game;

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));

        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void setPanelSize(){
        //copz size srt in game window oincludes the borders and all too, so its actually lesser
        Dimension size = new Dimension(1280, 800);
        //images are 32 pixels each so it would be 40 imahes wide and 25 images in height
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    //    this is a func of JPanel, we extended it so this class also have that so we override it
    //    graphics g is  a brush given by swing, it knows where to draw how to draw
    //    the panel is a whiteboard, once  a is drawn andcolored u cant change the color, u can only erase it and repaint  a new with new color


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
//        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
    }


    public Game getGame(){
        return game;
    }
}
