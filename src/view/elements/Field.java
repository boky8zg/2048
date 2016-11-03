/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import model.Orientation;

/**
 *
 * @author bojan
 */
public class Field {

    private static int width;
    private static int height;
    private static int radius;
    private static float fadeBy;
    private static int moveBy;
    
    private int x;
    private int y;
    
    private int value;
    private boolean animating;
    private boolean appearing;
    private boolean moving;
    private float opacity;
    private int moveToX;
    private int moveToY;
    private Orientation movingOrientation;
    
    private static Color[] backgroundColors;
    private static Color[] foregroundColors;
 
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = Math.random() * 100 < 90 ? 1 : 2;
        this.opacity = 0.0f;
        this.animating = true;
        this.appearing = true;
        this.moving = false;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Field.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Field.height = height;
    }

    public static int getRadius() {
        return radius;
    }

    public static void setRadius(int radius) {
        Field.radius = radius;
    }

    public static float getFadeBy() {
        return fadeBy;
    }

    public static void setFadeBy(float fadeBy) {
        Field.fadeBy = fadeBy;
    }

    public static int getMoveBy() {
        return moveBy;
    }
    
    public static void setMoveBy(int moveBy) {
        Field.moveBy = moveBy;
    }
    
    public static Color[] getBackgroundColors() {
        return backgroundColors;
    }
    
    public static void setBackgroundColors(Color[] backgroundColors) {
        Field.backgroundColors = backgroundColors;
    }

    public static Color[] getForegroundColors() {
        return foregroundColors;
    }
    
    public static void setForegroundColors(Color[] foregroundColors) {
        Field.foregroundColors = foregroundColors;
    }

    public boolean isAnimating() {
        return animating;
    }
    
    public void appear() {
        animating = true;
        appearing = true;
        opacity = 0.0f;
    }
    
    public void moveToX(int toX) {
        animating = true;
        moving = true;
        moveToX = toX;
        movingOrientation = x < toX ? Orientation.RIGHT : Orientation.LEFT;
    }
    
    public void moveToY(int toY) {
        animating = true;
        moving = true;
        moveToY = toY;
        movingOrientation = y < toY ? Orientation.DOWN : Orientation.UP;
    }
    
    private void nextAnimationFrame() {
        if (animating) {
            if (appearing) {
                nextAppearAnimationFrame();
            }
            else if (moving) {
                nextMoveAnimationFrame();
            } else {
                animating = false;
            }
        }
    }
    
    private void nextAppearAnimationFrame() {
        if (opacity < 1.0f) {
            opacity += fadeBy;
            return;
        }
        
        animating = false;
        appearing = false;
    }
    
    private void nextMoveAnimationFrame() {
        switch (movingOrientation) {
            case UP:
                if (y > moveToY) {
                    y -= moveBy;
                    return;
                }
                y = moveToY;
                break;
                
            case RIGHT:
                if (x < moveToX) {
                    x += moveBy;
                    return;
                }
                x = moveToX;
                break;
                
            case DOWN:
                if (y < moveToY) {
                    y += moveBy;
                    return;
                }
                y = moveToY;
                break;
                
            case LEFT:
                if (x > moveToX) {
                    x -= moveBy;
                    return;
                }
                x = moveToX;
                break;
        }
        
        moving = false;
    }
    
    public void draw(Graphics g) {
        Font font = new Font("Segoe UI Bold", Font.PLAIN, 50);
        g.setFont(font);
        
        String text = "" + (int)Math.pow(2.0, (double)value);
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(text, g2d);
        int textX = x + (width - (int) r.getWidth()) / 2;
        int textY = y + (height - (int) r.getHeight()) / 2 + fm.getAscent();
        
        int alpha = (int)(255 * opacity);
        alpha = alpha > 255 ? 255 : alpha;
        Color bc = backgroundColors[value - 1];
        Color fc = foregroundColors[value - 1];
        Color backgroundColor = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), alpha);
        Color foregroundColor = new Color(fc.getRed(), fc.getGreen(), fc.getBlue(), alpha);
        
        g.setColor(backgroundColor);
        g.fillRoundRect(x, y, width, height, radius, radius);
        
        g.setColor(foregroundColor);
        g.drawString(text, textX, textY);
        
        nextAnimationFrame();
    }

    public int getValue() {
        return value;
    }

    public void doubleValue() {
        this.value *= 2;
    }
    
}
