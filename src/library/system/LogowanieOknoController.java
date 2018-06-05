package library.system;

import Database.Client;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    boolean poprawne_dane1;

    /**
     *
     */
    public static int przekazanieloginu;

    /**
     *
     */
    public static List<Integer> lista = new ArrayList();

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
        pobranieNrIdentyfikacji();

    }

    /**
     *
     * pobranie danych do osoby logującej się
     */
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

    /**
     * metoda do rozróżniania klientów
     */
    private void pobranieNrIdentyfikacji() {

        try {
            Client client = new Client();
            client.openConnect();
            String sql5 = "select nr_identyfikacji_k from klienci";
            int zmienna;
            lista.clear();
            st = client.connection.prepareStatement(sql5);
            rs = st.executeQuery();
            while (rs.next()) {
                zmienna = rs.getInt("nr_identyfikacji_k");
                lista.add(zmienna);
                System.out.println("Lista klientow"+lista);
            }
            rs.close();
            client.connection.close();
        } catch (SQLException sql) {
            System.out.println("Błąd z pobranieNrIdentyfikacji" + sql);
        }

    }

    /**
     * logowanie klienta lub pracownika
     *
     * @param event
     * @return
     * @throws java.lang.Exception
     */
    @FXML
    public int zaloguj(ActionEvent event) throws Exception {
        int n = 0;
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
                    n = 2;
                    System.out.println("done uczen");
                    log.setNextScene(n);
                } else if (rs.getInt("profil") == 4) {
                    //zalogowano jako nie uczen
                    //login 997 pass qwer1234
                    n = 2;
                    System.out.println("done nie uczący się");
                    log.setNextScene(n);
                } else if (rs.getInt("profil") == 1) {
                    //zalogowano jako admin
                    //login 123 pass admin
                    n = 3;
                    System.out.println("done admin");
                    log.setNextScene(n);
                } else if (rs.getInt("profil") == 2) {
                    //zalogowano jako bibliotekarz
                    //login 111 pass 1234
                    n = 1;
                    System.out.println("done bibliotekarz");
                    log.setNextScene(n);
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
        return n;
    }

    @FXML
    private void zarejestruj(ActionEvent event) {

        poprawne_dane1 = true;
        boolean czyszczenie = false;
        String imie = rejestracjaImie.getText().trim();
        String nazwisko = rejestracjaNazwisko.getText().trim();
        String email = rejestracjaEmail.getText().trim();
        String haslo = rejestracjaHaslo.getText().trim();

        if (rejestracjaImie.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj imię!");
        } else if (rejestracjaNazwisko.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj nazwisko!");
        } else if (rejestracjaEmail.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj e-mail!");
        } else if (rejestracjaHaslo.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj hasło!");
        } else if (rejestracjaHaslo1.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Powtórz hasło!");
        } else if (!rejestracjaHaslo.getText().equals(rejestracjaHaslo1.getText())) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Złe hasła!", "Hasła muszą być takie same!");
        } else if (rejestracjaNrIdentyfikacyjny.getText().isEmpty()) {
            DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj numer identyfikacyjny!");
        } else if (!(rejestracjaNrIdentyfikacyjny.getText().trim().isEmpty())) {
            int nr_identyfikacyjny = Integer.parseInt(rejestracjaNrIdentyfikacyjny.getText().trim());
            int rozmiar = lista.size();
            int pomocnicza;
            for (int i = 0; i < rozmiar; i++) {
                pomocnicza = lista.get(i);
                if (nr_identyfikacyjny == pomocnicza) {
                    poprawne_dane1 = false;
                    break;
                }
            }
            if (poprawne_dane1 == false) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Zły numer identyfikacyjny!", "Numer identyfikacyjny jest zajęty! \n Musisz podać inny numer");
            }
            if (nr_identyfikacyjny < 0) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podano zły numer identyfikacyjny!");
            } else {
                try {
                    Client client = new Client();
                    client.openConnect();
                    String sql = "INSERT INTO klienci (imie_k, nazwisko_k, nr_identyfikacji_k, email_k, haslo_k, kara, profil) VALUES (?,?,?,?,?,0,3)";
                    String sql2 = "INSERT INTO klienci (imie_k, nazwisko_k, nr_identyfikacji_k, email_k, haslo_k, kara, profil) VALUES (?,?,?,?,?,0,4)";
                    if (radioTak.isSelected() == true && poprawne_dane1 == true) {
                        st = client.connection.prepareStatement(sql);
                        st.setString(1, imie);
                        st.setString(2, nazwisko);
                        st.setInt(3, nr_identyfikacyjny);
                        st.setString(4, email);
                        st.setString(5, haslo);
                        st.execute();
                        DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Udana rejestracja!", "Witamy " + rejestracjaImie.getText() + " " + rejestracjaNazwisko.getText());
                        czyszczenie = true;

                    } else if (radioNie.isSelected() == true && poprawne_dane1 == true) {
                        st = client.connection.prepareStatement(sql2);
                        st.setString(1, imie);
                        st.setString(2, nazwisko);
                        st.setInt(3, nr_identyfikacyjny);
                        st.setString(4, email);
                        st.setString(5, haslo);
                        st.execute();
                        DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Udana rejestracja!", "Witamy " + rejestracjaImie.getText() + " " + rejestracjaNazwisko.getText());
                        czyszczenie = true;
                    }

                    if (czyszczenie == true) {
                        rejestracjaImie.clear();
                        rejestracjaNazwisko.clear();
                        rejestracjaEmail.clear();
                        rejestracjaHaslo.clear();
                        rejestracjaHaslo1.clear();
                        rejestracjaNrIdentyfikacyjny.clear();
                        radioNie.setSelected(false);
                        radioTak.setSelected(false);
                    }

                    rs.close();
                    client.connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LogowanieOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
