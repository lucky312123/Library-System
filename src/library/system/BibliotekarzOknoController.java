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
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class BibliotekarzOknoController extends User implements Initializable {

    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
    ObservableList<Gatunki> gatunki_list = FXCollections.observableArrayList();
    ObservableList<Autorzy> autorzy_list = FXCollections.observableArrayList();
    ObservableList<MojeKsiazki> mojeksiazki_list = FXCollections.observableArrayList();
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
    private ComboBox<String> status_dodajComboBox;
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
    String pZniszczeniaNowe;
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
    @FXML
    private Label statusPole;
    @FXML
    private Button wypozyczKsiazkeBTN;
    String nr_identyfikacji;
    @FXML
    private Button btnWyloguj;
    @FXML
    private TableColumn<?, ?> columnImieWyszukaj1;
    @FXML
    private TableColumn<?, ?> columnNazwiskoWyszukaj1;
    @FXML
    private TableColumn<?, ?> columnPseudonimWyszukaj1;
    @FXML
    private TableColumn<?, ?> columnData_uWyszukaj1;
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
    private TableColumn<?, ?> columnNazwaDodawanieGatunku;
    @FXML
    private TableColumn<?, ?> columnOpisDodawanieGatunku;

    @FXML
    private Button usun_aBtn;
    @FXML
    private Button karaZaplaconaBTN;

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

        usunBtn.disableProperty().bind(tableWyszukajKsiazki.getSelectionModel().selectedItemProperty().isNull());
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
        edycjaKsiazki();
        getGatunki();
        edycjaZniszczenia();
    }

//do edycjipubli 
    public void getGatunki() {
        client.openConnect();
        String sql = "SELECT g.nazwa_g FROM gatunki g";
        String sql2 = "SELECT s.nazwa_s FROM statusy s";
        String sql3 = "SELECT nazwisko_a FROM autorzy";
        ResultSet rs;
        PreparedStatement preparedStmt, preparedStmt1, preparedStmt2;
        gatunek_dodajComboBox.getItems().clear();
        gatunki.clear();
        try {
            preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt1 = client.connection.prepareStatement(sql2);
            preparedStmt2 = client.connection.prepareStatement(sql3);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                gatunki.add(rs.getString("nazwa_g"));

            }
            rs = preparedStmt2.executeQuery();

            while (rs.next()) {
                autor_dodajComboBox.getItems().addAll(rs.getString("nazwisko_a"));
            }
            rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                status_dodajComboBox.getItems().addAll(rs.getString("nazwa_s"));
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

    @FXML
    public void wczytajKsiazki(ActionEvent event) throws Exception {
        wczytajKsiazki(ksiazki_list);

    }

    @FXML
    private void wczytajGatunki() throws Exception {
        wczytajGatunki(gatunki_list);
    }

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
    private void wyczyscWypozyczeniaZwroty(ActionEvent event) {
        nr_identyfikacjiTextField.clear();
        karaPole.setText("");
        ilosc_wypPole.setText("");
        imiePole.setText("");
        nazwiskoPole.setText("");
        emialPole.setText("");
        statusPole.setText("");
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

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @FXML
    private void dodajKsiazke() {

        try {
            int i = 1;
            client.openConnect();
            String tytul = tytulDodawanieKsiazka.getText().trim();
            String ISBN = isbnDodawanieKsiazka.getText().trim();
            int procentZ = Integer.parseInt(procent_zniszczeniaDodawanieKsiazka.getText().trim());
            LocalDate localDate = data_wydDodawanieKsiazka.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            i = Integer.parseInt(l_egzemplarzy.getText().trim());
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
            tytulDodawanieKsiazka.clear();
            isbnDodawanieKsiazka.clear();
            data_wydDodawanieKsiazka.getEditor().clear();
            procent_zniszczeniaDodawanieKsiazka.clear();
            l_egzemplarzy.clear();
            autor_dodajComboBox.getSelectionModel().clearSelection();
            gatunek_dodajComboBox.getSelectionModel().clearSelection();
            status_dodajComboBox.getSelectionModel().clearSelection();
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void wyszukajUzytkownika() {
        try {
            client.openConnect();
            nr_identyfikacji = nr_identyfikacjiTextField.getText().trim();
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
            String sql = "select k.id_ksiazki,s.nazwa_s from ksiazka k,statusy s where k.status=s.status and k.tytul=? and k.status='2'";
            st = client.connection.prepareStatement(sql);
            st.setString(1, k.getTytul());
            rs = st.executeQuery();

            if (rs.next()) {
                idKsiazki = rs.getInt("id_ksiazki");
                status = rs.getString("nazwa_s");
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
                String sql9 = "UPDATE ksiazka SET aktPzniszczenia = ? WHERE id_ksiazki =?";
                st = client.connection.prepareStatement(sql9);
                st.setString(1, pZniszczeniaNowe);
                st.setInt(2, idKsiazki);
                st.executeUpdate();
                System.out.println("Update rokordu ! ID= " + idKsiazki);

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
            client.connection.close();
            DialogsUtils.showAlert(Alert.AlertType.INFORMATION, "Usuwanie autora", "Autor został usunięty");
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    @FXML
    private void wyloguj(ActionEvent event) throws Exception {
        log.setNextScene(0);
    }

}
