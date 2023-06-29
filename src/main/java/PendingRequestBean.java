import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class PendingRequestBean {

    private List<PendingRequest> requests;

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
}
