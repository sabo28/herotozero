import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String userStatus = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Benutzername und Passwort stimmen überein
                if (resultSet.getString("username").equals("admin")){
                    userStatus = "admin";
                }else {
                    userStatus = "scientist";
                }
            } else {
                // Benutzername und Passwort stimmen nicht überein
                userStatus = "Wrong username or password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userStatus;
    }

    public List<String> getAllCountries() throws ClassNotFoundException {
        List<String> columnData = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT land FROM emissions";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String data = resultSet.getString("land");
                    columnData.add(data);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnData;
    }

    public String sendNewEmissionData(String selectedCountry, String inputValue, String username) throws ClassNotFoundException {
        String pendingStatus = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO pendingrequests (scientist, land, emissionwert) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, selectedCountry);
            statement.setString(3, inputValue);
            if (statement.executeUpdate() == 0){
                pendingStatus = "Anfrage fehlgeschlagen";
            }else{
                pendingStatus = "Anfrage geschickt";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingStatus;

    }
}