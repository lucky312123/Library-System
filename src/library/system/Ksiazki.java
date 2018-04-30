package library.system;

import javafx.beans.property.SimpleStringProperty;

public class Ksiazki {

    private final SimpleStringProperty tytul;
    private final SimpleStringProperty ISBN;
    private final SimpleStringProperty imie_a;
    private final SimpleStringProperty nazwisko_a;
    private final SimpleStringProperty data_wyd;
    private final SimpleStringProperty nazwa_g;
    private final SimpleStringProperty nazwa_s;
    private final SimpleStringProperty ilosc;

    public Ksiazki(String tytul, String ISBN, String imie_a, String nazwisko_a, String data_wyd, String nazwa_g, String nazwa_s,String ilosc) {
        this.tytul = new SimpleStringProperty(tytul);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.imie_a = new SimpleStringProperty(imie_a);
        this.nazwisko_a = new SimpleStringProperty(nazwisko_a);
        this.data_wyd = new SimpleStringProperty(data_wyd);
        this.nazwa_g = new SimpleStringProperty(nazwa_g);
        this.nazwa_s = new SimpleStringProperty(nazwa_s);
        this.ilosc = new SimpleStringProperty(ilosc);

    }

    public String getTytul() {
        return tytul.get();
    }

    public void setTytul(String value) {
        tytul.set(value);
    }

    public String getISBN() {
        return ISBN.get();
    }

    public void setISNB(String value) {
        ISBN.set(value);
    }

    public String getData_wyd() {
        return data_wyd.get();
    }

    public void setData_wyd(String value) {
        data_wyd.set(value);
    }

    public String getImie_a() {
        return imie_a.get();
    }

    public void setImie_a(String value) {
        imie_a.set(value);
    }

    public String getNazwisko_a() {
        return nazwisko_a.get();
    }

    public void setNazwisko_a(String value) {
        nazwisko_a.set(value);
    }

    public String getNazwa_g() {
        return nazwa_g.get();
    }

    public void setNazwa_g(String value) {
        nazwa_g.set(value);
    }

    public String getNazwa_s() {
        return nazwa_s.get();
    }

    public void setNazwa_s(String value) {
        nazwa_s.set(value);
    }
    
     public String getIlosc() {
        return ilosc.get();
    }

    public void setIlosc(String value) {
        ilosc.set(value);
    }

}
