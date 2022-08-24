/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MainMenuController {

    @FXML
    private ImageView BackGround;

    @FXML
    private Button helpButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button playButton;

    @FXML
    void stop(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void help(ActionEvent event) {
        Strategie.setCurrentPane(1);
    }

    @FXML
    void play(ActionEvent event) {
        Strategie.setCurrentPane(2);
    }

}
