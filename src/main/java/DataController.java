import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.IOException;

@Named
@RequestScoped
public class DataController {

    public String getEmissionData(String country) throws IOException {
        DataService dataService = new DataService();
        return dataService.fetchCSVData(country);
    }
}
