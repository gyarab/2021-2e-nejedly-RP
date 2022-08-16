/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategie;

import Core.Field;
import Core.Squere;
import Figures.Archer;
import Figures.Figure;
import Figures.Knight;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

/**
 * FXML Controller class
 *
 * @author Vojta
 */
public class GameGridController implements Initializable {
    /**
     * logicke pole pro vypocty tahu provedem na grafickem poli.
     */
    private Field f;
    private int currentX;
    private int currentY;

    private boolean turnColor = false;


    private int moveCount = 0;

    /**
     *  maxMoves urcuje pocet tahu za kolo,
     *  nesmi byt mensi nez 1.
     */
    public int maxMoves = 2;

    private boolean moving = false;
    private ImageView[][] images;
    private Label[][] texts;

    private ProgressBar[][] progress;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button backButton;
    
    @FXML
    private GridPane GameGrid;

    @FXML
    private Label turnLabel;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    void mousePressed(MouseEvent event) {
        if (event.getY() <= 30)
            return;

        if (moving) {
            double curX = (event.getX()/1920)*12;
            double curY = ((event.getY()-30)/1050)*10;
            int targetX = (int) curX;
            int targetY = (int) curY;
//            System.out.print(targetX);
//            System.out.print(";");
//            System.out.println(targetY);


            images[currentX][currentY].setOpacity(1.0);
            if (f.getFigure(targetX,targetY) == null){
                if (f.move(currentX, currentY, targetX, targetY))
                    moveCount++;
            } else if (f.getFigure(currentX, currentY).isColor() == f.getFigure(targetX, targetY).isColor()) {
                    if (f.move(currentX, currentY, targetX, targetY))
                        moveCount++;
                } else if (f.getFigure(currentX, currentY).getRangedDmg() > 0) {
                    if (f.rangedAttack(currentX, currentY, targetX, targetY))
                        moveCount++;
                } else if (f.attack(currentX, currentY, targetX, targetY))
                    moveCount++;



            moving = false;
            updateMap();
        }
        else {
            double curX = (event.getX()/1920)*12;
            double curY = ((event.getY()-30)/1050)*10;
            currentX = (int) curX;
            currentY = (int) curY;
//            System.out.print(currentX);
//            System.out.print(";");
//            System.out.println(currentY);

            Figure fig = f.getFigure(currentX, currentY);
            if (fig == null)
                return;
            if (fig.isColor() != turnColor)
                return;

            images[currentX][currentY].setOpacity(0.5);
            moving = true;
        }
    }
    
    @FXML
    void mouseReleased(MouseEvent event) {

    }
    
    
    @FXML
    void mouseDragged(MouseEvent event) {

    }
    
    @FXML
    void backToMenu(ActionEvent event) {
        Strategie.setCurrentPane(0);
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameGrid.setGridLinesVisible(true);
        initializeBattlefield();
        initializeArmy();
    }

    private void updateMap() {
        if (moveCount == maxMoves){
            turnColor = !turnColor;
            moveCount = 0;
        }

        int blue = 0;
        int red = 0;
        for (int i = 0; i < Field.WIDTH; i++) {
            for (int j = 0; j < Field.HEIGHT; j++) {
                Figure figure = f.getFigure(i, j);
                if (figure != null) {
                    if (figure.isColor())
                        red++;
                    else blue++;
                    images[i][j].setImage(figure.getImg());
                    texts[i][j].setText(String.valueOf(figure.getCount()));
                    texts[i][j].setVisible(true);
                    progress[i][j].setProgress(figure.getCurrentHp() / 100);
                    progress[i][j].setVisible(true);
                    if (figure.isColor()) {
                        progress[i][j].setEffect(new ColorAdjust(-1.0, 0.0, 0.0, 1.0));
                        //progress[i][j].setStyle("-fx-accent: red;");
                        texts[i][j].setTextFill(Color.RED);
                    } else {
                        texts[i][j].setTextFill(Color.BLUE);
                        progress[i][j].setEffect(new ColorAdjust(0.3, 0.0, 0.0, 1.0));
                        //progress[i][j].setStyle("-fx-accent: blue;");
                    }
                } else {
                    texts[i][j].setTextFill(null);
                    images[i][j].setImage(null);
                    texts[i][j].setVisible(false);
                    texts[i][j].setText("");
                    progress[i][j].setVisible(false);
                    progress[i][j].setProgress(0);
                }
            }
        }
        if (turnColor) {
            turnLabel.setTextFill(Color.RED);
            turnLabel.setText("Hraje Cerveny");
        } else {
            turnLabel.setTextFill(Color.BLUE);
            turnLabel.setText("Hraje Modry");
        }
        if (red == 0) {
            turnLabel.setText("Blue Wins!");
            turnLabel.setTextFill(Color.GOLD);
            turnColor = true;
        }
        if (blue == 0) {
            turnLabel.setText("Red Wins!");
            turnLabel.setTextFill(Color.GOLD);
            turnColor = false;
        }
    }

    private void initializeArmy() {
        //Blue
        f.assignPos(0, 0, new Knight(false, 100));
        f.assignPos(0, 2, new Knight(false, 10));
        f.assignPos(0, 4, new Knight(false, 10));
        f.assignPos(0, 6, new Knight(false, 1));
        f.assignPos(0, 8, new Knight(false, 1));
        f.assignPos(1, 4, new Knight(false, 1));
        f.assignPos(1, 5, new Knight(false, 1));
        f.assignPos(0,1,new Archer(false,5));

        //Red
        f.assignPos(11, 0, new Knight(true, 100));
        f.assignPos(11, 2, new Knight(true, 10));
        f.assignPos(11, 4, new Knight(true, 10));
        f.assignPos(11, 6, new Knight(true, 1));
        f.assignPos(11, 8, new Knight(true, 1));
        f.assignPos(10, 4, new Knight(true, 1));
        f.assignPos(10, 5, new Knight(true, 1));
        f.assignPos(11,1,new Archer(true,5));

        updateMap();
    }
    
    private void initializeBattlefield(){
        images = new ImageView[Field.WIDTH][Field.HEIGHT];
        texts = new Label[Field.WIDTH][Field.HEIGHT];
        progress = new ProgressBar[Field.WIDTH][Field.HEIGHT];
        for (int i = 0; i < Field.WIDTH; i++) {
            for (int j = 0; j < Field.HEIGHT; j++) {

                ImageView iv = new ImageView();
                iv.setFitWidth(160);
                iv.setFitHeight(105);
                iv.setPreserveRatio(false);
                Label l = new Label();
                l.setAlignment(Pos.BOTTOM_LEFT);
                l.setMinSize(0,0);
                l.setPrefSize(160,105);
                l.setMaxSize(160,105);
                l.setVisible(false);
                l.setFont(new Font(20));
                ProgressBar pb = new ProgressBar();
                l.setAlignment(Pos.TOP_LEFT);
                pb.setVisible(false);
                GameGrid.add(pb, i, j + 1);
                progress[i][j] = pb;
                GameGrid.add(l, i, j + 1);
                texts[i][j] = l;
                GameGrid.add(iv, i, j + 1);
                images[i][j] = iv;


            }
        }
        f = new Field();
    }
}
