import java.sql.*;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        SQLOperatons sqlOperatons=new SQLOperatons();
        //sqlOperatons.insertIntoStudentTable("gowrishankar", 23, "jaffna", "JHC");
        sqlOperatons.viewStudentDetails("gowrishankar");
        sqlOperatons.deleteStudent(3);
        sqlOperatons.updateStudentDetails(4,"g", 23, "jaffna", "JHC");


    }

    public Connection connect() {
        return LoadDriver.connect();
    }


}