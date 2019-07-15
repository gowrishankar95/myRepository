import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class LoadDriver {
    static Connection conn = null;
    public static Connection connect(){
        try {
            String url       = "jdbc:mysql://localhost:3306/db";
            String user      = "root";
            String password  = "beyondm";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected");
            return conn;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
