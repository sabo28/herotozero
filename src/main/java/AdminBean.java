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
public class AdminBean {

    private List<PendingRequest> requests;
    private String auth;

    @Inject
    private DataController dataController;
    @PostConstruct
    public void init() throws ClassNotFoundException {
        requests = dataController.getAllPendingRequests();
    }

    public List<PendingRequest> getRequests() {
        return requests;
    }
    

    public void confirmRequest(PendingRequest pendingRequest) {
        if (dataController.confirmRequest(pendingRequest)){
            requests.remove(pendingRequest);
        }
    }

    public void deleteRequest(PendingRequest pendingRequest) {
        if (dataController.deleteRequest(pendingRequest)){
            requests.remove(pendingRequest);
        }
    }

    public String getAuth() {
        return auth;
    }

    public String confirmAuth() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String userRole = (String) externalContext.getSessionMap().get("userRole");
        if (Objects.equals(userRole, "admin")) {
            return null;
        }else {
            this.auth = "empty";
            return "Not authorized";
        }
    }
}
