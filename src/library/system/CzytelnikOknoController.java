package library.system;

import Database.Client;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import library.system.dialogs.DialogsUtils;

public class CzytelnikOknoController implements Initializable {

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
    private Label karaPole;
    @FXML
    private Label ilosc_wypPole;
    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
    @FXML
    private Button btnWczytajKsiazki;
    ResultSet rs;
    PreparedStatement st;

    @FXML
    private void wczytajKsiazki(ActionEvent event) throws Exception {
        try {
            Client client = new Client();
            client.openConnect();
            String sql = "SELECT k.tytul,k.ISBN,a.imie_a,a.nazwisko_a,k.data_wyd,g.nazwa_g,s.nazwa_s from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and k.status=s.status";

            st = client.connection.prepareStatement(sql);
            rs = st.executeQuery();

            ksiazki_list.clear();
            while (rs.next()) {
                ksiazki_list.add(new Ksiazki(rs.getString("tytul"), rs.getString("ISBN"), rs.getString("imie_a"), rs.getString("nazwisko_a"), rs.getString("data_wyd"), rs.getString("nazwa_g"), rs.getString("nazwa_s")));
                //System.out.println(rs.getString("tytul") + " " + rs.getString("ISBN")+ " " + rs.getString("nazwisko_a")+ " " + rs.getString("data_wyd")+ " " + rs.getString("nazwa_g")+ " " + rs.getString("nazwa_s"));

            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            //złe sql
            System.out.println("bec2" + sql);
        }
    }

    @FXML
    private void wyszukajKsiazki(ActionEvent event) {
        try {
            Client client = new Client();
            client.openConnect();
            String tytul = tytulSzukanie.getText().trim();
            String imie_a = imieASzukanie.getText().trim();
            String nazwisko_a = nazwiskoASzukanie.getText().trim();
            String gatunek = gatunekSzukanie.getText().trim();
            String sql2 = "SELECT k.tytul,k.ISBN,a.imie_a,a.nazwisko_a,k.data_wyd,g.nazwa_g,s.nazwa_s from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and k.status=s.status and k.tytul=? and a.imie_a=? and a.nazwisko_a=? and g.nazwa_g=?";

            st = client.connection.prepareStatement(sql2);

            st.setString(1, tytul);
            st.setString(2, imie_a);
            st.setString(3, nazwisko_a);
            st.setString(4, gatunek);

            rs = st.executeQuery();

            ksiazki_list.clear();
            while (rs.next()) {
                ksiazki_list.add(new Ksiazki(rs.getString("tytul"), rs.getString("ISBN"), rs.getString("imie_a"), rs.getString("nazwisko_a"), rs.getString("data_wyd"), rs.getString("nazwa_g"), rs.getString("nazwa_s")));

            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            //złe sql
            System.out.println("bec2" + sql);
        }
    }

    @FXML
    private void wyczyscWyszukiwanie(ActionEvent event) {
        tytulSzukanie.clear();
        imieASzukanie.clear();
        nazwiskoASzukanie.clear();
        gatunekSzukanie.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ///Dodanie do tabel elementów list
        tableWyszukajKsiazki.setItems(null);
        tableWyszukajKsiazki.setItems(ksiazki_list);

        //Przypisanie komórek z TableView do zmienych
        //Wyszukanie ksiazek
        columnTytulWyszukaj.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnISBNWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnImieWyszukaj.setCellValueFactory(new PropertyValueFactory<>("imie_a"));
        columnNazwiskoWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwisko_a"));
        columnData_wydWyszukaj.setCellValueFactory(new PropertyValueFactory<>("data_wyd"));
        columnGatunekWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnStatusWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));

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
