import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    public PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException;
}

