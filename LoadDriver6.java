import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadDriver6 {
    public static void main(String[] args) {
        // Connection and PreparedStatement objects
        Connection conn = null;
        PreparedStatement pst = null;
        
        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter roll number and name:");
        int rollNo = sc.nextInt();
        String name = sc.next();

        // SQL query using placeholders
        String sql = "INSERT INTO Students (rollno, name) VALUES (?, ?)";

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (update with your actual database credentials)
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
            System.err.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions (in case the JDBC driver isn't found)
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close PreparedStatement
            if (pst != null) {
                try {
                    pst.close();
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
