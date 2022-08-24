/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Figures.Figure;

/**
 *
 * @author Vojta
 */
public class Square {

    private Figure currentFigure;

    private int xPos;
    private int yPos;

    public Square(int xPos, int yPos, Figure currentFigure) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentFigure = currentFigure;
    }

    public boolean isEmpty() {
        return currentFigure == null;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

}
