import tables.AbsTable;
import tables.Curator;
import tables.Group;
import tables.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main (String... arg) throws SQLException {
        AbsTable student = new Student();
        AbsTable group = new Group();
        AbsTable curator = new Curator();

        try {
            if (student.isTableExist()){
                student.delete();
            }
            student.create(); //1. create table student
            student.insertAmount(5); //4. insert some students (students = n x 2)

            if (group.isTableExist()){
                group.delete();
            }
            group.create(); //2. create table groups
            group.insert(); //4. insert few groups

            if (curator.isTableExist()){
                curator.delete();
            }
            curator.create(); //3. create table curator
            curator.insert(); //4. insert few curators
            ResultSet studentsInfo = ((Student) student).getStudentsInfo();
            System.out.println("Print info about students");
            System.out.println("ID, Name, LastName, Sex, Group, Curator");
            student.print(studentsInfo);//5. print info about students
            System.out.println("--------------------------------------------------------");
            ResultSet count = student.select("count(*)");
            System.out.println("Amount students:");
            student.print(count);//6. Print amount students
            System.out.println("--------------------------------------------------------");
            ResultSet girls = student.selectWithConditions("name, lastname", "sex = 'female'");
            System.out.println("Girls from students:");
            student.print(girls);//7. Print girls
            group.set("id_curator = '3'", "id_curator = '1'");//8. update group
            System.out.println("--------------------------------------------------------");
            ResultSet groupInfo = ((Group) group).getGroupAndCurator();
            System.out.println("Group name + Curator");
            group.print(groupInfo);//9. Print groups with curators
            System.out.println("--------------------------------------------------------");
            ResultSet studentsInSelectedGroup = ((Student) student).getStudentsFromSelectedGroup("Lakers");
            System.out.println("Student in some group");
            student.print(studentsInSelectedGroup);//10. Students from selected

        }
        finally {
            student.getiDbExecutor().close();
        }
    }
}
