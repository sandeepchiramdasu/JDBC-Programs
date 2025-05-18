// jdbc program to create table in a database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadDriver2 {
    public static void main(String[] args) {
        // Connection object
        Connection conn = null;
        Statement st = null;

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database name, username, and password)
            conn = DriverManager.getConnection("jdbc:mysql://localhost/student1?user=root&password=S@ndeep74");

            // Create the SQL query for table creation
            String sql = "CREATE TABLE IF NOT EXISTS studentS (rollno INT, name VARCHAR(255))";

            // Create a statement
            st = conn.createStatement();

            // Execute the SQL statement
            st.executeUpdate(sql);

            System.out.println("Table 'studentS' created successfully!");

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions (in case the JDBC driver isn't found)
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close the statement
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println("SQLException during statement close: " + e.getMessage());
                }
            }

            // Close the connection
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
