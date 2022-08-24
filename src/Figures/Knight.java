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
public class Knight extends Figure {

    public Knight(boolean color, int count) {
        super(color, count);
        if (color) {
            super.img = new Image("resources/KnightR.png");
        } else {
            super.img = new Image("resources/KnightB.png");
        }
        super.maxHp = 100.0;
        super.meleeDmg = 30.0;
        super.armor = 20.0;
        super.moveDist = 5;
        super.currentHp = maxHp;
    }
}
