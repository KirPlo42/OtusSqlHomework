package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Student extends AbsTable{

    public Student(){
        super("students");
    }
    public List<String> boysNames = Arrays.asList("Igor", "Alex", "Max", "Nikita", "Eduard", "Oleg", "Slava");
    public List<String> girlsNames = Arrays.asList("Olga", "Sveta", "Zina", "Karina", "Oksana", "Irina", "Valeria");
    public List<String> lastNames = Arrays.asList("Templer", "Shyrup", "Knyaz", "Tsoy", "Bolt", "Cat", "Omon");
    @Override
    public void insertAmount(int n) throws SQLException {
        for (int i = 1; i < n+1; i++){
            iDbExecutor.execute(String.format("insert into students values (%d, '%s', '%s', 'man', %d)", i, getRandomElement(boysNames), getRandomElement(lastNames),(int)(Math.random()*3+1)),false);
            iDbExecutor.execute(String.format("insert into students values (%d, '%s', '%s', 'female', %d)", i+n, getRandomElement(girlsNames), getRandomElement(lastNames), (int)(Math.random()*3+1)),false);
        }
    }

    @Override
    public void create() throws SQLException {
        iDbExecutor.execute("Create table students (Id varchar(10), name varchar(10), lastName varchar(10), sex varchar(10), id_group varchar(10))", false);
    }

    public ResultSet getStudentsInfo() throws SQLException {
        ResultSet result = iDbExecutor.execute("SELECT s.id \n" +
                ",s.\"name\" \n" +
                ",s.lastname \n" +
                ",s.sex \n" +
                ",g.\"name\" \n" +
                ",c.\"name\" \n" +
                "FROM students s join \"groups\" g on s.id_group = g.id \n" +
                "join curator c on g.id_curator = c.id", true);
        return result;
    }

    public ResultSet getStudentsFromSelectedGroup(String groupName) throws SQLException {
        ResultSet result = iDbExecutor.execute(String.format("select s.name, s.lastname from students s \n" +
                "where s.id_group = (select g.id from groups g where g.name = '%s')", groupName), true);
        return result;
    }
}
