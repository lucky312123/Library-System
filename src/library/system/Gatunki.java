package library.system;

import javafx.beans.property.SimpleStringProperty;

public class Gatunki {

    private final SimpleStringProperty nazwa_g;
    private final SimpleStringProperty opis;

    public Gatunki(String nazwa_g, String opis) {
        this.nazwa_g = new SimpleStringProperty(nazwa_g);
        this.opis = new SimpleStringProperty(opis);

    }

    public String getNazwa_g() {
        return nazwa_g.get();
    }

    public void setNazwa_g(String value) {
        nazwa_g.set(value);
    }

    public String getOpis() {
        return opis.get();
    }

    public void setOpis(String value) {
        opis.set(value);
    }

}
