import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DataController {
    private final DataService dataService = new DataService();
    public String getEmissionData(String country) throws ClassNotFoundException {
        return dataService.fetch(country);
    }
}
