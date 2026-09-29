import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                    "SELECT donor_name, amount " +
                    "FROM donations " +
                    "WHERE amount > ?";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, 199);

            ResultSet resultSet =
                    statement.executeQuery();

            System.out.println("\nDonors who donated more than 200:");

            while (resultSet.next()) {

                String donorName =
                        resultSet.getString("donor_name");

                java.math.BigDecimal amount =
                        resultSet.getBigDecimal("amount");

                System.out.println(
                        "Donor: " + donorName +
                        " | Amount: " + amount
                );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Database operation failed!");
            e.printStackTrace();
        }
    }
}