import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnectionMaker implements ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://172.18.102.128/jeju?serverTimezone=UTC", "portal", "portaljejunu");
//        return DriverManager.getConnection("jdbc:mysql://192.168.0.54/jeju?serverTimezone=UTC", "jeju", "jejupw");
    }
}
