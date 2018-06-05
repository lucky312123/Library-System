package library.system;

import Database.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class User {

    ResultSet rs;
    PreparedStatement st;
    //ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();

    Client client = new Client();

    public void wczytajKsiazki(ObservableList<Ksiazki> ksiazki_list) throws Exception {

        try {

            client.openConnect();
            String sql = "SELECT k.tytul,k.ISBN,a.imie_a,a.nazwisko_a,k.data_wyd,g.nazwa_g,s.nazwa_s,count(k.tytul) as ilosc from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and k.status=s.status group by k.tytul";

            st = client.connection.prepareStatement(sql);
            rs = st.executeQuery();

            ksiazki_list.clear();
            while (rs.next()) {
                ksiazki_list.add(new Ksiazki(rs.getString("tytul"), rs.getString("ISBN"), rs.getString("imie_a"), rs.getString("nazwisko_a"), rs.getString("data_wyd"), rs.getString("nazwa_g"), rs.getString("nazwa_s"), rs.getString("ilosc")));
                //System.out.println(rs.getString("tytul") + " " + rs.getString("ISBN")+ " " + rs.getString("nazwisko_a")+ " " + rs.getString("data_wyd")+ " " + rs.getString("nazwa_g")+ " " + rs.getString("nazwa_s"));

            }

            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wczytajKsiazki" + sql);
        }
    }

     public void wczytajGatunki(ObservableList<Gatunki> gatunki_list) throws Exception {

       try {

            client.openConnect();
            String sql = "SELECT nazwa_g,opis from gatunki";

            st = client.connection.prepareStatement(sql);
            rs = st.executeQuery();

            gatunki_list.clear();
            while (rs.next()) {
                gatunki_list.add(new Gatunki(rs.getString("nazwa_g"), rs.getString("opis")));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wczytajGatunki" + sql);
        }
    }
     
     public void wczytajAutora(ObservableList<Autorzy> autorzy_list) throws Exception {

        try {

            client.openConnect();
            String sql = "SELECT imie_a,nazwisko_a,pseudonim, data_ur from autorzy";

            st = client.connection.prepareStatement(sql);
            rs = st.executeQuery();

            autorzy_list.clear();
            while (rs.next()) {
                autorzy_list.add(new Autorzy(rs.getString("imie_a"), rs.getString("nazwisko_a"), rs.getString("pseudonim"), rs.getString("data_ur")));
            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wczytajAutora" + sql);
        }
    }
    
    public void wyszukaj(ObservableList<Ksiazki> ksiazki_list, String tytul, String imie_a, String nazwisko_a, String gatunek) throws Exception {
        try {

            client.openConnect();

            String sql2 = "SELECT k.tytul,k.ISBN,a.imie_a,a.nazwisko_a,k.data_wyd,g.nazwa_g,s.nazwa_s,count(k.tytul) as ilosc from ksiazka k, gatunki g, autorzy a, autorzy_ksiazki ak, statusy s where k.id_gatunku=g.id_gatunku and k.id_ksiazki=ak.id_aut_ks and a.id_autora=ak.id_autora and k.status=s.status and k.status='1' and ( k.tytul=? or a.imie_a=? or a.nazwisko_a=? or g.nazwa_g like '%"+gatunek+"%') group by k.tytul";

            st = client.connection.prepareStatement(sql2);

            st.setString(1, tytul);
            st.setString(2, imie_a);
            st.setString(3, nazwisko_a);
            //st.setString(4, gatunek);

            rs = st.executeQuery();

            ksiazki_list.clear();
            while (rs.next()) {
                ksiazki_list.add(new Ksiazki(rs.getString("tytul"), rs.getString("ISBN"), rs.getString("imie_a"), rs.getString("nazwisko_a"), rs.getString("data_wyd"), rs.getString("nazwa_g"), rs.getString("nazwa_s"), rs.getString("ilosc")));

            }
            rs.close();
            client.connection.close();

        } catch (SQLException sql) {
            System.out.println("Problem z wyszukajKsiazki" + sql);
        }
    }
}
