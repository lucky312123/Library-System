
package library.system.dialogs;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogsUtils {
    
    //Okienka informacyjne 
    
    public static void dialogAboutAplication() {
        Alert informacja = new Alert(Alert.AlertType.INFORMATION);
        informacja.setTitle("O aplikacji");
        informacja.setContentText("Projekt");
        informacja.setHeaderText("System biblioteczny wersja 1.0");
        informacja.showAndWait();

    }
    
     public static Optional<ButtonType> confirmationDialog() {
        Alert potwierdz = new Alert(Alert.AlertType.CONFIRMATION);
        potwierdz.setTitle("Wyjście");
        potwierdz.setHeaderText("Czy na pewno chcesz wyjść??");
        Optional<ButtonType> result = potwierdz.showAndWait();
        return result;

    }
    
}
