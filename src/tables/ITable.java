package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITable {
    void create() throws SQLException;
    void delete() throws SQLException;
    boolean isTableExist() throws SQLException;
    void insertAmount(int n) throws SQLException;
    void insert() throws SQLException;
    ResultSet select(String what) throws SQLException;
    ResultSet selectWithConditions(String what, String where) throws SQLException;
    int getNumberOfColumns(ResultSet obj) throws SQLException;
    void set(String whatOnWhat, String where) throws SQLException;
}
