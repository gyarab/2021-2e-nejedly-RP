/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategie;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Vojta
 */
public class Strategie extends Application {
    static AnchorPane root;
    static List<GridPane> gridPanes = new ArrayList<GridPane>();
    private static int current = 0;

    
    @Override
    public void start(Stage stage) throws Exception {
        root = (AnchorPane) FXMLLoader.load(getClass().getResource("Anchor.fxml"));
        gridPanes.add((GridPane) FXMLLoader.load(getClass().getResource("MainMenu.fxml"))); // 0 , hlavni menu
        gridPanes.add((GridPane) FXMLLoader.load(getClass().getResource("Help.fxml"))); // 1, nápověda
        gridPanes.add((GridPane) FXMLLoader.load(getClass().getResource("GameGrid.fxml"))); // 2, hra
        
        
        root.getChildren().add(gridPanes.get(0));
        
        Scene scene = new Scene(root, 1920, 1080);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setTitle("HolyWars");
        stage.setScene(scene);
        stage.show();
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    }
    
    public static void setCurrentPane(int newPane){
        root.getChildren().remove(gridPanes.get(current));
        root.getChildren().add(gridPanes.get(newPane));
        current = newPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
