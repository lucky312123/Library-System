
package library.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class CzytelnikOknoControllerTest {
    
    ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
    
    public CzytelnikOknoControllerTest() {
    }
   
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of wczytajKsiazki method, of class CzytelnikOknoController.
     */
    @Test
    public void testWczytajKsiazki() throws Exception {
        System.out.println("wczytajKsiazki");
        ActionEvent event = null;
        CzytelnikOknoController instance = new CzytelnikOknoController();
        instance.wczytajKsiazki(ksiazki_list);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
    }
    

    /**
     * Test of initialize method, of class CzytelnikOknoController.
     */
    @Ignore
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        CzytelnikOknoController instance = new CzytelnikOknoController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
