package library.system;

import Database.Client;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class BibliotekarzOknoController extends User implements Initializable {

    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
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
    private ComboBox<?> autor_dodajComboBox;
    @FXML
    private ComboBox<?> gatunek_dodajComboBox;
    @FXML
    private ComboBox<?> status_dodajComboBox;
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
    private TextField autorDodawanie;
    @FXML
    private TextField autorPseudonim;
    @FXML
    private DatePicker data_urDodawanie;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableWyszukajKsiazki.setItems(null);
        tableWyszukajKsiazki.setItems(ksiazki_list);
        columnTytulWyszukaj.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnISBNWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnImieWyszukaj.setCellValueFactory(new PropertyValueFactory<>("imie_a"));
        columnNazwiskoWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwisko_a"));
        columnData_wydWyszukaj.setCellValueFactory(new PropertyValueFactory<>("data_wyd"));
        columnGatunekWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_g"));
        columnStatusWyszukaj.setCellValueFactory(new PropertyValueFactory<>("nazwa_s"));
        columnIloscWyszukaj.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        usunBtn.disableProperty().bind(tableWyszukajKsiazki.getSelectionModel().selectedItemProperty().isNull());

        edycjaKsiazki();
    }
//do edycji

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

    @FXML
    public void wczytajKsiazki(ActionEvent event) throws Exception {
        wczytajKsiazki(ksiazki_list);

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
public int getId(String n){
       int id =-1;
        try {
         
            PreparedStatement preparedStmt;
            ResultSet rs;
           client.openConnect();
            String sql1 = "Select id_ksiazki From ksiazka Where tytul =?";
            preparedStmt = client.connection.prepareStatement(sql1);
            preparedStmt.setString(1,n);
            
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
                preparedStmt.setInt(3,id );
                 preparedStmt.execute();
                System.out.println("oba"+id);
            } else if (!tytulp.equals("1") && ISBN.equals("1")) {
                String sql = "UPDATE ksiazka SET tytul = ? WHERE id_ksiazki =?";
                preparedStmt = client.connection.prepareStatement(sql);
                preparedStmt.setString(1, tytulp);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                System.out.println("tytul:"+id+" po zmianie: " +tytulp);
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
            ///System.out.print(tytul);
        } catch (SQLException ex) {
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void dodajGatunek(ActionEvent event) {
        try {
            String nazwaGatunku = nazwaDodawanieGatunek.getText().toString();
            String opisGatunku = opisDodawanieGatunek.getText().toString();

            client.openConnect();
            String sql = "insert into gatunki (nazwa_g, opis) values (?, ?)";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setString(1, nazwaGatunku);
            preparedStmt.setString(2, opisGatunku);
            preparedStmt.execute();
            System.out.println("uda");

            client.connection.close();
        } catch (SQLException ex) {
            System.out.println("łydki");
            Logger.getLogger(BibliotekarzOknoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usunKsiazke() {
        Ksiazki k = tableWyszukajKsiazki.getSelectionModel().getSelectedItem();
        try {
            // narazie nic
            // pobieram
            String sql = "DELETE FROM ksiazka WHERE tytul = ?";
            PreparedStatement preparedStmt = client.connection.prepareStatement(sql);
            preparedStmt.setString(1, k.getTytul());
            preparedStmt.execute();
            tableWyszukajKsiazki.getItems().remove(k);
            System.out.print("usunieto");
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

}
