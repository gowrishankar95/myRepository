import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLOperatons {
    public boolean insertIntoStudentTable(String name, int age, String address, String schoolName) {
        if (name.isEmpty()) {
            System.out.println("name is epmty");
        } else if (name.length() > 50) {
            System.out.println("name cannot exceed 50 characters");

        } else if (age < 0) {
            System.out.println("age cannot be negative");
        } else if (address.isEmpty()) {
            System.out.println("address is epmty");
        } else if (address.length() > 500) {
            System.out.println("address cannot exceed 500 characters");

        }
        if (schoolName.isEmpty()) {
            System.out.println("school name is epmty");
        } else if (schoolName.length() > 150) {
            System.out.println("school name cannot exceed 150 characters");

        } else {
            String sql = "INSERT INTO student (name, age, address, schoolName) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = null;
            try (Connection connection = LoadDriver.connect()) {
                statement = connection.prepareStatement(sql);
                statement.setString(1, name.trim());
                statement.setInt(2, age);
                statement.setString(3, address.trim());
                statement.setString(4, schoolName.trim());
                int rowsInserted = statement.executeUpdate();
                statement.close();
                if (rowsInserted == 1) {
                    System.out.println("successfully inserted data student table");
                }
                return rowsInserted == 1;

            } catch (SQLException e) {
                System.out.println("connection error");
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void viewStudentDetails(String studentName) {
        if (studentName.isEmpty()) {
            System.out.println("student name cannot be empty");
        } else {
            String sql = "SELECT id, name, age, address " +
                    "FROM student" + " where name = " + "\'" + studentName.trim() + "\'";
            try (Connection connection = LoadDriver.connect()) {
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("no match found");

                } else {
                    rs.beforeFirst();
                }

                // loop through the result set
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getInt("age"));

                }
                stmt.close();
                rs.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public boolean deleteStudent(int id) {
        if (isIdExist(id) == 0) {
            System.out.println("id not found");
        } else if (isIdExist(id) == 1000) {
            System.out.println(" exception while checking id");
        } else {
            String sql = "DELETE  FROM student  WHERE id=?";
            System.out.println(sql);
            PreparedStatement statement = null;
            try (Connection connection = LoadDriver.connect()) {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted == 1) {
                    System.out.println("A user with id " + id + " was deleted successfully!");
                    return true;
                } else {
                    System.out.printf("delete error");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }


        return false;
    }

    public int isIdExist(int studentId) {
        String sql = "SELECT id " +
                "FROM student" + " where id = " + "\'" + studentId + "\'";
        try (Connection connection = LoadDriver.connect()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                stmt.close();
                rs.close();
                return 0;

            } else {
                stmt.close();
                rs.close();
                return 1;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("connection error");
            return 1000;
        }
    }


    public boolean updateStudentDetails(int id, String name, int age, String address, String schoolName) {
        if (isIdExist(id) == 0) {
            System.out.println("id not found");
        } else if (isIdExist(id) == 1000) {
            System.out.println(" exception while checking id");

        } else if (name.isEmpty()) {
            System.out.println("name is epmty");
        } else if (name.length() > 50) {
            System.out.println("name cannot exceed 50 characters");

        } else if (age < 0) {
            System.out.println("age cannot be negative");
        } else if (address.isEmpty()) {
            System.out.println("address is epmty");
        } else if (address.length() > 500) {
            System.out.println("address cannot exceed 500 characters");

        } else {
            String sql = "UPDATE student SET name=?, age=?, address=?,schoolName=? WHERE id=?";


            PreparedStatement statement = null;
            try (Connection connection = LoadDriver.connect()) {
                statement = connection.prepareStatement(sql);
                statement.setString(1, name.trim());
                statement.setInt(2, age);
                statement.setString(3, address.trim());
                statement.setString(4, schoolName.trim());
                statement.setInt(5, id);
                int rowsUpdated = statement.executeUpdate();
                statement.close();
                if (rowsUpdated == 1) {
                    System.out.println("An existing student with id " + id + " was updated successfully!");
                    return true;
                } else {
                    System.out.printf("update error");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;


    }
}
