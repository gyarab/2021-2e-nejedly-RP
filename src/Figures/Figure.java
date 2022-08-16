/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figures;

import javafx.scene.image.Image;

/**
 *
 * @author Vojta
 */
public class Figure {
    double rangedDmg;

    double maxHp;

    double currentHp;
    double meleeDmg;
    double armor;
    int moveDist;
    int count;
    boolean color; // false modry, true cerveny
    Image img;

    /**
     * 
     * @param color false -> modry, true-> cerveny
     * @param count pocet jednotek
     */
    public Figure(boolean color, int count) {
        this.color = color;
        this.count = count;
        this.moveDist = 5;
        this.rangedDmg = -1;
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMoveDist() {
        return moveDist;
    }

    public double getRangedDmg() {
        return rangedDmg;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public double getMeleeDmg() {
        return meleeDmg;
    }

    public double getArmor() {
        return armor;
    }

    public int getCount() {
        return count;
    }

    public boolean isColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

}
