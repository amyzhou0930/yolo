package com.eugenewang.mobattack;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by eugen on 9/22/2016.
 */
public class KeyHandel extends MouseAdapter implements MouseMotionListener, MouseListener {


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
    	
    	//if (inGame)
    	Screen.getStore().click(e.getButton());
    	//System.out.println(e.getButton());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        Screen.mse = new Point(e.getX() + (Main.dimension.width - Screen.myWidth)/2 , e.getY() + (Main.dimension.height -  Screen.myHeight -Main.dimension.width+Screen.myWidth)/2);

    }

    public void mouseMoved(MouseEvent e) {
        Screen.mse = new Point(e.getX() + (Main.dimension.width - Screen.myWidth)/2 , e.getY() + (Main.dimension.height -  Screen.myHeight -Main.dimension.width+Screen.myWidth)/2);
    }
}
