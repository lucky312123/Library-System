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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import library.system.dialogs.DialogsUtils;

public class CzytelnikOknoController extends User implements Initializable {

    @FXML
    private ToggleGroup styleGroup;
    @FXML
    private TextField tytulSzukanie;
    @FXML
    private TextField gatunekSzukanie;
    @FXML
    private TextField imieASzukanie;
    @FXML
    private TextField nazwiskoASzukanie;
    @FXML
    private Button wyszukajKsiazkeBTN;
    @FXML
    private TableView<Ksiazki> tableWyszukajKsiazki;
    @FXML
    private TableColumn<?, ?> columnTytulWyszukaj;
    @FXML
    private TableColumn<?, ?> columnISBNWyszukaj;
    @FXML
    private TableColumn<?, ?> columnImieWyszukaj;
    @FXML
    private TableColumn<?, ?> columnNazwiskoWyszukaj;
    @FXML
    private TableColumn<?, ?> columnData_wydWyszukaj;
    @FXML
    private TableColumn<?, ?> columnGatunekWyszukaj;
    @FXML
    private TableColumn<?, ?> columnStatusWyszukaj;
    @FXML
    private Button wyczyscWyszukiwanieBTN;
    @FXML
    private TableView<MojeKsiazki> tableMojeKsiazki;
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
    private Label karaPole;
    @FXML
    private Label ilosc_wypPole;
    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
    ObservableList<MojeKsiazki> mojeksiazki_list = FXCollections.observableArrayList();
    @FXML
    private Button btnWczytajKsiazki;
    ResultSet rs;
    PreparedStatement st;
    @FXML
    private TextField imieDane;
    @FXML
    private TextField nazwiskoDane;
    @FXML
    private TextField nr_identyfikacyjnyDane;
    @FXML
    private TextField emailDane;
    @FXML
    private TableColumn<?, ?> columnIloscWyszukaj;
    @FXML
    private Button edytujDaneBTN;

    @FXML
    private TableColumn<?, ?> columnStatusMoje_ks;

    Client client = new Client();

    @FXML
    public void wczytajKsiazki(ActionEvent event) throws Exception {
        wczytajKsiazki(ksiazki_list);
        //To change body of generated methods, choose Tools | Templates.

        tableWyszukajKsiazki.setItems(ksiazki_list);
    }

