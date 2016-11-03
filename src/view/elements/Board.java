/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.elements;

import java.awt.Color;
import java.awt.Graphics;
import model.Orientation;

/**
 *
 * @author bojan
 */
public class Board {
    
    private int width;
    private int height;
    private int fps;
    private int spacing;
    private int cols;
    private int rows;
    private int radius;
    private Field[][] fields;
    private boolean repaint;
    
    private int fieldWidth;
    private int fieldHeight;
    private int fieldRadius;
    private float fieldFadeBy;
    private int fieldMoveBy;
    
    private Color background;
    private Color foreground;
    private Color emptyFieldBackground;

    public Board(int width, int height, int fps) {
        this.width = width;
        this.height = height;
        this.fps = fps;
        
        setupBoardDefaults();
        setupFieldDefaults();
        
        newField();
        newField();
    }
    
    private void setupBoardDefaults() {
        spacing = 16;
        cols = 4;
        rows = 4;
        radius = 16;
        fields = new Field[rows][cols];
        repaint = true;
        
        background = new Color(187, 173, 160);
        foreground = new Color(0, 0, 0);
        emptyFieldBackground = new Color(205, 193, 180);
    }
    
    private void setupFieldDefaults() {
        fieldWidth = (width - spacing * (cols + 1)) / cols;
        fieldHeight = (height - spacing * (rows + 1)) / rows;
        fieldRadius = 8;
        fieldFadeBy = 3.0f / fps;
        fieldMoveBy = 2000 / fps;
        
        Field.setWidth(fieldWidth);
        Field.setHeight(fieldHeight);
        Field.setRadius(fieldRadius);
        Field.setFadeBy(fieldFadeBy);
        Field.setMoveBy(fieldMoveBy);
        
        Field.setBackgroundColors(new Color[] {
            new Color(238, 228, 218),
            new Color(237, 224, 200)
        });
        
        Field.setForegroundColors(new Color[] {
            new Color(119, 110, 101),
            new Color(119, 110, 101)
        });
    }
    
    private boolean isAnimating() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (fields[row][col] != null && fields[row][col].isAnimating()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void drawBoard(Graphics g) {
        g.setColor(background);
        g.fillRoundRect(0, 0, width, height, radius, radius);
    }
    
    private void drawEmptyFields(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                drawEmptyField(g, row, col);
            }
        }
    }
    
    private void drawEmptyField(Graphics g, int row, int col) {
        int x = colToX(col);
        int y = rowToY(row);
        
        g.setColor(emptyFieldBackground);
        g.fillRoundRect(x, y, fieldWidth, fieldHeight, fieldRadius, fieldRadius);
    }
    
    private void drawFields(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (fields[row][col] != null) {
                    fields[row][col].draw(g);
                }
            }
        }
    }
    
    public void repaint() {
        this.repaint = true;
    }
    
    public boolean isRepaintNeeded() {
        if (isAnimating() || repaint) {
            return true;
        }
        return false;
    }
    
    public void draw(Graphics g) {
        drawBoard(g);
        drawEmptyFields(g);
        drawFields(g);

        repaint = false;
    }
    
    public void movement(Orientation o) {
        switch (o) {
            case UP:
                break;
                
            case RIGHT:
                break;
                
            case DOWN:
                break;

            case LEFT:
                
                break;
        }
    }
    
    private int countEmptyFields() {
        int count = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (fields[row][col] == null) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private int colToX(int col) {
        return spacing + (fieldWidth + spacing) * col;
    }
    
    private int rowToY(int row) {
        return spacing + (fieldHeight + spacing) * row;
    }
    
    private boolean newField() {
        int emptyFields = countEmptyFields();
        int random = (int)(Math.random() * emptyFields);
        int count = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (count == random) {
                    int x = colToX(col);
                    int y = rowToY(row);
                    
                    fields[row][col] = new Field(x, y);
                    return true;
                }
                count++;
            }
        }
        
        return false;
    }
}
