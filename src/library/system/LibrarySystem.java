
package library.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibrarySystem extends Application {
    
    public static Stage L_window, L_window2;
    public int nextScene = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogowanieOkno.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("BibliotekarzOkno.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("CzytelnikOkno.fxml"));
        
          
        L_window = stage;

        if (nextScene == 1) 
        {
            Scene scene = new Scene(root2);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("KSIAŻKI");
            stage.show();
        } 
        else if (nextScene == 2) 
        {
            Scene scene = new Scene(root3);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("KSIAŻKI");
            stage.show();

        }
        else {                                  //Jeżeli nie to włącza pierwszą scene czyli okienko logowania
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("KSIAŻKI");
            stage.show();
        }
    }

    public void setNextScene(int a) throws Exception {   //setter dla zmiennej nextScene oraz aktualizacja  sceny
        this.nextScene = a;

        start(L_window);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
