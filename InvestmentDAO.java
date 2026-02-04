import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDAO {

	private static final String SELECT_SQL = "SELECT gold_worth, gold_gram, gold_worth_currency FROM Investment";
    private static final String INSERT_SQL = "INSERT INTO Investment (total_investment, gold_gram, gold_worth, gold_worth_currency) VALUES (?, ?, ?, ?)";

    public static void insertInvestment(
            double totalInvestment,
            double goldGram,
            double goldWorth,
            String currency) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setDouble(1, totalInvestment);
            ps.setDouble(2, goldGram);
            ps.setDouble(3, goldWorth);
            ps.setString(4, currency);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Investment> getAllInvestments() {
        List<Investment> list = new ArrayList<>();
        String query = "SELECT gold_worth, gold_gram, gold_worth_currency FROM Investment"; 

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Investment(
                    rs.getDouble("gold_worth"),
                    rs.getDouble("gold_gram"),
                    rs.getString("gold_worth_currency")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