    @FXML
    private void wyszukajKsiazki(ActionEvent event) {
        try {
            String tytul = tytulSzukanie.getText().trim();
            String imie_a = imieASzukanie.getText().trim();
            String nazwisko_a = nazwiskoASzukanie.getText().trim();
            String gatunek = gatunekSzukanie.getText().trim();
            wyszukaj(ksiazki_list, tytul, imie_a, nazwisko_a, gatunek);
        } catch (Exception ex) {
            Logger.getLogger(CzytelnikOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void wyczyscWyszukiwanie() {
        tytulSzukanie.clear();
        imieASzukanie.clear();
        nazwiskoASzukanie.clear();
        gatunekSzukanie.clear();
        ksiazki_list.clear();
    }

    private void wczytanieDanych() {
        System.out.println("ID zalogowanego " + LogowanieOknoController.przekazanieloginu);
        try {
            Client client = new Client();
            client.openConnect();

            String sql = "SELECT imie_k,nazwisko_k,nr_identyfikacji_k,email_k from klienci where id_klienta=?";

            st = client.connection.prepareStatement(sql);
            st.setInt(1, LogowanieOknoController.przekazanieloginu);
            rs = st.executeQuery();

            while (rs.next()) {
                imieDane.setText(rs.getString("imie_k"));
                nazwiskoDane.setText(rs.getString("nazwisko_k"));
                nr_identyfikacyjnyDane.setText(rs.getString("nr_identyfikacji_k"));
                emailDane.setText(rs.getString("email_k"));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wczytanieDanych" + sql);
        }

    }

    private void mojeKsiazki() {
        try {
            Client client = new Client();
            client.openConnect();
            String sql3 = "SELECT k.tytul,concat(a.imie_a,' ',a.nazwisko_a) as autor,k.ISBN,g.nazwa_g,w.data_wyp,w.data_zwrotu,s.nazwa_s from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, wypozyczenia w, klienci kl, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and w.id_ksiazki=k.id_ksiazki and w.id_klienta=kl.id_klienta and s.status = k.status and kl.id_klienta=?";

            st = client.connection.prepareStatement(sql3);
            st.setInt(1, LogowanieOknoController.przekazanieloginu);
            rs = st.executeQuery();

            mojeksiazki_list.clear();
            while (rs.next()) {
                mojeksiazki_list.add(new MojeKsiazki(rs.getString("tytul"), rs.getString("autor"), rs.getString("ISBN"), rs.getString("nazwa_g"), rs.getString("data_wyp"), rs.getString("data_zwrotu"), rs.getString("nazwa_s")));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z mojeKsiazki" + sql);
        }
    }

    private void iloscKsiazek() {
        try {
            Client client = new Client();
            client.openConnect();
            String sql3 = "select count(w.id_klienta) ilosc_wyp, kl.kara from wypozyczenia w, klienci kl where w.id_klienta=kl.id_klienta and kl.id_klienta=?";

            st = client.connection.prepareStatement(sql3);
            st.setInt(1, LogowanieOknoController.przekazanieloginu);
            rs = st.executeQuery();

            while (rs.next()) {
                ilosc_wypPole.setText(rs.getString("ilosc_wyp"));
                karaPole.setText(rs.getString("kara"));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z iloscKsiazek" + sql);
        }
    }

    @FXML
    private void edytujDane(ActionEvent event) {
        try {
            Client client = new Client();
            client.openConnect();
            String imie = imieDane.getText().trim();
            String nazwisko = nazwiskoDane.getText().trim();
            String email = emailDane.getText().trim();

            String sql4 = "update klienci set imie_k =?, nazwisko_k =?, email_k =? where id_klienta =?";

            st = client.connection.prepareStatement(sql4);

            st.setString(1, imie);
            st.setString(2, nazwisko);
            st.setString(3, email);
            st.setInt(4, LogowanieOknoController.przekazanieloginu);

            st.executeUpdate();

            st.close();
            client.connection.close();
            wczytanieDanych();
            DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Zaktualizowano dane!", "Twoje nowe dane: " + imieDane.getText() + " " + nazwiskoDane.getText() + "\n email: " + emailDane.getText());

        } catch (SQLException sql) {
            System.out.println("Problem z wczytanieDanych" + sql);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Wczytanie danych
        wczytanieDanych();
        mojeKsiazki();
        iloscKsiazek();

        ///Dodanie do tabel elementów list
        tableWyszukajKsiazki.setItems(null);
        tableWyszukajKsiazki.setItems(ksiazki_list);
        tableMojeKsiazki.setItems(null);
        tableMojeKsiazki.setItems(mojeksiazki_list);

        nr_identyfikacyjnyDane.setEditable(false);
        nr_identyfikacyjnyDane.setMouseTransparent(true);
        nr_identyfikacyjnyDane.setFocusTraversable(false);

        //Przypisanie komórek z TableView do zmienych
        //Wyszukanie ksiazek
        columnTytulWyszukaj.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnISBNWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnImieWyszukaj.setCellValueFactory(new PropertyValueFactory<>("imie_a"));
        columnNazwiskoWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwisko_a"));
        columnData_wydWyszukaj.setCellValueFactory(new PropertyValueFactory<>("data_wyd"));
        columnGatunekWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnStatusWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));
        columnIloscWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        //Zakładka moje książki
        columnTytulMoje_ks.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnAutorMoje_ks.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnISBNMoje_ks.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnGatunekMoje_ks.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnData_wypMoje_ks.setCellValueFactory(new PropertyValueFactory<>("data_wyp"));
        columnData_zwrMoje_ks.setCellValueFactory(new PropertyValueFactory<>("data_zwrotu"));
        columnStatusMoje_ks.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));
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
