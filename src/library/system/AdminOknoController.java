package library.system;

import Database.Client;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import library.system.dialogs.DialogsUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AdminOknoController extends User implements Initializable {

    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
    ObservableList<Gatunki> gatunki_list = FXCollections.observableArrayList();
    ObservableList<Autorzy> autorzy_list = FXCollections.observableArrayList();
    ObservableList<MojeKsiazki> mojeksiazki_list = FXCollections.observableArrayList();
    ObservableList<Bibliotekarz> bibliotekarz_list = FXCollections.observableArrayList();
    LibrarySystem log = new LibrarySystem();
    ObservableList<String> gatunki = FXCollections.observableArrayList();
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
    private ToggleGroup styleGroup;
    @FXML
    private TextField tytulDodawanieKsiazka;
    @FXML
    private TextField isbnDodawanieKsiazka;
    @FXML
    private DatePicker data_wydDodawanieKsiazka;
    @FXML
    private TextField procent_zniszczeniaDodawanieKsiazka;
    @FXML
    private ComboBox<String> autor_dodajComboBox;
    @FXML
    private ComboBox<String> gatunek_dodajComboBox;
    @FXML
    private Button dodajKsiazkeBTN;
    @FXML
    private TextField nazwaDodawanieGatunek;
    @FXML
    private TextArea opisDodawanieGatunek;
    @FXML
    private Button dodajGatunekBTN;
    @FXML
    private Button btnZapisz;
    @FXML
    private Button usunBtn;
    @FXML
    private Button usun_gBtn;
    @FXML
    private DatePicker data_urDodawanie;
    private TableView<Gatunki> tableWyszukajGatunek;
    @FXML
    private TableView<Autorzy> tableWyszukajAutora;
    @FXML
    private TableView<Ksiazki> tableWyszukajKsiazki;

    //do edycji trzeba <?,?> zmienć na <ksiazki, (typ-string int itd)>
    @FXML
    private TableColumn<Ksiazki, String> columnTytulWyszukaj;
    @FXML
    private TableColumn<Ksiazki, String> columnISBNWyszukaj;
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
    private TableColumn<?, ?> columnIloscWyszukaj;
    Client client = new Client();
    String tytulp = "1";
    String tytul1 = "2";
    String ISBN = "1";
    String pZniszczeniaNowe = "0";
    String pZniszczeniaStare;
    @FXML
    private Button btnWczytajKsiazki;
    @FXML
    private Button btnWczytajGatunki;
    @FXML
    private Button wyczyscWyszukiwanieBTN;
    @FXML
    private TextField autorImieDodawanie;
    @FXML
    private TextField autorNazwiskoDodawanie;
    @FXML
    private TextField autorPseudonimDodawanie;
    @FXML
    private TextField l_egzemplarzy;
    @FXML
    private Button dodajAutoraBTN;
    @FXML
    private TextField nr_identyfikacjiTextField;
    @FXML
    private TableView<MojeKsiazki> tableWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnTytulWpozyczenia;
    @FXML
    private TableColumn<?, ?> columnAutorWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnISBNWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnGatunekWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnData_wypWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnData_zwrWypozyczenia;
    @FXML
    private TableColumn<?, ?> columnStatusWypozyczenia;
    @FXML
    private Label karaPole;
    @FXML
    private Label ilosc_wypPole;
    @FXML
    private Button wyszukajUzytkownikaBTN;
    @FXML
    private Label imiePole;
    @FXML
    private Label nazwiskoPole;
    @FXML
    private Label emialPole;
    private Label statusPole;
    @FXML
    private Button wypozyczKsiazkeBTN;
    String nr_identyfikacji;
    @FXML
    private Button btnWyloguj;
    @FXML
    private TableColumn<Autorzy, String> columnImieWyszukaj1;
    @FXML
    private TableColumn<Autorzy, String> columnNazwiskoWyszukaj1;
    @FXML
    private TableColumn<Autorzy, String> columnPseudonimWyszukaj1;
    @FXML
    private TableColumn<Autorzy, String> columnData_uWyszukaj1;
    @FXML
    private Button btnWczytajAutora;
    @FXML
    private Button wyczyscWyszukiwanieBTN1;
    @FXML
    private Button wyczyscWyszukiwanieBTN11;
    @FXML
    private TableColumn<MojeKsiazki, String> columnZniszczenieWypozyczenia;
    @FXML
    private Button zwrocKsiazkeBTN;
    @FXML
    private TableView<Gatunki> tableDodajGatunek;
    @FXML
    private TableColumn<Gatunki, String> columnNazwaDodawanieGatunku;
    @FXML
    private TableColumn<Gatunki, String> columnOpisDodawanieGatunku;

    @FXML
    private Button usun_aBtn;
    @FXML
    private Button karaZaplaconaBTN;
    @FXML
    private Button wyczyscDodawanieKsiazkiBTN;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private Button zatwierdzBTN;
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
    private TextField rejestracjaNrIdentyfikacyjny;
    @FXML
    private Button zarejestrujBTN;
    public static List<Integer> lista = new ArrayList();
    boolean poprawne_dane;
    boolean poprawne_dane1;
    @FXML
    private TableView<Bibliotekarz> tableBibliotekarze;
    @FXML
    private TableColumn<?, ?> columnImieBibliotekarza;
    @FXML
    private TableColumn<?, ?> columnNazwiskoBibliotekarza;
    @FXML
    private TableColumn<?, ?> columnNrIdentBibliotekarza;
    @FXML
    private TableColumn<?, ?> columnEmailBibliotekarza;
    @FXML
    private Button wyswietlBibliotekarzyBTN;
    @FXML
    private Button usunbibliotekarzaBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableWyszukajKsiazki.setItems(null);
        tableWyszukajKsiazki.setItems(ksiazki_list);
        tableWypozyczenia.setItems(null);
        tableWypozyczenia.setItems(mojeksiazki_list);
        tableDodajGatunek.setItems(null);
        tableDodajGatunek.setItems(gatunki_list);
        tableWyszukajAutora.setItems(null);
        tableWyszukajAutora.setItems(autorzy_list);
        tableBibliotekarze.setItems(null);
        tableBibliotekarze.setItems(bibliotekarz_list);

        columnTytulWyszukaj.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnISBNWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnImieWyszukaj.setCellValueFactory(new PropertyValueFactory<>("imie_a"));
        columnNazwiskoWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwisko_a"));
        columnData_wydWyszukaj.setCellValueFactory(new PropertyValueFactory<>("data_wyd"));
        columnGatunekWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnStatusWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));
        columnIloscWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        columnNazwaDodawanieGatunku.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnOpisDodawanieGatunku.setCellValueFactory(new PropertyValueFactory<>("opis"));

        columnImieWyszukaj1.setCellValueFactory(new PropertyValueFactory<>("imie_a"));
        columnNazwiskoWyszukaj1.setCellValueFactory(new PropertyValueFactory<>("nazwisko_a"));
        columnPseudonimWyszukaj1.setCellValueFactory(new PropertyValueFactory<>("pseudonim"));
        columnData_uWyszukaj1.setCellValueFactory(new PropertyValueFactory<>("data_ur"));

        columnTytulWpozyczenia.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnAutorWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnISBNWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnGatunekWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnData_wypWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("data_wyp"));
        columnData_zwrWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("data_zwrotu"));
        columnStatusWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));
        columnZniszczenieWypozyczenia.setCellValueFactory(new PropertyValueFactory<>("aktPzniszczenia"));

        columnImieBibliotekarza.setCellValueFactory(new PropertyValueFactory<>("imie_p"));
        columnNazwiskoBibliotekarza.setCellValueFactory(new PropertyValueFactory<>("nazwisko_p"));
        columnNrIdentBibliotekarza.setCellValueFactory(new PropertyValueFactory<>("nr_identyfikacji_p"));
        columnEmailBibliotekarza.setCellValueFactory(new PropertyValueFactory<>("email_p"));

        usunBtn.disableProperty().bind(tableWyszukajKsiazki.getSelectionModel().selectedItemProperty().isNull());
        btnZapisz.disableProperty().bind(tableWyszukajKsiazki.getSelectionModel().selectedItemProperty().isNull());
        wypozyczKsiazkeBTN.disableProperty().bind(tableWypozyczenia.getSelectionModel().selectedItemProperty().isNull());
        zwrocKsiazkeBTN.disableProperty().bind(tableWypozyczenia.getSelectionModel().selectedItemProperty().isNull());

        usun_gBtn.disableProperty().bind(tableDodajGatunek.getSelectionModel().selectedItemProperty().isNull());
        usun_aBtn.disableProperty().bind(tableWyszukajAutora.getSelectionModel().selectedItemProperty().isNull());

        dodajGatunekBTN.disableProperty().bind(nazwaDodawanieGatunek.textProperty().isEmpty());
        dodajGatunekBTN.disableProperty().bind(opisDodawanieGatunek.textProperty().isEmpty());
        dodajAutoraBTN.disableProperty().bind(autorImieDodawanie.textProperty().isEmpty());
        dodajAutoraBTN.disableProperty().bind(autorNazwiskoDodawanie.textProperty().isEmpty());
        dodajAutoraBTN.disableProperty().bind(autorPseudonimDodawanie.textProperty().isEmpty());
        dodajAutoraBTN.disableProperty().bind(data_urDodawanie.valueProperty().isNull());

        dodajKsiazkeBTN.disableProperty().bind(tytulDodawanieKsiazka.textProperty().isEmpty());
        dodajKsiazkeBTN.disableProperty().bind(isbnDodawanieKsiazka.textProperty().isEmpty());
        dodajKsiazkeBTN.disableProperty().bind(procent_zniszczeniaDodawanieKsiazka.textProperty().isEmpty());
        dodajKsiazkeBTN.disableProperty().bind(autor_dodajComboBox.valueProperty().isNull());
        dodajKsiazkeBTN.disableProperty().bind(gatunek_dodajComboBox.valueProperty().isNull());
        dodajKsiazkeBTN.disableProperty().bind(l_egzemplarzy.textProperty().isEmpty());
        dodajKsiazkeBTN.disableProperty().bind(data_wydDodawanieKsiazka.valueProperty().isNull());

        karaZaplaconaBTN.disableProperty().bind(nr_identyfikacjiTextField.textProperty().isEmpty());

        edycjaKsiazki();
        edycjaGatunki();
        edycjaAutora();
        getGatunki();
        edycjaZniszczenia();
        statusCombo.getItems().clear();
        statusCombo.getItems().addAll("Bibliotekarz");

        pobranieNrIdentyfikacjiP();
    }

    /**
     * edycja gatunków
     */
    public void getGatunki() {
        client.openConnect();
        String sql = "SELECT g.nazwa_g FROM gatunki g";
        String sql3 = "SELECT nazwisko_a FROM autorzy";
        ResultSet rs;
        PreparedStatement preparedStmt, preparedStmt1, preparedStmt2;
        gatunek_dodajComboBox.getItems().clear();
        gatunki.clear();
        try {
            preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt2 = client.connection.prepareStatement(sql3);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                gatunki.add(rs.getString("nazwa_g"));

            }
            autor_dodajComboBox.getItems().clear();
            rs = preparedStmt2.executeQuery();

            while (rs.next()) {
                autor_dodajComboBox.getItems().addAll(rs.getString("nazwisko_a"));
            }
            client.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gatunek_dodajComboBox.getItems().addAll(gatunki);

    }

    public void edycjaKsiazki() {
        client.openConnect();
        tableWyszukajKsiazki.setEditable(true);
        columnTytulWyszukaj.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTytulWyszukaj.setOnEditCommit(
                new EventHandler<CellEditEvent<Ksiazki, String>>() {
            @Override
            public void handle(CellEditEvent<Ksiazki, String> t) {
                //tytul = t.getOldValue();
                tytul1 = t.getOldValue();
                ((Ksiazki) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setTytul(t.getNewValue());
                tytulp = t.getNewValue();

            }
        }
        );

        columnISBNWyszukaj.setCellFactory(TextFieldTableCell.forTableColumn());
        columnISBNWyszukaj.setOnEditCommit(
                new EventHandler<CellEditEvent<Ksiazki, String>>() {
            @Override
            public void handle(CellEditEvent<Ksiazki, String> t) {
                //ISBN = t.getOldValue();
                System.out.println("ISBNold " + ISBN);
                ((Ksiazki) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setISNB(t.getNewValue());
                ISBN = t.getNewValue();
                System.out.print("ISBNnew " + ISBN);

            }
        }
        );
    }

    public void edycjaGatunki() {

        String sql2 = "update gatunki set nazwa_g=? where nazwa_g=?";

        tableDodajGatunek.setEditable(true);
        columnOpisDodawanieGatunku.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNazwaDodawanieGatunku.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNazwaDodawanieGatunku.setOnEditCommit(
                new EventHandler<CellEditEvent<Gatunki, String>>() {
            @Override
            public void handle(CellEditEvent<Gatunki, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql2);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        String sql3 = "update gatunki set opis=? where opis=?";
        columnOpisDodawanieGatunku.setOnEditCommit(
                new EventHandler<CellEditEvent<Gatunki, String>>() {
            @Override
            public void handle(CellEditEvent<Gatunki, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql3);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

    }

    public void edycjaAutora() {

        String sql6 = "update autorzy set imie_a=? where imie_a=?";

        tableWyszukajAutora.setEditable(true);
        columnImieWyszukaj1.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNazwiskoWyszukaj1.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPseudonimWyszukaj1.setCellFactory(TextFieldTableCell.forTableColumn());
        columnData_uWyszukaj1.setCellFactory(TextFieldTableCell.forTableColumn());
        columnImieWyszukaj1.setOnEditCommit(
                new EventHandler<CellEditEvent<Autorzy, String>>() {
            @Override
            public void handle(CellEditEvent<Autorzy, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql6);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        String sql3 = "update autorzy set nazwisko_a=? where nazwisko_a=?";
        columnNazwiskoWyszukaj1.setOnEditCommit(
                new EventHandler<CellEditEvent<Autorzy, String>>() {
            @Override
            public void handle(CellEditEvent<Autorzy, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql3);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        String sql4 = "update autorzy set pseudonim=? where pseudonim=?";
        columnPseudonimWyszukaj1.setOnEditCommit(
                new EventHandler<CellEditEvent<Autorzy, String>>() {
            @Override
            public void handle(CellEditEvent<Autorzy, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql4);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        String sql5 = "update autorzy set data_ur=? where data_ur=?";
        columnData_uWyszukaj1.setOnEditCommit(
                new EventHandler<CellEditEvent<Autorzy, String>>() {
            @Override
            public void handle(CellEditEvent<Autorzy, String> t) {
                try {
                    client.openConnect();
                    //tytul = t.getOldValue();
                    PreparedStatement state = client.connection.prepareStatement(sql5);
                    state.setString(1, t.getNewValue());
                    state.setString(2, t.getOldValue());
                    System.out.print(t.getNewValue());
                    state.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

    }

    public void edycjaZniszczenia() {
        MojeKsiazki k = tableWypozyczenia.getSelectionModel().getSelectedItem();
        tableWypozyczenia.setEditable(true);
        columnZniszczenieWypozyczenia.setCellFactory(TextFieldTableCell.forTableColumn());
        columnZniszczenieWypozyczenia.setOnEditCommit((CellEditEvent<MojeKsiazki, String> t) -> {
            pZniszczeniaStare = t.getOldValue();
            System.out.println(pZniszczeniaStare);
            ((MojeKsiazki) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setAktPzniszczenia(t.getNewValue());
            pZniszczeniaNowe = t.getNewValue();
            System.out.println(pZniszczeniaNowe);
        });
    }

    /**
     * wczytywanie wszystkich ksiażek do tabeli
     */
    @FXML
    public void wczytajKsiazki(ActionEvent event) throws Exception {
        wczytajKsiazki(ksiazki_list);

    }

    /**
     * wczytywanie wszystkich gatunków do tabeli
     */
    @FXML
    private void wczytajGatunki() throws Exception {
        wczytajGatunki(gatunki_list);
    }

    /**
     * wczytywanie wszystkich autorów do tabeli
     */
    @FXML
    private void wczytajAutora() throws Exception {
        wczytajAutora(autorzy_list);
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

    @FXML
    private void wyczyscWypozyczeniaZwroty() {
        nr_identyfikacjiTextField.clear();
        karaPole.setText("");
        ilosc_wypPole.setText("");
        imiePole.setText("");
        nazwiskoPole.setText("");
        emialPole.setText("");
        //statusPole.setText("");
        mojeksiazki_list.clear();
    }

    @FXML
    private void wyczyscWyszukiwanieGatunku(ActionEvent event) {

        gatunki_list.clear();
        nazwaDodawanieGatunek.clear();
        opisDodawanieGatunek.clear();
    }

    @FXML
    private void wyczyscWyszukiwanieAutora(ActionEvent event) {

        autorzy_list.clear();
        autorImieDodawanie.clear();
        autorNazwiskoDodawanie.clear();
        autorPseudonimDodawanie.clear();
        data_urDodawanie.getEditor().clear();
    }

    @FXML
    private void wyczyscDodawanieKsiazki() {
        tytulDodawanieKsiazka.clear();
        isbnDodawanieKsiazka.clear();
        data_wydDodawanieKsiazka.getEditor().clear();
        procent_zniszczeniaDodawanieKsiazka.clear();
        l_egzemplarzy.clear();
        autor_dodajComboBox.getSelectionModel().clearSelection();
        gatunek_dodajComboBox.getSelectionModel().clearSelection();
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @FXML
    private void dodajKsiazke() {

        try {
            int i = 1;
            int procentZ = 1;
            client.openConnect();
            String tytul = tytulDodawanieKsiazka.getText().trim();
            String ISBN = isbnDodawanieKsiazka.getText().trim();
            LocalDate localDate = data_wydDodawanieKsiazka.getValue();

            if (tytulDodawanieKsiazka.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj nazwę książki!");
            } else if (isbnDodawanieKsiazka.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj ISBN książki!");
            } else if (procent_zniszczeniaDodawanieKsiazka.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podano zły procent zniszczenia!");
            } else if (data_wydDodawanieKsiazka.getValue().equals("")) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Nie podano daty!");
            } else if (gatunek_dodajComboBox.getSelectionModel().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Nie wybrano gatunku!");
            } else if (autor_dodajComboBox.getSelectionModel().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Nie wybrano autora!");
            } else if (procent_zniszczeniaDodawanieKsiazka.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podano zły procent zniszczenia!");
            } else if (l_egzemplarzy.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Zła liczba egzemplarzy!");
            } else if (!(procent_zniszczeniaDodawanieKsiazka.getText().trim().isEmpty())) {
                procentZ = Integer.parseInt(procent_zniszczeniaDodawanieKsiazka.getText().trim());
                if (procentZ < 0) {
                    DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podano zły procent zniszczenia!");
                    System.out.println("ZLYYYYYYYYYYYYYYYYYY PROCENT");
                } else if (!(l_egzemplarzy.getText().trim().isEmpty())) {
                    i = Integer.parseInt(l_egzemplarzy.getText().trim());
                    if (i < 0) {
                        DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Zła liczba egzemplarzy!");
                        System.out.println("ZLYYYYYYYYYYYYYYYYYY Liczba egzemplarzy");
                    } else {
                        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

                        String sql5 = "SELECT id_gatunku FROM gatunki WHERE nazwa_g = ?";
                        PreparedStatement preparedStmt5 = client.connection.prepareStatement(sql5);
                        preparedStmt5.setString(1, gatunek_dodajComboBox.getSelectionModel().getSelectedItem().toString());
                        rs = preparedStmt5.executeQuery();
                        int id_g = -1;
                        while (rs.next()) {
                            System.out.print("id gatunku " + rs.getInt("id_gatunku"));
                            id_g = rs.getInt("id_gatunku");
                        }
                        System.out.println(i);
                        for (int x = 0; x < i; x++) {
                            try {
                                Date d = dt.parse(dt.format(date));
                                client.openConnect();
                                String sql = "INSERT INTO ksiazka (tytul, ISBN, data_wyd,aktPzniszczenia,status,id_gatunku) values (?,?,?,?,1,?)";
                                PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
                                preparedStmt.setString(1, tytul);
                                preparedStmt.setString(2, ISBN);
                                java.sql.Date sDate = convertUtilToSql(d);
                                System.out.println(sDate);
                                preparedStmt.setDate(3, sDate);
                                preparedStmt.setInt(4, procentZ);
                                preparedStmt.setInt(5, id_g);

                                preparedStmt.execute();
                                client.connection.close();
                            } catch (SQLException exception) {
                                Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, exception);
                            } catch (ParseException ex) {
                                Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        String sql2 = "SELECT id_autora FROM autorzy WHERE nazwisko_a =?";
                        client.openConnect();
                        PreparedStatement preparedStmt = client.connection.prepareStatement(sql2);
                        preparedStmt.setString(1, autor_dodajComboBox.getSelectionModel().getSelectedItem());
                        rs = preparedStmt.executeQuery();
                        int id_a = -1;
                        while (rs.next()) {
                            System.out.print(rs.getInt("id_autora"));
                            id_a = rs.getInt("id_autora");
                        }

                        String sql3 = "SELECT id_ksiazki FROM ksiazka WHERE tytul =?";
                        String sql4 = "INSERT INTO autorzy_ksiazki (id_ksiazki, id_autora) values (?,?) ";
                        client.openConnect();
                        PreparedStatement preparedStmt2 = client.connection.prepareStatement(sql3);

                        preparedStmt2.setString(1, tytul);
                        rs = preparedStmt2.executeQuery();
                        while (rs.next()) {
                            System.out.print(rs.getInt("id_ksiazki"));
                            PreparedStatement preparedStmt3 = client.connection.prepareStatement(sql4);
                            preparedStmt3.setInt(1, rs.getInt("id_ksiazki"));
                            preparedStmt3.setInt(2, id_a);
                            preparedStmt3.execute();

                        }
                        DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Dodano książkę!", "Dodana książka to  " + tytulDodawanieKsiazka.getText());
                        wyczyscDodawanieKsiazki();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * metoda wyszukująca konkretnego użytkownika
     */
    @FXML
    private void wyszukajUzytkownika() {
        try {
            client.openConnect();
            int numer_identyfikacjiInt = 0;
            nr_identyfikacji = nr_identyfikacjiTextField.getText().trim();
            if (nr_identyfikacjiTextField.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj numer identyfikatora");
            } else if (!(nr_identyfikacjiTextField.getText().trim().isEmpty())) {
                numer_identyfikacjiInt = Integer.parseInt(nr_identyfikacjiTextField.getText().trim());
                if (numer_identyfikacjiInt < 0) {
                    DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj poprawny numer identyfikatora");
                } else {
                    String sql = "SELECT k.imie_k,k.nazwisko_k,k.email_k,p.nazwa_p,round(k.kara,2) as kara,count(w.id_klienta) ilosc_wyp from klienci k,profil_uzytkownika p, wypozyczenia w where k.profil=p.profil and w.id_klienta=k.id_klienta and k.nr_identyfikacji_k=?";

                    st = client.connection.prepareStatement(sql);
                    st.setString(1, nr_identyfikacji);
                    rs = st.executeQuery();

                    while (rs.next()) {
                        imiePole.setText(rs.getString("imie_k"));
                        nazwiskoPole.setText(rs.getString("nazwisko_k"));
                        emialPole.setText(rs.getString("email_k"));
                        statusPole.setText(rs.getString("nazwa_p"));
                        karaPole.setText(rs.getString("kara"));
                        ilosc_wypPole.setText(rs.getString("ilosc_wyp"));
                    }

                    String sql2 = "SELECT k.tytul,concat(a.imie_a,' ',a.nazwisko_a) as autor,k.ISBN,g.nazwa_g,w.data_wyp,w.data_zwrotu,s.nazwa_s,k.aktPzniszczenia from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, wypozyczenia w, klienci kl, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and w.id_ksiazki=k.id_ksiazki and w.id_klienta=kl.id_klienta and s.status = k.status and kl.nr_identyfikacji_k=?";
                    st = client.connection.prepareStatement(sql2);
                    st.setString(1, nr_identyfikacji);
                    rs = st.executeQuery();

                    mojeksiazki_list.clear();
                    while (rs.next()) {
                        mojeksiazki_list.add(new MojeKsiazki(rs.getString("tytul"), rs.getString("autor"), rs.getString("ISBN"), rs.getString("nazwa_g"), rs.getString("data_wyp"), rs.getString("data_zwrotu"), rs.getString("nazwa_s"), rs.getString("aktPzniszczenia")));
                    }
                    rs.close();
                    client.connection.close();
                }
            }
        } catch (SQLException sql) {
            System.out.println("Problem z wyszukajUzytkownika" + sql);
        }

    }

    @FXML
    private void wypozyczKsiazke(ActionEvent event) {
        MojeKsiazki k = tableWypozyczenia.getSelectionModel().getSelectedItem();
        int idKsiazki = 0;
        String status = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String data = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.MONTH, 2); // narazie data oddania ustawiona na 2 miesiace
        String dataOddania = dateFormat.format(calendar.getTime());
        try {
            client.openConnect();
            String sql = "select k.id_ksiazki,s.nazwa_s from ksiazka k,statusy s where k.status=s.status and k.tytul=? and k.status='3'";
            st = client.connection.prepareStatement(sql);
            st.setString(1, k.getTytul());
            rs = st.executeQuery();

            if (rs.next()) {
                idKsiazki = rs.getInt("id_ksiazki");
                status = rs.getString("nazwa_s");
            }

            if (status.contentEquals("zarezerwowana")) {
                String sql2 = "update wypozyczenia set data_wyp =?, data_zwrotu=? where id_ksiazki=?";
                st = client.connection.prepareStatement(sql2);
                st.setString(1, data);
                st.setString(2, dataOddania);
                st.setInt(3, idKsiazki);
                st.executeUpdate();

                String sql3 = "update ksiazka set status =? where id_ksiazki =?";
                st = client.connection.prepareStatement(sql3);
                st.setString(1, "2");
                st.setInt(2, idKsiazki);
                st.executeUpdate();

                rs.close();
                client.connection.close();
                DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Wypożyczono książkę!", "Książka została wypożyczona dla " + imiePole.getText() + " " + nazwiskoPole.getText());
                wyszukajUzytkownika();
            } else {
                DialogsUtils.showAlert(Alert.AlertType.WARNING, "Książka wypożyczona", "Książka została już wypożyczona!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void zwrocKsiazke(ActionEvent event) throws ParseException {
        MojeKsiazki k = tableWypozyczenia.getSelectionModel().getSelectedItem();
        int idKsiazki = 0;
        String status = "";
        String aktPzniszczenia = "";
        String dataZwrotu = "";
        double pobranaKara = 0;
        double kara;
        double kara1;
        String formatKara = "0";
        Date d1;
        Date d2;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String dzisiejszaData = dateFormat.format(calendar.getTime());

        try {
            client.openConnect();
            String sql = "select k.id_ksiazki,s.nazwa_s,k.aktPzniszczenia from ksiazka k,statusy s where k.status=s.status and k.tytul=? and k.status='2'";
            st = client.connection.prepareStatement(sql);
            st.setString(1, k.getTytul());
            rs = st.executeQuery();

            if (rs.next()) {
                idKsiazki = rs.getInt("id_ksiazki");
                status = rs.getString("nazwa_s");
                aktPzniszczenia = rs.getString("aktPzniszczenia");
            }

            if (status.contentEquals("wypozyczona")) {
                String sql3 = "update ksiazka set status =? where id_ksiazki =?";
                st = client.connection.prepareStatement(sql3);
                st.setString(1, "1");
                st.setInt(2, idKsiazki);
                st.executeUpdate();

                String sql1 = "select w.data_zwrotu, k.kara from klienci k, wypozyczenia w where w.id_ksiazki=? or k.id_klienta=?";
                st = client.connection.prepareStatement(sql1);
                st.setInt(1, idKsiazki);
                st.setString(2, nr_identyfikacji);
                rs = st.executeQuery();

                if (rs.next()) {
                    dataZwrotu = rs.getString("data_zwrotu");
                    pobranaKara = rs.getDouble("kara");
                }

                edycjaZniszczenia();
                if (pZniszczeniaNowe.equals("0")) {
                    String sql9 = "UPDATE ksiazka SET aktPzniszczenia = ? WHERE id_ksiazki =?";
                    st = client.connection.prepareStatement(sql9);
                    st.setString(1, aktPzniszczenia);
                    st.setInt(2, idKsiazki);
                    st.executeUpdate();
                    System.out.println("Update rokordu starego ! ID= " + idKsiazki);
                } else {
                    String sql8 = "UPDATE ksiazka SET aktPzniszczenia = ? WHERE id_ksiazki =?";
                    st = client.connection.prepareStatement(sql8);
                    st.setString(1, pZniszczeniaNowe);
                    st.setInt(2, idKsiazki);
                    st.executeUpdate();
                    System.out.println("Update rokordu nowego ! ID= " + idKsiazki);
                }

                String sql2 = "delete from wypozyczenia where id_ksiazki=?";
                st = client.connection.prepareStatement(sql2);
                st.setInt(1, idKsiazki);
                st.execute();
                tableWypozyczenia.getItems().remove(k);
                java.text.DecimalFormat df = new java.text.DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);

                System.out.println("Data zwrotu " + dataZwrotu);
                System.out.println("Dzisiejsza data " + dzisiejszaData);
                System.out.println("Pobrana kara " + pobranaKara);
                d1 = dateFormat.parse(dataZwrotu);
                d2 = dateFormat.parse(dzisiejszaData);
                long dd1 = d1.getTime();
                long dd2 = d2.getTime();
                double roznica = (dd1 - dd2) / 86400000;
                System.out.println("po odjeciu dni " + roznica);
                if (roznica < 0) {
                    kara1 = 0.2 * Math.abs(roznica);
                    formatKara = df.format(kara1);
                    kara = pobranaKara + 0.2 * Math.abs(roznica);
                    String sql4 = "update klienci set kara =? where nr_identyfikacji_k =?";
                    st = client.connection.prepareStatement(sql4);
                    st.setDouble(1, kara);
                    st.setString(2, nr_identyfikacji);
                    st.executeUpdate();
                    DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Zwrócono książkę!", "Książka została zwrocona przez " + imiePole.getText() + " " + nazwiskoPole.getText() + "\nKara wynosi " + formatKara + "zł");
                    wyszukajUzytkownika();
                } else if (roznica > 0) {
                    rs.close();
                    client.connection.close();
                    DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Zwrócono książkę!", "Książka została zwrocona przez " + imiePole.getText() + " " + nazwiskoPole.getText() + "\nKara wynosi " + formatKara + "zł");
                    wyszukajUzytkownika();
                }
            } else {
                DialogsUtils.showAlert(Alert.AlertType.WARNING, "Książka zarezerwowana", "Książka jest zarezerwowana! Nie można zwrócić");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Pobieranie ID książki
     */
    public int getId(String n) {
        int id = -1;
        try {

            PreparedStatement preparedStmt;
            ResultSet rs;
            client.openConnect();
            String sql1 = "Select id_ksiazki From ksiazka Where tytul =?";
            preparedStmt = client.connection.prepareStatement(sql1);
            preparedStmt.setString(1, n);

            rs = preparedStmt.executeQuery();
            //id = rs.getInt("id_ksiazki");
            if (rs.next()) {
                // System.out.print("tutaj " + k.getTytul() + " n " + rs.getInt("id_ksiazki"));
                id = rs.getInt("id_ksiazki");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    /**
     * Edytowanie książki po naciśnięciu przycisku
     */
    @FXML
    public void edycja() throws SQLException {
        PreparedStatement preparedStmt;
        ResultSet rs;
        Ksiazki k = tableWyszukajKsiazki.getSelectionModel().getSelectedItem();
        client.openConnect();
        //System.out.print(k.getTytul());
        int id = getId(tytul1);

        try {

            if (!tytulp.equals("1") && !ISBN.equals("1")) {
                String sql = "UPDATE ksiazka SET tytul = ?, ISBN = ? WHERE id_ksiazki =?";
                preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, tytulp);
                preparedStmt.setString(2, ISBN);
                preparedStmt.setInt(3, id);
                preparedStmt.execute();
                System.out.println("oba" + id);
            } else if (!tytulp.equals("1") && ISBN.equals("1")) {
                String sql = "UPDATE ksiazka SET tytul = ? WHERE id_ksiazki =?";
                preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, tytulp);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                System.out.println("tytul:" + id + " po zmianie: " + tytulp);
            } else if (tytulp.equals("1") && !ISBN.equals("1")) {
                String sql = "UPDATE ksiazka SET ISBN = ? WHERE id_ksiazki =?";
                preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, ISBN);
                id = getId(k.getTytul());
                preparedStmt.setInt(2, id);
                System.out.println("isbn: " + ISBN + " do ksiazki id: " + id);
                preparedStmt.execute();
            }

            //rs.close();
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Edycja książki", "Edycja wykonana pomyślnie!");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void dodajGatunek(ActionEvent event) throws Exception {
        try {
            String nazwaGatunku = nazwaDodawanieGatunek.getText().trim();
            String opisGatunku = opisDodawanieGatunek.getText().trim();
            if (nazwaDodawanieGatunek.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj nazwę gatunku!");
            } else if (opisDodawanieGatunek.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj opis gatunku!");
            } else {

                client.openConnect();
                String sql = "insert into gatunki (nazwa_g, opis) values (?, ?)";
                PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, nazwaGatunku);
                preparedStmt.setString(2, opisGatunku);
                preparedStmt.execute();
                System.out.println("uda");

                client.connection.close();
                DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Dodano nowy gatunek!", "Nowy gatunek: " + nazwaGatunku);
                nazwaDodawanieGatunek.clear();
                opisDodawanieGatunek.clear();
                client.connection.close();
                wczytajGatunki();
                getGatunki();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void dodajAutora(ActionEvent event) throws Exception {
        try {
            String imieAutora = autorImieDodawanie.getText().trim();
            String nazwiskoAutora = autorNazwiskoDodawanie.getText().trim();
            String pseudonimAutora = autorPseudonimDodawanie.getText().trim();
            String data_ur = data_urDodawanie.getValue().toString();

            if (autorImieDodawanie.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj imie autora!");
            } else if (autorNazwiskoDodawanie.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj nazwisko autora!");
            } else if (autorPseudonimDodawanie.getText().trim().isEmpty()) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj pseudonim autora");
            } else if (data_urDodawanie.getValue().equals(null)) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podaj date urodzenia autora");
            } else {

                client.openConnect();
                String sql = "insert into autorzy (imie_a, nazwisko_a, pseudonim, data_ur) values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, imieAutora);
                preparedStmt.setString(2, nazwiskoAutora);
                preparedStmt.setString(3, pseudonimAutora);
                preparedStmt.setString(4, data_ur);
                preparedStmt.execute();

                client.connection.close();
                DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Dodano nowego autora!", "Nowy autor: \n" + imieAutora + " " + nazwiskoAutora + " ");
                autorImieDodawanie.clear();
                autorNazwiskoDodawanie.clear();
                autorPseudonimDodawanie.clear();
                data_urDodawanie.getEditor().clear();
                client.connection.close();
                wczytajAutora();
                getGatunki();
            }
        } catch (SQLException ex) {
            System.out.println("blad");
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usunKsiazke() {
        Ksiazki k = tableWyszukajKsiazki.getSelectionModel().getSelectedItem();
        try {
            client.openConnect();
            String sql = "DELETE FROM ksiazka WHERE tytul = ?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setString(1, k.getTytul());
            preparedStmt.execute();
            tableWyszukajKsiazki.getItems().remove(k);
            System.out.print("usunieto");
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Usuwanie książki", "Książka została usunięta!");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usunGatunek() {
        Gatunki g = tableDodajGatunek.getSelectionModel().getSelectedItem();
        try {
            // narazie nic
            // pobieram
            client.openConnect();
            String sql = "DELETE FROM gatunki WHERE nazwa_g = ?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setString(1, g.getNazwa_g());
            preparedStmt.execute();
            tableDodajGatunek.getItems().remove(g);
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Usuwanie gatunku", "Gatunek został usunięty!");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usunAutora() {
        Autorzy a = tableWyszukajAutora.getSelectionModel().getSelectedItem();
        try {
            client.openConnect();
            String sql = "DELETE FROM autorzy WHERE imie_a = ?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setString(1, a.getImie_a());
            preparedStmt.execute();
            tableWyszukajAutora.getItems().remove(a);
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Usuwanie autora", "Autor został usunięty");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda zerująca kare konkretnego użytkownika
     */
    @FXML
    private void zaplacKare(ActionEvent event) {
        try {
            client.openConnect();
            String sql = "UPDATE klienci SET kara=? WHERE nr_identyfikacji_k =?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setDouble(1, 0);
            preparedStmt.setString(2, nr_identyfikacji);
            preparedStmt.executeUpdate();

            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Kara!", "Kara została wyzerowana");
            wyszukajUzytkownika();
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pobranieNrIdentyfikacjiP() {

        try {
            Client client = new Client();
            client.openConnect();
            String sql5 = "select nr_identyfikacji_p from pracownicy";
            int zmienna;
            lista.clear();
            st = client.connection.prepareStatement(sql5);
            rs = st.executeQuery();
            while (rs.next()) {
                zmienna = rs.getInt("nr_identyfikacji_p");
                lista.add(zmienna);
                System.out.println(lista);
            }
            rs.close();
            client.connection.close();
        } catch (SQLException sql) {
            System.out.println("Błąd z pobranieNrIdentyfikacjiPracownika" + sql);
        }

    }

    @FXML
    private void zarejestruj(ActionEvent event) {

        poprawne_dane1 = true;
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
            } else if (nr_identyfikacyjny < 0) {
                DialogsUtils.showAlert(Alert.AlertType.ERROR, "Nie wpisano danych!", "Podano zły numer identyfikacyjny!");
            } else {

                try {
                    Client client = new Client();
                    client.openConnect();
                    String sql = "INSERT INTO pracownicy (imie_p, nazwisko_p, nr_identyfikacji_p, email_p, haslo_p, profil) VALUES (?,?,?,?,?,2)";
                    st = client.connection.prepareStatement(sql);
                    st.setString(1, imie);
                    st.setString(2, nazwisko);
                    st.setInt(3, nr_identyfikacyjny);
                    st.setString(4, email);
                    st.setString(5, haslo);
                    st.execute();

                    rs.close();
                    client.connection.close();
                    DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Dodano bibliotekarza!", "Nowy bibliotekarz to " + rejestracjaImie.getText() + " " + rejestracjaNazwisko.getText());
                    rejestracjaImie.clear();
                    rejestracjaNazwisko.clear();
                    rejestracjaEmail.clear();
                    rejestracjaHaslo.clear();
                    rejestracjaHaslo1.clear();
                    rejestracjaNrIdentyfikacyjny.clear();
                    wczytajBibliotekarzy();
                } catch (SQLException ex) {
                    Logger.getLogger(LogowanieOknoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @FXML
    private void zamknijAplikacje(ActionEvent event
    ) {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void setCaspian(ActionEvent event
    ) {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    @FXML
    private void setModena(ActionEvent event
    ) {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    @FXML
    private void aboutApplication(ActionEvent event
    ) {
        DialogsUtils.dialogAboutAplication();
    }

    @FXML
    private void wyloguj(ActionEvent event) throws Exception {
        log.setNextScene(0);
    }

    @FXML
    private void zatwierdzanie(ActionEvent event
    ) {
        nr_identyfikacjiTextField.clear();
        karaPole.setText("");
        ilosc_wypPole.setText("");
        imiePole.setText("");
        nazwiskoPole.setText("");
        emialPole.setText("");
        statusCombo.getItems().clear();
        DialogsUtils.showAlert(Alert.AlertType.CONFIRMATION, "Zmieniono status", "Zmieniono status użytkownika");
    }

    @FXML
    private void wczytajBibliotekarzy() {
        try {

            client.openConnect();
            String sql = "SELECT imie_p,nazwisko_p,nr_identyfikacji_p, email_p from pracownicy where profil=2";

            st = client.connection.prepareStatement(sql);
            rs = st.executeQuery();

            bibliotekarz_list.clear();
            while (rs.next()) {
                bibliotekarz_list.add(new Bibliotekarz(rs.getString("imie_p"), rs.getString("nazwisko_p"), rs.getInt("nr_identyfikacji_p"), rs.getString("email_p")));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wczytajBibliotekarzy" + sql);
        }
    }

    @FXML
    private void usunBibliotekarza(ActionEvent event) {
         Bibliotekarz k = tableBibliotekarze.getSelectionModel().getSelectedItem();
        try {
            client.openConnect();
            String sql = "DELETE FROM pracownicy WHERE nr_identyfikacji_p = ?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setInt(1, k.getNr_identyfikacji_p());
            preparedStmt.execute();
            tableBibliotekarze.getItems().remove(k);
            System.out.print("usunieto");
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Usuwanie bibliotekarza", "Bibliotekarz został usunięty!");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
