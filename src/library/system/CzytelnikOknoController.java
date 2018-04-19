
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import library.system.dialogs.DialogsUtils;


public class CzytelnikOknoController implements Initializable {

    @FXML
    private ToggleGroup styleGroup;
    @FXML
    private TextField tytulSzukanie;
    @FXML
    private TextField autorSzukanie;
    @FXML
    private TextField gatunekSzukanie;
    @FXML
    private Button wyszukajKsiazkeBTN;
    @FXML
    private TableView<?> tableWyszukajKsiazki;
    @FXML
    private TableColumn<?, ?> columnTytulWyszukaj;
    @FXML
    private TableColumn<?, ?> columnISBNWyszukaj;
    @FXML
    private TableColumn<?, ?> columnAutorWyszukaj;
    @FXML
    private TableColumn<?, ?> columnData_wydWyszukaj;
    @FXML
    private TableColumn<?, ?> columnGatunekWyszukaj;
    @FXML
    private TableColumn<?, ?> columnStatusWyszukaj;
    @FXML
    private Button wyczyscWyszukiwanieBTN;
    @FXML
    private TableView<?> tableMojeKsiazki;
    @FXML
    private TableColumn<?, ?> columnTytulMoje_ks;
    @FXML
    private TableColumn<?, ?> columnAutorMoje_ks;
    @FXML
    private TableColumn<?, ?> columnISBNMoje_ks;
    @FXML
    private TableColumn<?, ?> columnGatunekMoje_ks;
    @FXML
    private TableColumn<?, ?> columnData_wypMoje_ks;
    @FXML
    private TableColumn<?, ?> columnData_zwrMoje_ks;
    @FXML
    private TextField karaPole;
    @FXML
    private TextField ilosc_wypPole;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
