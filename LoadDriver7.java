// jdbc program to delete specific row from a table

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadDriver7 {
    public static void main(String[] args) {
        // Connection and PreparedStatement objects
        Connection conn = null;
        PreparedStatement pstmt = null;

        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter roll number to delete:");
        int rollNo = sc.nextInt();

        // SQL query using a placeholder
        String sql = "DELETE FROM Students WHERE rollno = ?";

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database credentials)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // Prepare the statement with the parameter
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rollNo);

            // Execute the delete operation
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Row deleted successfully!");
            } else {
                System.out.println("No row found with the specified roll number.");
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions (in case the JDBC driver isn't found)
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close PreparedStatement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("SQLException during statement close: " + e.getMessage());
                }
            }

            // Close Connection
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.err.println("SQLException during connection close: " + e.getMessage());
                }
            }

            // Close the scanner
            sc.close();
        }
    }
}
