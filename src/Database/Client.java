/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Karol
 */
public class Client {
    private Connection connection;
    private Statement statement;
    
    public Connection openConnect(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection =(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"lib","root","");
            System.out.println("udaao sie");
        } catch (ClassNotFoundException  | SQLException sqle) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, sqle);
        }
        
        
    return connection;
}
}
