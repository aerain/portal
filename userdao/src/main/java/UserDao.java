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
            StatementStrategy statementStrategy = new GetStatementStrategy();
            preparedStatement = statementStrategy.makePreparedStatement(id, connection);
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

            StatementStrategy statementStrategy = new AddStatementStrategy();
            preparedStatement = statementStrategy.makePreparedStatement(user, connection);
            preparedStatement.executeUpdate();


            id = getLastInsertId(connection);
        } finally {
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



        return id;
    }

    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateStatementStrategy();
            preparedStatement = statementStrategy.makePreparedStatement(user, connection);
            preparedStatement.executeUpdate();

        } finally {
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

    }

    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
            preparedStatement = statementStrategy.makePreparedStatement(id, connection);
            preparedStatement.executeUpdate();

        } finally {
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
    
}
