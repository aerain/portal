import java.sql.*;

public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://172.18.102.128/jeju?serverTimezone=UTC", "portal", "portaljejunu");

        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");

        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword((resultSet.getString("password")));

        return user;
    }
}
