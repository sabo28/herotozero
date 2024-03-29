import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ScientistBean {

    private String selectedCountry;
    private String inputValue;
    private String pendingStatus;
    private String name;
    private final DataController dataController = new DataController();

    public ScientistBean(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() throws ClassNotFoundException {
        return dataController.getAllCountries();
    }

    public String getPendingStatus() {
        return pendingStatus;
    }


    public void submitData() throws ClassNotFoundException {
        if (!inputValue.matches("[0-9]+([.][0-9]+)?")) {
            this.pendingStatus = "Error: You can only enter numbers in this format: 1234 or 1234.1234";
        }else {
            this.pendingStatus = dataController.sendNewEmissionData(selectedCountry, inputValue, name);
        }
    }
}
