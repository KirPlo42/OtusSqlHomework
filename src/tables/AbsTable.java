package tables;

import db.IDbExecutor;
import db.PostgresDbExecutor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsTable implements ITable{

    private String tableName;
    protected IDbExecutor iDbExecutor;

    public IDbExecutor getiDbExecutor(){
        return iDbExecutor;
    }

    public AbsTable(String tableName){
        this.tableName = tableName;
        iDbExecutor = new PostgresDbExecutor();
    }

    @Override
    public void delete() throws SQLException {
        iDbExecutor.execute(String.format("drop table %s", tableName), false);
    }

    public String createPredicate(String[] predicateValues){
        return String.format("where %s", String.join(" and ", predicateValues));
    }

    @Override
    public boolean isTableExist() throws SQLException {
        ResultSet tables = iDbExecutor.execute("SELECT tablename FROM pg_catalog.pg_tables", true);
        boolean isTableCreated = false;
            while (tables.next()){
                if (tables.getString(1).equals(tableName)){
                    isTableCreated = true;
                    break;
                }
            }
            return isTableCreated;

    }
    public String getRandomElement(List<String> array){
        return array.get((int) (Math.random()*array.size()));
    }

    @Override
    public ResultSet select(String what) throws SQLException {
        ResultSet result = iDbExecutor.execute(String.format("Select %s from %s", what, tableName), true);
        return result;
    }

    @Override
    public ResultSet selectWithConditions(String what, String where) throws SQLException {
        ResultSet result = iDbExecutor.execute(String.format("Select %s from %s where %s", what, tableName, where), true);
        return result;
    }

    @Override
    public int getNumberOfColumns(ResultSet obj) throws SQLException {
        ResultSetMetaData meta = obj.getMetaData();
        int count = meta.getColumnCount();
        return count;
    }

    public void print(ResultSet someResult) throws SQLException {
        int n = getNumberOfColumns(someResult);
        while (someResult.next()) {
            List<String> toPrint = new ArrayList<>();
            for (int i = 1; i<n+1; i++){
                toPrint.add(someResult.getString(i));
            }
            System.out.println(toPrint.stream().map(String::valueOf).collect(Collectors.joining("| |", "[", "]")));
        }
    }

    @Override
    public void insert() throws SQLException {
    }
    public void insertAmount(int n) throws SQLException {
    }

    @Override
    public void set(String whatOnWhat, String where) throws SQLException {
        iDbExecutor.execute(String.format("update %s set %s where %s", tableName, whatOnWhat, where), false);
    }
}
