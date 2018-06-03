/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Karol
 */
public class BibliotekarzOknoControllerTest {
     ObservableList<Ksiazki> ksiazki_list = FXCollections.observableArrayList();
     ObservableList<Gatunki> gatunki_list = FXCollections.observableArrayList();
    
    public BibliotekarzOknoControllerTest() {
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
     * Test of initialize method, of class BibliotekarzOknoController.
     */
    @Ignore
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        BibliotekarzOknoController instance = new BibliotekarzOknoController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGatunki method, of class BibliotekarzOknoController.
     */
 



    /**
     * Test of getId method, of class BibliotekarzOknoController.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String n = "";
        BibliotekarzOknoController instance = new BibliotekarzOknoController();
        int expResult = -1;
        int result = instance.getId(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of edycja method, of class BibliotekarzOknoController.
     */


  
}
