import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection{

    public static Connection getConnection() throws ClassNotFoundException {
        final String DB_URL = "jdbc:mysql://localhost/loginform?serverTimezone=UTC";
        final String USER = "root";
        final String PASS = "";

        Connection connection = null;

        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}
