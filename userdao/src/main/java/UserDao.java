import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
//        StatementStrategy statementStrategy = new GetStatementStrategy(id);

        // class 로 나누기보다 구현체를 실제 실행하는것도 좋은 방법. 이거는 취향차이!
        // 람다함수를 통해서 가능할까?

        // 이거는 너무 길다!
//        StatementStrategy statementStrategy = new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement preparedStatement =
//                        connection.prepareStatement("select * from userinfo where id = ?");
//
//                preparedStatement.setLong(1, id);
//                return preparedStatement;
//            }
//        };

        // 우선순위 함수, 람다함수를 이용해서 작성 가능하다!
        // JDK 1.8 이상 가능

        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[] { id };
        return jdbcContext.get(sql, params);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo(name, password) values(?, ?)";
        Object[] params = new Object[] { user.getName(), user.getPassword() };
        return jdbcContext.add(sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{ user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{ id };
        jdbcContext.update(sql, params);
    }
}
