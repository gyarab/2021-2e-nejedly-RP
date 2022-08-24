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
public class Archer extends Knight {

    public Archer(boolean color, int count) {
        super(color, count);
        if (color) {
            super.img = new Image("resources/ArcherR.png");
        } else {
            super.img = new Image("resources/ArcherB.png");
        }
        super.maxHp = 100;
        super.armor = 10.0;
        super.moveDist = 3;
        super.meleeDmg = 10.0;
        super.moveDist = 3;
        super.rangedDmg = 20.0;
        super.currentHp = maxHp;
    }

    public double getRangedDmg() {
        return rangedDmg;
    }

}
