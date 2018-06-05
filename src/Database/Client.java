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

    /**
     *
     */
    public Connection connection;
    private Statement statement;

    /**
     *
     * @return
     */
    /**
     * metoda łaczonca aplikajcę z bazą danych mysql
     */
    public Connection openConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/librarydatabase?useUnicode=true&characterEncoding=utf8&" + "librarydatabase", "root", "");
            System.out.println("udaao sie");
        } catch (ClassNotFoundException | SQLException sqle) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return connection;
    }
}
