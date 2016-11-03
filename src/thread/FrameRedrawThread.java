/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Canvas;

/**
 *
 * @author bojan
 */
public class FrameRedrawThread implements Runnable {

    private final Canvas canvas;
    private final int frameDuration;

    public FrameRedrawThread(Canvas canvas, int fps) {
        this.canvas = canvas;
        this.frameDuration = (int)(1000 / fps);
    }
    
    @Override
    public void run() {
        while (true) {            
            if (canvas.getBoard().isRepaintNeeded()) {
                canvas.repaint();
            }
            
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameRedrawThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
