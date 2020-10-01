// This imports Sql package for java.
// (It means it is bringing all the classes of sql into this existing package from outside.)
// The classes like Connection , statement which we will be using belong to Sql.
import java.sql.*;

public class JDBCHomeworkMySql {
    //JDBC driver name
    String className = "com.mysql.jdbc.Driver";
    String URL, user, password;
    Connection connection;

    public JDBCHomeworkMySql(String URL, String user, String password) {
        // JDBC URL (format is : "jdbc:mysql://localhost:3306")
        this.URL = "jdbc:mysql://" + URL;

        // Database credentials
        this.user = user;
        this.password = password;
    }

    public void getConnection(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName(className);
            System.out.println("connecting");
        }catch (ClassNotFoundException ex) {
            System.out.println("Unable to load the class. Terminating the program");
            System.exit(-1);
        }
        try {
            //STEP 3: Open a connection
            connection = DriverManager.getConnection(URL,user,password);
            System.out.println("connection to database successful.");
        } catch (SQLException ex) {
            System.out.println("Error getting connection: " + ex.getMessage());
            System.exit(-1);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(-1);
        }
        try{
            //STEP 4: Create statement
            Statement stmt = connection.createStatement();
            String query1 = "DROP DATABASE IF EXISTS STUDENTS";
            String query2 = "CREATE DATABASE STUDENTS";
            String query3 = "CREATE TABLE STUDENTS.STUDENT_LIST(STUDENT_ID int, FNAME varchar(30), LNAME varchar(30))";
            String query4 = "INSERT INTO STUDENTS.STUDENT_LIST VALUES (1,'Ruchi','Patel')";
            String query5 = "INSERT INTO STUDENTS.STUDENT_LIST VALUES (1,'jay','Patel')";
            String query6 = "INSERT INTO STUDENTS.STUDENT_LIST VALUES (1,'het','Patel')";
            String query7 = "SELECT * FROM STUDENTS.STUDENT_LIST";
            String query8 = "ALTER TABLE STUDENTS.STUDENT_LIST ADD GENDER varchar(5)";

            //STEP 5: Execute query
            stmt.execute(query1);
            stmt.execute(query2);
            stmt.execute(query3);
            stmt.executeUpdate(query4);
            stmt.executeUpdate(query5);
            stmt.executeUpdate(query6);
            stmt.execute(query8);

            ResultSet rs = stmt.executeQuery(query7);
            while (rs.next()) {
                System.out.println(rs.getString("FNAME"));
            }

            //STEP 6: Close statement
            stmt.close();
            //STEP 5: Close connection
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCHomeworkMySql object = new JDBCHomeworkMySql("localhost:3306", "root", "jaypatel");
        object.getConnection();
    }

}
