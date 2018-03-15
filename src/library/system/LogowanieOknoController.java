
package library.system;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import library.system.dialogs.DialogsUtils;


public class LogowanieOknoController implements Initializable {
    
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

    @FXML
    private void zamknijAplikacje(ActionEvent event) {
         Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void setCaspian(ActionEvent event) {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    @FXML
    private void setModena(ActionEvent event) {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    @FXML
    private void aboutApplication(ActionEvent event) {
        DialogsUtils.dialogAboutAplication();
    }
    
}
