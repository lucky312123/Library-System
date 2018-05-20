/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Pawel
 */
public class Gatunki {
        private final SimpleStringProperty nazwa_g;
        private final SimpleStringProperty opis;

        public Gatunki(String nazwa_g, String opis) {
            this.nazwa_g = new SimpleStringProperty(nazwa_g);
            this.opis = new SimpleStringProperty(opis);
    }
        public String getNazwa() {
        return nazwa_g.get();
    }

    public void setNazwa(String value) {
        nazwa_g.set(value);
    }
        public String getOpis() {
        return opis.get();
    }

    public void setOpis(String value) {
        opis.set(value);
    }

}
