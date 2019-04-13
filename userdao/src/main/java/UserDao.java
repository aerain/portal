import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final JejuJdbcTemplate jdbcTemplate;

    public UserDao(JejuJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

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

        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
               User user = new User();
               user.setId(rs.getLong("id"));
               user.setName(rs.getString("name"));
               user.setPassword(rs.getString("password"));
               return user;
            });
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo(name, password) values(?, ?)";
        Object[] params = new Object[] { user.getName(), user.getPassword() };
//        return jdbcTemplate.add(sql, params);
        return jdbcTemplate.insert(sql, params);

    }

    private Long insert(String sql, Object[] params) {
        // 키홀더는 뭘까?
        // PreparedStatementCreator 인터페이스 구현
        return jdbcTemplate.insert(sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{ user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{ id };
        jdbcTemplate.update(sql, params);
    }
}
