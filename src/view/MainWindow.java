/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bojan
 */
public class MainWindow {

    private final String WINDOW_TITLE = "2048";
    private JFrame frame;
    private Canvas canvas;
    
    private Image img;
    
    public MainWindow() {
        initialize();
    }
    
    private void initialize() {
        createMainWindow();
        setMainWindowIcon();
    }
    
    private void createMainWindow() {
        frame = new JFrame(WINDOW_TITLE);        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createCanvas();
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void setMainWindowIcon() {
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());
    }
    
    private void createCanvas() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(500, 500));
        canvas.setFocusable(true);
        canvas.addKeyListener(canvas);
        
        frame.add(canvas);
    }
    
}
