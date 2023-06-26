import java.sql.*;

public class DataService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2emissiondata";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    public String fetchEmissionData(String country) throws ClassNotFoundException {
        String emissionData = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT emissionswert FROM emissions WHERE land = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                emissionData = resultSet.getString("emissionswert");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emissionData;
    }

    public String login(String username, String password) throws ClassNotFoundException {
        String loginStatus = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Benutzername und Passwort stimmen überein
                loginStatus = "Login successful";
            } else {
                // Benutzername und Passwort stimmen nicht überein
                loginStatus = "Wrong username or password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginStatus;
    }
}