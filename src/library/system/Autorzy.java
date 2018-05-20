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
public class Autorzy {
        private final SimpleStringProperty imie_a;
        private final SimpleStringProperty nazwisko_a;
        private final SimpleStringProperty pseudonim;
        private final SimpleStringProperty data_ur;

        public Autorzy(String imie_a, String nazwisko_a, String pseudonim, String data_ur) {
            this.imie_a = new SimpleStringProperty(imie_a);
            this.nazwisko_a= new SimpleStringProperty(nazwisko_a);
            this.pseudonim = new SimpleStringProperty(pseudonim);
            this.data_ur = new SimpleStringProperty(data_ur);
    }
        public String getImieA() {
        return imie_a.get();
    }

    public void setImieA(String value) {
        imie_a.set(value);
    }
        public String getNazwiskoA() {
        return nazwisko_a.get();
    }

    public void setNazwiskoA(String value) {
        nazwisko_a.set(value);
    }
     public String getPseudonim() {
        return pseudonim.get();
    }

    public void setPseudonim(String value) {
        pseudonim.set(value);
    }
     public String getData_ur() {
        return data_ur.get();
    }

    public void setData_ur(String value) {
        data_ur.set(value);
    }

}
