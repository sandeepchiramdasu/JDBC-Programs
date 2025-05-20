// jdbc program for batch update

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadDriver8 {
    public static void main(String[] args) {
        // Connection and PreparedStatement objects
        Connection conn = null;
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtUpdate = null;

        try {
            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database credentials)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // SQL statements for batch processing
            String insertSQL = "INSERT INTO students (rollno, name) VALUES (?, ?)";
            String updateSQL = "UPDATE students SET name = ? WHERE rollno = ?";

            // Prepare the insert statement
            pstmtInsert = conn.prepareStatement(insertSQL);
            pstmtInsert.setInt(1, 104);
            pstmtInsert.setString(2, "bharath");
            pstmtInsert.addBatch();

            // Prepare the update statement
            pstmtUpdate = conn.prepareStatement(updateSQL);
            pstmtUpdate.setString(1, "laxman");
            pstmtUpdate.setInt(2, 116);
            pstmtUpdate.addBatch();

            // Execute batch
            int[] results = pstmtInsert.executeBatch();
            int[] updateResults = pstmtUpdate.executeBatch();

            // Print results of the batch operations
            System.out.println("Insert result: " + results[0]);
            System.out.println("Update result: " + updateResults[0]);

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions (in case the JDBC driver isn't found)
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close PreparedStatements
            try {
                if (pstmtInsert != null) {
                    pstmtInsert.close();
                }
                if (pstmtUpdate != null) {
                    pstmtUpdate.close();
                }
            } catch (SQLException e) {
                System.err.println("SQLException during statement close: " + e.getMessage());
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
        }
    }
}
