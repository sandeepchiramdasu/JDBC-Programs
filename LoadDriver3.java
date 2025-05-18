// jdbc program to insert data into database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadDriver3 {
    public static void main(String[] args) {
        // Connection and PreparedStatement objects
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database name, username, and password)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // SQL query using placeholders
            String sql = "INSERT INTO students (rollno, name) VALUES (?, ?)";

            // Prepare the statement with parameterized values
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 101);   // Set roll number
            pst.setString(2, "ram");  // Set name

            // Execute the insert operation
            int rowsAffected = pst.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) successfully!");

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
        }
    }
}
