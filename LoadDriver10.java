import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class LoadDriver10 {
    public static void main(String[] args) {
        // Connection object
        Connection conn = null;
        CallableStatement cstmt = null;

        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter roll number, name:");
        int rollNo = sc.nextInt();
        String name = sc.next();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database credentials)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // Prepare the callable statement for the stored procedure
            cstmt = conn.prepareCall("{call students(?, ?)}");
            cstmt.setInt(1, rollNo);
            cstmt.setString(2, name);

            // Execute the callable statement
            cstmt.execute();
            System.out.println("Student record successfully inserted.");

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
