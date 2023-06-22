import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DataService {

    public String fetchCSVData(String country) throws IOException {

        final String CSV_URL = "https://myco2emissionbucket.s3.eu-central-1.amazonaws.com/world-bank-group-data/CO2_emissions/latest/API_EN.ATM.CO2E.KT_DS2_en_csv_v2_5551845.csv";

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(new URL(CSV_URL).openStream().readAllBytes());
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[0].trim().equalsIgnoreCase('"' + country + '"')) {
                    return parts[63].trim();
                }
            }
        }

        return "No data available for this country";
    }
}