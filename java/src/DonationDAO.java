import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DonationDAO {

    private Connection connection;

    public DonationDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertDonation(
            String donorName,
            java.math.BigDecimal amount,
            String donationType,
            String paymentMode,
            int festivalYear) {

        String sql =
                "INSERT INTO donations " +
                "(donor_name, amount, donation_type, payment_mode, festival_year) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, donorName);
            statement.setBigDecimal(2, amount);
            statement.setString(3, donationType);
            statement.setString(4, paymentMode);
            statement.setInt(5, festivalYear);

            int rowsAffected =
                    statement.executeUpdate();

            System.out.println(
                    "Rows inserted: " + rowsAffected
            );

            statement.close();

        } catch (Exception e) {

            System.out.println("Insert operation failed!");
            e.printStackTrace();
        }
    }

    public void getAllDonations() {

        String sql =
                "SELECT donor_name, amount " +
                "FROM donations";

        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            System.out.println("\nDonation Records:");

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

        } catch (Exception e) {

            System.out.println("Select operation failed!");
            e.printStackTrace();
        }
    }

    public void updateDonationAmount(
            java.math.BigDecimal newAmount,
            String donorName,
            int festivalYear) {

        String sql =
                "UPDATE donations " +
                "SET amount = ? " +
                "WHERE donor_name = ? " +
                "AND festival_year = ?";

        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setBigDecimal(1, newAmount);
            statement.setString(2, donorName);
            statement.setInt(3, festivalYear);

            int rowsAffected =
                    statement.executeUpdate();

            System.out.println(
                    "Rows updated: " + rowsAffected
            );

            statement.close();

        } catch (Exception e) {

            System.out.println("Update operation failed!");
            e.printStackTrace();
        }
    }

    public void deleteDonation(
            String donorName,
            int festivalYear) {

        String sql =
                "DELETE FROM donations " +
                "WHERE donor_name = ? " +
                "AND festival_year = ?";

        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, donorName);
            statement.setInt(2, festivalYear);

            int rowsAffected =
                    statement.executeUpdate();

            System.out.println(
                    "Rows deleted: " + rowsAffected
            );

            statement.close();

        } catch (Exception e) {

            System.out.println("Delete operation failed!");
            e.printStackTrace();
        }
    }
}
