package library.system;

import Database.Client;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import library.system.dialogs.DialogsUtils;

public class LogowanieOknoController implements Initializable {

    LibrarySystem log = new LibrarySystem();
    ResultSet rs;
    PreparedStatement st;
    @FXML
    private Button zalogujBTN;
    @FXML
    private TextField loginField;
    @FXML
    private TextField hasloField;
    @FXML
    private ToggleGroup styleGroup;
    @FXML
    private TextField rejestracjaImie;
    @FXML
    private TextField rejestracjaNazwisko;
    @FXML
    private TextField rejestracjaEmail;
    @FXML
    private PasswordField rejestracjaHaslo;
    @FXML
    private PasswordField rejestracjaHaslo1;
    @FXML
    private Button zarejestrujBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zalogujBTN.disableProperty().bind(loginField.textProperty().isEmpty());
        zalogujBTN.disableProperty().bind(hasloField.textProperty().isEmpty());

    }

    @FXML
    private void zaloguj(ActionEvent event) throws Exception {
        try{
        String login  = loginField.getText().trim();
        String pass  = hasloField.getText().trim();
         Client client = new Client();
         client.openConnect();
        String sql = "select rodzaj_uzytkownika from uzytkownicy where nr_identyfikacji =? and haslo =?";
        st = client.connection.prepareStatement(sql);
        st.setString(1, login);
        st.setString(2, pass);
        rs = st.executeQuery();
        if(rs.next()){
            
            if(rs.getString("rodzaj_uzytkownika").equals("czytelnik")){
                //zalogowano jako czytelnik
                    //login 123 pass 321
             System.out.println("done");
             log.setNextScene(2);}
            else if(
             rs.getString("rodzaj_uzytkownika").equals("bibliotekarz")){
                //zalogowano jako bibliotekarz
                    //login 997 pass policja
             System.out.println("done");
             log.setNextScene(1);  
            }
             
        }
        else{
            //bład danych
             System.out.println("bec1  bład");
             //log.setNextScene(2);
        }
       
        }
        catch(SQLException sql){
            //złe sql
             System.out.println("bec2"+sql);
        }

    }

    @FXML
    private void zarejestruj(ActionEvent event) {
        if (rejestracjaImie.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj swoje imię!");
        }
        if (rejestracjaNazwisko.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj swoje nazwisko!");
        }
        if (rejestracjaEmail.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj e-mail!");
        }
        if (rejestracjaHaslo.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj hasło!");
        }
        if (rejestracjaHaslo1.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Powtórz hasło!");
        }
        if (!rejestracjaHaslo.getText().equals(rejestracjaHaslo1.getText())) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Złe hasła!", "Hasła muszą być takie same!");
        } else {
            DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Udana rejestracja!", "Witamy " + rejestracjaImie.getText() + " " + rejestracjaNazwisko.getText());
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
