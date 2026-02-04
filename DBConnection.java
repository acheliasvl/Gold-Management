import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=enterDatabaseName;user=enterUserName;password=enterPassword;encrypt=true;trustServerCertificate=true;";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}