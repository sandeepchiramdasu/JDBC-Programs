// jdbc program to retrive data from database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoadDriver4 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/student1?user=root&password=S@ndeep74");

            // Create statement and execute query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM studentS");

            // Process the result set
            while (rs.next()) {
                System.out.println("rollno:" + rs.getInt("rollno"));
                System.out.println("name: " + rs.getString("name"));
            }

        } catch (SQLException ex) {
            // Print SQL exception details
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: MySQL JDBC Driver not found.");
            ex.printStackTrace();
        } finally {
            // Close ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            // Close Statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }

            // Close Connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
        }
    }
}
