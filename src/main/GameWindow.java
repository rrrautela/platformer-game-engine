package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashSet;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();
//        A window with title bar, close, minimize, resize buttons
//        A surface to draw stuff or add UI components


        jframe.add(gamePanel);
        //now even when we close the gui frame the program is still running, so we do this
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //so now when window closes, the whole program terminates




        jframe.setResizable(false);
        //as the name suggests

        jframe.pack();
        //so that it would fit the jpanel

        jframe.setLocationRelativeTo(null);
        //to open game window in the center of our screen
        //we wrote this after jframe.pack() because centering depends on final size,
        // which is not calculated before jframe.pack


        jframe.setVisible(true);
        //always write it at the end so that it appears fully configured

        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                //so that if user opens another window all movments of player stoop
                System.out.println("BYEEE");
                gamePanel.getGame().windowFocusLost();
            }
        });
    }

}
