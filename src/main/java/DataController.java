import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class DataController {
    public DataController() {
    }
    private final DataService dataService = new DataService();

    public String getEmissionData(String country) throws ClassNotFoundException {
        return dataService.fetchEmissionData(country).isEmpty() ? dataService.fetchEmissionData(country) : dataService.fetchEmissionData(country) + "kt";
    }

    public String loginUser(String username, String password) throws ClassNotFoundException {
        return dataService.login(username, password);
    }

    public List<String> getAllCountries() throws ClassNotFoundException {
        return dataService.getAllCountries();
    }

    public String sendNewEmissionData(String selectedCountry, String inputValue, String username) throws ClassNotFoundException {
        return dataService.sendNewEmissionData(selectedCountry, inputValue, username);
    }

    public List<PendingRequest> getAllPendingRequests() throws ClassNotFoundException {
        return dataService.getAllPendingRequests();
    }

    public boolean confirmRequest(PendingRequest pendingRequest) {
        return dataService.confirmRequest(pendingRequest);
    }

    public boolean deleteRequest(PendingRequest pendingRequest) {
        return dataService.deleteRequest(pendingRequest);
    }
}
