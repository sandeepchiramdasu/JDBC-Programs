import java.sql.*;
public class LoadDriver9 {
    public static void main(String[] args) {
        // Connection object
        Connection conn = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (replace with your actual database credentials)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1", "root", "S@ndeep74");

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            displayResult(rs);

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found exceptions
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("SQLException during connection close: " + e.getMessage());
            }
        }
    }

    static void displayResult(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int col = rsmd.getColumnCount();
        System.out.println("Number of columns: " + col);
        
        // Display column names and types
        for (int i = 1; i <= col; i++) {
            System.out.println("Column Name: " + rsmd.getColumnName(i));
            System.out.println("Column Type: " + rsmd.getColumnTypeName(i));
        }

        // Display data in the ResultSet
        while (rs.next()) {
            for (int i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}
