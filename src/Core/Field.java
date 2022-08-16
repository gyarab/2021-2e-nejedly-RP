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
public class Field{
    public static final int WIDTH = 12;
    public static final int HEIGHT = 10;
    private Squere[][] field;

    public Field() {
        this.field = new Squere[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                this.field[i][j] = new Squere(i, j, null);
            }
        }
    }

    public void assignPos(int xPos, int yPos, Figure f)
    {
        if (xPos < 0 || xPos >= WIDTH)
            return;
        if (yPos < 0 || yPos >= HEIGHT)
            return;

        field[xPos][yPos].setCurrentFigure(f);
    }

    public Figure getFigure(int xPos, int yPos)
    {
        if (xPos < 0 || xPos >= WIDTH)
            return null;
        if (yPos < 0 || yPos >= HEIGHT)
            return null;

        return field[xPos][yPos].getCurrentFigure();
    }

    public boolean move(int xPosFrom, int yPosFrom, int xPosTo, int yPosTo){
        if (field[xPosFrom][yPosFrom].isEmpty())
            return false;
        if(xPosFrom == xPosTo && yPosFrom == yPosTo)
            return false;
        Figure fig = this.field[xPosFrom][yPosFrom].getCurrentFigure();
        int x = xPosTo-xPosFrom;
        int y = yPosTo-yPosFrom;
        if (Math.sqrt(((x*x)+(y*y))) > fig.getMoveDist())
            return false;
        if (!field[xPosTo][yPosTo].isEmpty()) {
            if (field[xPosTo][yPosTo].getCurrentFigure().isColor() == fig.isColor())
                return mergeArmy(xPosFrom, yPosFrom, xPosTo, yPosTo);
            else return false;
        }
        this.field[xPosTo][yPosTo].setCurrentFigure(fig);
        this.field[xPosFrom][yPosFrom].setCurrentFigure(null);
        return true;
    }

    public boolean mergeArmy(int xPosFrom,int yPosFrom, int xPosTo,int yPosTo){
        Figure fig = field[xPosTo][yPosTo].getCurrentFigure();
        Figure fig2 = field[xPosFrom][yPosFrom].getCurrentFigure();
        if (fig.getClass() != fig2.getClass())
            return false;
        field[xPosTo][yPosTo].getCurrentFigure().setCount(fig.getCount() + fig2.getCount());
        field[xPosFrom][yPosFrom].setCurrentFigure(null);
        return true;
    }
    
    public boolean attack(int xPosFrom, int yPosFrom, int xPosTo, int yPosTo){
        if (field[xPosFrom][yPosFrom].isEmpty())
            return false;
        if(field[xPosTo][yPosTo].isEmpty())
            return false;
        Figure attacker = field[xPosFrom][yPosFrom].getCurrentFigure();
        Figure victim = field[xPosTo][yPosTo].getCurrentFigure();
        if(attacker.isColor() == victim.isColor())
            return false;
        int x = xPosTo-xPosFrom;
        int y = yPosTo-yPosFrom;
        if(Math.sqrt(((x*x)+(y*y))) > 1.5)
            return false;
        double dmg = attacker.getMeleeDmg() * attacker.getCount();
        double hitPoints = ((victim.getArmor()/5) + victim.getMaxHp()) * (victim.getCount()-1) + victim.getCurrentHp();
        double hp = (hitPoints - dmg);
        int maxCount = victim.getCount();
        int newCount = ((int) (hp/100));
        if (maxCount <newCount) {
            victim.setCount(maxCount);
        } else {
            victim.setCount(newCount);
        }
        victim.setCurrentHp(hp%100);
        if(victim.getCount() <=0){
            field[xPosTo][yPosTo].setCurrentFigure(null);
            move(xPosFrom, yPosFrom, xPosTo, yPosTo);
        }
        return true;
        
    }

    public boolean rangedAttack(int xPosFrom, int yPosFrom, int xPosTo, int yPosTo){
        Figure archer = field[xPosFrom][yPosFrom].getCurrentFigure();
        if(archer.getRangedDmg() < 0)
            return false;
        Figure victim = field[xPosTo][yPosTo].getCurrentFigure();
        if(archer.isColor()==victim.isColor())
            return false;
        
        double dmg = archer.getRangedDmg()*archer.getCount(); // poskozeni zpusobeno vsemi lokostrelci
        double hitPoints = ((victim.getArmor()/5) + victim.getMaxHp()) * (victim.getCount()-1) + victim.getCurrentHp(); // (HP+Armor) * (pocet-1) + currentHp
        double hp = (hitPoints - dmg);
        int maxCount = victim.getCount();
        int newCount = ((int) (hp/100));
        double newHp = hp%100;
        if (maxCount <newCount) {
            if (victim.getCurrentHp()>newHp) {
                victim.setCount(maxCount-1);
            } else
                victim.setCount(maxCount);
        } else {
            victim.setCount(newCount);
        }
        victim.setCurrentHp(hp%100);
        if(victim.getCount() <=0){
            field[xPosTo][yPosTo].setCurrentFigure(null);
            return true;
        }
        return true;
        
    }
    
}
