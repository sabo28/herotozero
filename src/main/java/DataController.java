import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.Objects;

@Named
@RequestScoped
public class DataController {
    private final DataService dataService = new DataService();
    public String getEmissionData(String country) throws ClassNotFoundException {
        String emissionData = dataService.fetch(country);
        return (!Objects.equals(emissionData, "")) ? emissionData : "Emissionswert nicht gefunden";
    }
}
