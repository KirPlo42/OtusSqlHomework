package tables;

import java.sql.SQLException;

public class Curator extends AbsTable{
    public Curator() {
        super("curator");
    }

    @Override
    public void create() throws SQLException {
        iDbExecutor.execute("Create table curator (Id varchar(10), name varchar(50))", false);
    }

    @Override
    public void insert() throws SQLException {
        iDbExecutor.execute("insert into curator values ('1', 'LeBron'), ('2', 'Jimmy'), ('3', 'Coby')",false);
    }
}
