package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group extends AbsTable{

    public Group() {
        super("groups");
    }

    @Override
    public void create() throws SQLException {
        iDbExecutor.execute("Create table groups (Id varchar(10), name varchar(50), id_curator varchar(10))", false);

    }

    @Override
    public void insert() throws SQLException {
        iDbExecutor.execute("insert into groups values ('1', 'Lakers', '1'), ('2', 'Miami Heat', '2'), ('3', 'Chicago Bulls', '3')",false);
    }

    public ResultSet getGroupAndCurator() throws SQLException {
        ResultSet result = iDbExecutor.execute("select g.name, c.name from groups g \n" +
                "join curator c on g.id_curator = c.id ", true);
        return result;
    }
}
