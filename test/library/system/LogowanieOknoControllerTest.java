
package library.system;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class LogowanieOknoControllerTest {
    
    public LogowanieOknoControllerTest() {
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
     * Test of initialize method, of class LogowanieOknoController.
     */
    @Ignore
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        LogowanieOknoController instance = new LogowanieOknoController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    @Test
    public void testLogowanie(){
       LogowanieOknoController iController = new LogowanieOknoController();
        ActionEvent event = null;
        int n=0;
        try {
            n = iController.zaloguj(event);
        } catch (Exception ex) {
            Logger.getLogger(LogowanieOknoControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 0;
         assertEquals(expResult, n);
    }
}
