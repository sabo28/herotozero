import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@Named
@RequestScoped
public class Co2EmissionBean {
    private String country;
    private String emissionData;

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

    public String getEmissionData() {
        return emissionData;
    }

    public void data() throws IOException {
        this.emissionData = dataController.getEmissionData(country);
    }
}