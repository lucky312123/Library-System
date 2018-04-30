package library.system;

import javafx.beans.property.SimpleStringProperty;

public class MojeKsiazki {

    private final SimpleStringProperty tytul;
    private final SimpleStringProperty autor;
    private final SimpleStringProperty ISBN;
    private final SimpleStringProperty nazwa_g;
    private final SimpleStringProperty data_wyp;
    private final SimpleStringProperty data_zwrotu;

    public MojeKsiazki(String tytul, String autor, String ISBN, String nazwa_g, String data_wyp, String data_zwrotu) {
        this.tytul = new SimpleStringProperty(tytul);
        this.autor = new SimpleStringProperty(autor);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.nazwa_g = new SimpleStringProperty(nazwa_g);
        this.data_wyp = new SimpleStringProperty(data_wyp);
        this.data_zwrotu = new SimpleStringProperty(data_zwrotu);
    }
    
    public String getTytul() {
        return tytul.get();
    }

    public void setTytul(String value) {
        tytul.set(value);
    }
    
    public String getAutor() {
        return autor.get();
    }

    public void setautor(String value) {
        autor.set(value);
    }
    
    public String getISBN() {
        return ISBN.get();
    }

    public void setISNB(String value) {
        ISBN.set(value);
    }
    
     public String getNazwa_g() {
        return nazwa_g.get();
    }

    public void setNazwa_g(String value) {
        nazwa_g.set(value);
    }
    
    public String getData_wyp() {
        return data_wyp.get();
    }

    public void setData_wyp(String value) {
        data_wyp.set(value);
    }
    
    public String getData_zwrotu() {
        return data_zwrotu.get();
    }

    public void setData_zwrotu(String value) {
        data_zwrotu.set(value);
    }

}
