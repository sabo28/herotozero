import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DataController {

    public int getEmissionData(String country) {
        return 2;
    }
}
