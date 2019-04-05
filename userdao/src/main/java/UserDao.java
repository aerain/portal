import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("select * from userinfo where id = ?");

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword((resultSet.getString("password")));
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }




        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Long id;

        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("insert into userinfo(name, password) values(?, ?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();


            id = getLastInsertId(connection);
        } finally {
            preparedStatement.close();
            connection.close();

        }



        return id;
    }

    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();

        } finally {
            preparedStatement.close();
            connection.close();

        }

    }

    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("delete from userinfo where id = ?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } finally {
            preparedStatement.close();
            connection.close();

        }

    }

    private Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;

        try {
            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);

        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }



        return id;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //        return DriverManager.getConnection("jdbc:mysql://192.168.0.54/jeju?serverTimezone=UTC", "jeju", "jejupw");
        return dataSource.getConnection();
    }



}
