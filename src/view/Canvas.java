/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Orientation;
import thread.FrameRedrawThread;
import view.elements.Board;

/**
 *
 * @author bojan
 */
public class Canvas extends JPanel implements KeyListener {

    private Board board;
    private int fps;
    private Thread frameThread;
    
    public Canvas() {
        fps = 100;
        board = new Board(500, 500, fps);
        frameThread = new Thread(new FrameRedrawThread(this, fps));
        frameThread.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        turnOnAntialiasing(g);
        board.draw(g);
    }
    
    private void turnOnAntialiasing(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
 
    public Board getBoard() {
        return board;
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                board.movement(Orientation.UP);
                break;
                
            case KeyEvent.VK_RIGHT:
                board.movement(Orientation.RIGHT);
                break;
                
            case KeyEvent.VK_DOWN:
                board.movement(Orientation.DOWN);
                break;
                
            case KeyEvent.VK_LEFT:
                board.movement(Orientation.LEFT);
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    
}
