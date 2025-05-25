import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class LoadDriver11 {
    public static void main(String[] args) {
        // Connection object
        Connection conn = null;
        CallableStatement cstmt = null;

        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter roll number:");
        int rollNo = sc.nextInt();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // Prepare the callable statement for the stored procedure
            cstmt = conn.prepareCall("{call get_info(?, ?)}");
            cstmt.setInt(1, rollNo);
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute the callable statement
            cstmt.execute();
            
            // Retrieve the student name
            String studentName = cstmt.getString(2);
            System.out.println("Student name is: " + studentName);

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("SQLException during resource close: " + e.getMessage());
            }
            // Close the scanner
            sc.close();
        }
    }
}
