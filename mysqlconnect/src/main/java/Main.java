import java.sql.*;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        SqlOperatons sqlOperatons=new SqlOperatons();
        //sqlOperatons.insertIntoStudentTable("gowrishankar", 23, "jaffna", "JHC");
        sqlOperatons.viewStudentDetails("gowrishankar");
        sqlOperatons.deleteStudent(3);
        sqlOperatons.updateStudentDetails(4,"g", 23, "jaffna", "JHC");
    }
}