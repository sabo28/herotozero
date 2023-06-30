import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.Objects;

@Named
@RequestScoped
public class ScientistBean {

    private String selectedCountry;
    private String inputValue;
    private List<String> options;
    private String pendingStatus;
    private String auth;
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

    public String getAuth() {
        return auth;
    }

    public void submitData() throws ClassNotFoundException {
        if (!inputValue.matches("[0-9]+([.][0-9]+)?")) {
            this.pendingStatus = "Error: You can only enter numbers in this format: 1234 or 1234.1234";
        }else {
            this.pendingStatus = dataController.sendNewEmissionData(selectedCountry, inputValue, loginBean.getUsername());
        }
    }

    public String confirmAuth() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String userRole = (String) externalContext.getSessionMap().get("userRole");
        if (Objects.equals(userRole, "scientist")) {
            return null;
        }else {
            this.auth = "empty";
            return "Not authorized!";
        }
    }
}
