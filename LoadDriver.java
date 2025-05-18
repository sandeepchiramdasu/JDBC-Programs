import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadDriver {
    public static void main(String[] args) {
        System.out.println("hello");

        // Connection object to be used later
        Connection connection = null;

        try {
            // Register the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database (replace with your actual URL, username, and password)
            connection = DriverManager.getConnection("jdbc:mysql://localhost/student1?user=root&password=S@ndeep74");

            System.out.println("Connected to the database!");

        } catch (SQLException ex) {
            // Handle SQL exceptions (e.g., wrong credentials, database not reachable)
            System.out.println("SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            // Handle class not found exception if driver class is missing
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } finally {
            // Close the connection in the finally block to avoid resource leaks
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException ex) {
                    System.out.println("SQLException during close: " + ex.getMessage());
                }
            }
        }
    }
}
