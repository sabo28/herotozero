import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Co2EmissionBean {
    private String country;
    private int emissionData;

    public Co2EmissionBean() {
    }
    @Inject
    private DataController dataController;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getEmissionData() {
        return emissionData;
    }

    public void data() {
        this.emissionData = dataController.getEmissionData(country);
    }
}