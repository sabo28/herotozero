import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class AllCountriesBean {

    private String selectedCountry;
    private String inputValue;
    private List<String> options;
    private String pendingStatus;
    @Inject
    private LoginBean loginBean;
    @Inject
    private DataController dataController;
    @PostConstruct
    public void init() throws ClassNotFoundException {

        options = dataController.getAllCountries();
    }
    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public List<String> getOptions() {
        return options;
    }
    public String getPendingStatus() {
        return pendingStatus;
    }
    public void submitData() throws ClassNotFoundException {
        this.pendingStatus = dataController.sendNewEmissionData(selectedCountry, inputValue, loginBean.getUsername());
    }
}
