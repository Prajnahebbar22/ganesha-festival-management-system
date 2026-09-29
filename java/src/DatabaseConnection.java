import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/ganesha_festival";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

            String sql =
                    "DELETE FROM donations " +
                    "WHERE donor_name = ? " +
                    "AND festival_year = ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, "Test User");
            statement.setInt(2, 2026);

            int rowsAffected =
                    statement.executeUpdate();

            System.out.println(
                    "Rows deleted: " + rowsAffected
            );

            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Database operation failed!");
            e.printStackTrace();
        }
    }
}