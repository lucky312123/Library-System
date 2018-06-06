package library.system;

import javafx.beans.property.SimpleStringProperty;

public class Bibliotekarz {

    private final SimpleStringProperty imie_p;
    private final SimpleStringProperty nazwisko_p;
    private int nr_identyfikacji_p;
    private final SimpleStringProperty email_p;

    public Bibliotekarz(String imie_p, String nazwisko_p, int nr_identyfikacji_p, String email_p) {
        this.imie_p = new SimpleStringProperty(imie_p);
        this.nazwisko_p = new SimpleStringProperty(nazwisko_p);
        this.nr_identyfikacji_p = nr_identyfikacji_p;
        this.email_p = new SimpleStringProperty(email_p);
    }

    public String getImie_p() {
        return imie_p.get();
    }

    public void setImie_p(String value) {
        imie_p.set(value);
    }

    public String getNazwisko_p() {
        return nazwisko_p.get();
    }

    public void setNazwisko_p(String value) {
        nazwisko_p.set(value);
    }

    public int getNr_identyfikacji_p() {
        return nr_identyfikacji_p;
    }

    public void setNr_identyfikacji(int nr_identyfikacji_p) {
        this.nr_identyfikacji_p = nr_identyfikacji_p;
    }

    public String getEmail_p() {
        return email_p.get();
    }

    public void setEmail_p(String value) {
        email_p.set(value);
    }

}
