/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author student
 */
public class LogowanieKontroler implements Initializable {
    
    LibrarySystem log = new LibrarySystem();
    
    private Label label;
    @FXML
    private Button zalogujBTN;
    @FXML
    private TextField loginField;
    @FXML
    private TextField hasloField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zalogujBTN.disableProperty().bind(loginField.textProperty().isEmpty());
        zalogujBTN.disableProperty().bind(hasloField.textProperty().isEmpty());
        
        

        
    }    

    @FXML
    private void zaloguj(ActionEvent event) throws Exception {
        if(loginField.getText().toString().equals("bibliotekarz") && hasloField.getText().toString().equals("haslo"))
        {
            log.setNextScene(1);
        
        }
        else if(loginField.getText().toString().equals("czytelnik") && hasloField.getText().toString().equals("haslo1"))
        {
             log.setNextScene(2);
            
        }
    }
    
}
