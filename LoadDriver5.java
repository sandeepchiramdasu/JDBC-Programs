// jdbc program to insert data into database through user input

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadDriver5 {
    public static void main(String[] args) {
        // Connection and PreparedStatement objects
        Connection conn = null;
        PreparedStatement pst = null;
        
        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rollno and name:");
        int rollNo = sc.nextInt();
        String name = sc.next();

        // SQL query using placeholders
        String sql = "INSERT INTO Students (rollno, name) VALUES (?, ?)";

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database name, username, and password)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // Prepare the statement with parameterized values
            pst = conn.prepareStatement(sql);
            pst.setInt(1, rollNo);   // Set roll number
            pst.setString(2, name);   // Set name

            // Execute the insert operation
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Row successfully inserted!");
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions (in case the JDBC driver isn't found)
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close PreparedStatement
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("SQLException during statement close: " + e.getMessage());
                }
            }

            // Close Connection
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.out.println("SQLException during connection close: " + e.getMessage());
                }
            }

            // Close the scanner
            sc.close();
        }
    }
}
