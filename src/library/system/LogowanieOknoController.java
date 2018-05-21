package library.system;

import Database.Client;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
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
    @FXML
    private CheckBox check;
    @FXML
    private TextField rejestracjaNrIdentyfikacyjny;
    @FXML
    private ToggleGroup uczenieGroup;
    @FXML
    private RadioButton radioTak;
    @FXML
    private RadioButton radioNie;
    boolean poprawne_dane;
    public static int przekazanieloginu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zalogujBTN.disableProperty().bind(loginField.textProperty().isEmpty());
        zalogujBTN.disableProperty().bind(hasloField.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaImie.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaNazwisko.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaEmail.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaHaslo.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaHaslo1.textProperty().isEmpty());
        zarejestrujBTN.disableProperty().bind(rejestracjaNrIdentyfikacyjny.textProperty().isEmpty());

    }

    private void pobranieDanych() {
        if (check.isSelected() == false) {
            try {
                String login = loginField.getText().trim();
                Client client = new Client();
                client.openConnect();
                String sql = "select id_klienta from klienci where nr_identyfikacji_k = ?";

                st = client.connection.prepareStatement(sql);

                st.setString(1, login);

                rs = st.executeQuery();
                if (rs.next()) {
                    przekazanieloginu = rs.getInt("id_klienta");
                }
                rs.close();
                client.connection.close();
            } catch (SQLException sql) {
                System.out.println("bec2" + sql);
            }
        }
    }

    @FXML
    private void zaloguj(ActionEvent event) throws Exception {
        try {
            pobranieDanych();
            String login = loginField.getText().trim();
            String pass = hasloField.getText().trim();
            Client client = new Client();
            client.openConnect();
            String sql = "select profil from klienci where nr_identyfikacji_k =? and haslo_k =?";
            String sql2 = "select profil from pracownicy where nr_identyfikacji_p =? and haslo_p =?";
            if (check.isSelected() == false) {
                st = client.connection.prepareStatement(sql);
            } else {
                st = client.connection.prepareStatement(sql2);
            }
            st.setString(1, login);
            st.setString(2, pass);
            rs = st.executeQuery();
            if (rs.next()) {

                if (rs.getInt("profil") == 3) {
                    //zalogowano jako uczen
                    //login 997 pass qwer1234
                    System.out.println("done uczen");
                    log.setNextScene(2);
                } else if (rs.getInt("profil") == 4) {
                    //zalogowano jako nie uczen
                    //login 997 pass qwer1234
                    System.out.println("done nie uczący się");
                    log.setNextScene(2);
                } else if (rs.getInt("profil") == 1) {
                    //zalogowano jako admin
                    //login 123 pass admin
                    System.out.println("done admin");
                    log.setNextScene(1);
                } else if (rs.getInt("profil") == 2) {
                    //zalogowano jako bibliotekarz
                    //login 111 pass 1234
                    System.out.println("done bibliotekarz");
                    log.setNextScene(1);
                }

            } else {
                //bład danych
                System.out.println("bec1  bład");
                DialogsUtils.showAlert(Alert.AlertType.WARNING, "Złe dane", "Sprawdz poprawność wpisanych danych bądź uprawnienia!");
                //log.setNextScene(2);
            }
        } catch (SQLException sql) {
            //złe sql
            System.out.println("bec2" + sql);
        }

    }

    @FXML
    private void zarejestruj(ActionEvent event) {

        if (rejestracjaImie.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj swoje imię!");
            poprawne_dane = false;
        }
        if (rejestracjaNazwisko.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj swoje nazwisko!");
            poprawne_dane = false;
        }
        if (rejestracjaEmail.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj e-mail!");
            poprawne_dane = false;
        }
        if (rejestracjaHaslo.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj hasło!");
            poprawne_dane = false;
        }
        if (rejestracjaHaslo1.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Powtórz hasło!");
            poprawne_dane = false;
        }
        if (!rejestracjaHaslo.getText().equals(rejestracjaHaslo1.getText())) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Złe hasła!", "Hasła muszą być takie same!");
            poprawne_dane = false;
        } else {
            DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Udana rejestracja!", "Witamy " + rejestracjaImie.getText() + " " + rejestracjaNazwisko.getText());
            poprawne_dane = true;
        }
        try {
            Client client = new Client();
            client.openConnect();
            String sql = "INSERT INTO klienci (imie_k, nazwisko_k, nr_identyfikacji_k, email_k, haslo_k, ilosc_wypozyczonych, kara, profil) VALUES (?,?,?,?,?,0,0,3)";
            String sql2 = "INSERT INTO klienci (imie_k, nazwisko_k, nr_identyfikacji_k, email_k, haslo_k, ilosc_wypozyczonych, kara, profil) VALUES (?,?,?,?,?,0,0,4)";
            if (radioTak.isSelected() == true && poprawne_dane == true) {
                st = client.connection.prepareStatement(sql);
            } else if (radioNie.isSelected() == true && poprawne_dane == true) {
                st = client.connection.prepareStatement(sql2);
            }
            String imie = rejestracjaImie.getText().trim();
            String nazwisko = rejestracjaNazwisko.getText().trim();
            String email = rejestracjaEmail.getText().trim();
            String haslo = rejestracjaHaslo.getText().trim();
            int nr_identyfikacyjny = Integer.parseInt(rejestracjaNrIdentyfikacyjny.getText().trim());

            st.setString(1, imie);
            st.setString(2, nazwisko);
            st.setInt(3, nr_identyfikacyjny);
            st.setString(4, email);
            st.setString(5, haslo);
            st.execute();
            System.out.print("wstawiono");
            //rs = st.executeQuery();
            rejestracjaImie.clear();
            rejestracjaNazwisko.clear();
            rejestracjaEmail.clear();
            rejestracjaHaslo.clear();
            rejestracjaHaslo1.clear();
            rejestracjaNrIdentyfikacyjny.clear();
        } catch (SQLException ex) {
            Logger.getLogger(LogowanieOknoController.class.getName()).log(Level.SEVERE, null, ex);
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
