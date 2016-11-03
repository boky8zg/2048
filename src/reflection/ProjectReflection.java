/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;


import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bojan
 */
public class ProjectReflection {
    private static String[] packages = {"game2048", "model",
            "reflection", "thread", "view", "view.elements"};
    private static String[] classes = {"game2048.Main", "model.Orientation",
        "reflection.ProjectReflection", "thread.FrameRedrawThread",
        "view.Canvas", "view.MainWindow", "view.elements.Board",
        "view.elements.Field"};
    
    public String[] getPackages() {
        return packages;
    }
    
    public String[] getClasses() {
        return classes;
    }
    
    public List<String> getClassesInPackage(String packageName) {
        List<String> classes = new ArrayList<>();
        
        for (String c : getClasses()) {
            if (c.startsWith(packageName)) {
                classes.add(c);
            }
        }
        
        return classes;
    }
    
    public Method[] getClassMethods(String className) throws ClassNotFoundException {
        List<String> methods = new ArrayList<>();
        
        Class c = Class.forName(className);
        return c.getDeclaredMethods();
    }
    
    public void output() {
        FileWriter fw;
        
        try {
            fw = new FileWriter("reflection.txt");
        
            for (String p : getPackages()) {
                fw.write(String.format("@package: %s\n", p));

                for (String c : getClassesInPackage(p)) {
                    fw.write(String.format("\t@class: %s\n", c));

                    try {
                        for (Method m : getClassMethods(c)) {
                            fw.write(String.format("\t\t@method: %s\n", m.getName()));

                            for (Class param : m.getParameterTypes()) {
                                fw.write(String.format("\t\t\t@param: %s\n", param.getTypeName()));
                            }

                            fw.write(String.format("\t\t\t@return: %s\n", m.getReturnType().getTypeName()));
                        }
                    } catch (ClassNotFoundException ex) {
                        fw.write(String.format("\t\tClass not found '%s'!\n", c));
                    }
                }
            }
            
            fw.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
}
