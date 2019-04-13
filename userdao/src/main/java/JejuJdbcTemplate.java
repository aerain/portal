import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JejuJdbcTemplate extends JdbcTemplate {

    public JejuJdbcTemplate(DataSource dataSource ) {
        super(dataSource);
    }

    Long insert(String sql, Object[] params) {
        // 키홀더는 뭘까?
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // PreparedStatementCreator 인터페이스 구현
        update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}