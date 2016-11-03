/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048;

import javax.swing.JFrame;
import reflection.ProjectReflection;
import view.MainWindow;

/**
 *
 * @author bojan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        
        ProjectReflection pr = new ProjectReflection();
        pr.output();
    }
    
}
