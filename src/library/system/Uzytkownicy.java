package library.system;

import javafx.beans.property.SimpleStringProperty;

public class Uzytkownicy {

    private final SimpleStringProperty imie_k;
    private final SimpleStringProperty nazwisko_k;
    private final SimpleStringProperty nr_identyfikacji_k;
    private final SimpleStringProperty email_k;

    public Uzytkownicy(String imie_k, String nazwisko_k, String nr_identyfikacji_k, String email_k) {
        this.imie_k = new SimpleStringProperty(imie_k);
        this.nazwisko_k = new SimpleStringProperty(nazwisko_k);
        this.nr_identyfikacji_k = new SimpleStringProperty(nr_identyfikacji_k);
        this.email_k = new SimpleStringProperty(email_k);
    }

    public String getImie_k() {
        return imie_k.get();
    }

    public void setImie_k(String value) {
        imie_k.set(value);
    }

    public String getNazwisko_k() {
        return nazwisko_k.get();
    }

    public void setNazwisko_k(String value) {
        nazwisko_k.set(value);
    }

    public String getNr_identyfikacji_k() {
        return nr_identyfikacji_k.get();
    }

    public void setNr_identyfikacji_k(String value) {
        nr_identyfikacji_k.set(value);
    }

    public String getEmail_k() {
        return email_k.get();
    }

    public void setEmail_k(String value) {
        email_k.set(value);
    }

}
