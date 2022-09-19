package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDbExecutor {
    public ResultSet execute(String sqlRequest, boolean isResul) throws SQLException;

    void close() throws SQLException;
}
