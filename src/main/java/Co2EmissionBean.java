import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.xml.crypto.Data;
import java.io.Serializable;

@Named
@RequestScoped
public class Co2EmissionBean implements Serializable {
    private String country;
    private String emissionData;
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

    public void fetchEmissionData() throws ClassNotFoundException {
        this.emissionData = dataController.getEmissionData(country);
    }
}